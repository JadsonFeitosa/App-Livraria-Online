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
import com.atividade.main.model.Editora;
import com.atividade.main.model.Editora_;
import com.atividade.main.repository.filter.EditoraFilter;

public class EditoraRepositoryImpl  implements EditoraRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	
	@Override
	public Page<Editora> buscaByNome(String nome) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Editora> criteria = builder.createQuery(Editora.class);
		Root<Editora> root = criteria.from(Editora.class);
		criteria.multiselect(builder.like(builder.lower(root.get(Editora_.NOME)), "%"+nome.toLowerCase()+"%"));
	
		TypedQuery<Editora> query = manager.createQuery(criteria);
		return new PageImpl<>(query.getResultList(), null,query.getMaxResults());
	}


	

	@Override
	public Page<EditoraFilter> listaNomes(Pageable page) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<EditoraFilter> criteria = builder.createQuery(EditoraFilter.class);
		Root<Editora> root = criteria.from(Editora.class);
	    criteria.select(builder.construct(EditoraFilter.class
	    		,root.get(Editora_.editoraId)
	    		,root.get(Editora_.nome))); 
		criteria.orderBy(builder.desc(root.get(Editora_.nome)));
		TypedQuery<EditoraFilter> query = manager.createQuery(criteria);
		return new PageImpl<>(query.getResultList(), page, page.getPageSize());
	}
	
	
	private Predicate[] criarRestricoes(EditoraFilter filter, CriteriaBuilder builder, Root<Editora> root) {
		List<Predicate> predicates = new ArrayList<Predicate>();		
	
		if(filter.getNome()!=null) {
			predicates.add(builder.like(builder.lower(root.get(Editora_.NOME)), "%"+filter.getNome().toLowerCase()+"%"));
		}
		
		return predicates.toArray( new Predicate[predicates.size()]);
	}

	private Long totalDeRegistro(EditoraFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Editora> root = criteria.from(Editora.class);
		Predicate[] predicates = criarRestricoes(filter,builder, root );
		criteria.where(predicates).multiselect(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}


	private void adicionarPaginacao(TypedQuery<?> query, Pageable page) {
		int paginaAtual =  page.getPageNumber();
		int totalRegistroPage = page.getPageSize();
		int primeiroResgistroPage = paginaAtual * totalRegistroPage;
		
		query.setFirstResult(primeiroResgistroPage);
		query.setMaxResults(totalRegistroPage);
		
	}

}
