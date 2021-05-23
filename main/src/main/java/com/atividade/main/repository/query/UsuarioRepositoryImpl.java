package com.atividade.main.repository.query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.atividade.main.model.Autor;
import com.atividade.main.model.Autor_;
import com.atividade.main.model.Book;
import com.atividade.main.model.Book_;
import com.atividade.main.model.Categoria_;
import com.atividade.main.model.Editora_;
import com.atividade.main.model.Estoque_;
import com.atividade.main.model.Usuario;
import com.atividade.main.model.Usuario_;
import com.atividade.main.repository.dto.BookResumo;
import com.atividade.main.repository.dto.UsuarioDTO;
import com.atividade.main.repository.filter.AutorFilter;

public class UsuarioRepositoryImpl  implements UsuarioRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<UsuarioDTO> filter() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<UsuarioDTO> criteria = builder.createQuery(UsuarioDTO.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		root.fetch(Usuario_.enderecos); 
		TypedQuery<UsuarioDTO> query = manager.createQuery(criteria).setMaxResults(5);
		return query.getResultList();
	}
	

	@SuppressWarnings("unused")
	private Long totalDeRegistro(Usuario filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		Predicate[] predicates = criarRestricoes(filter,builder, root );
		criteria.where(predicates).multiselect(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}


	@SuppressWarnings("unused")
	private void adicionarPaginacao(TypedQuery<?> query, Pageable page) {
		int paginaAtual =  page.getPageNumber();
		int totalRegistroPage = page.getPageSize();
		int primeiroResgistroPage = paginaAtual * totalRegistroPage;
		
		query.setFirstResult(primeiroResgistroPage);
		query.setMaxResults(totalRegistroPage);
		
	}


	private Predicate[] criarRestricoes(Usuario autorFilter, CriteriaBuilder builder, Root<Usuario> root) {
		List<Predicate> predicates = new ArrayList<Predicate>();		
	
		if(autorFilter.getNome()!=null) {
			predicates.add(builder.like(builder.lower(root.get(Usuario_.NOME)), "%"+autorFilter.getNome().toLowerCase()+"%"));
		}
		
		return predicates.toArray( new Predicate[predicates.size()]);
	}

}
