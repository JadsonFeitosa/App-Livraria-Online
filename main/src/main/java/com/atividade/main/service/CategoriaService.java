package com.atividade.main.service;

import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atividade.main.model.Categoria;
import com.atividade.main.repository.CategoriaRepository;
import com.atividade.main.service.exception.CategoriaExistException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria save(Categoria categoria) {
		if(isCategoriaExist(categoria.getDescricao())) {
			throw new CategoriaExistException();
		}
		return categoriaRepository.save(categoria);
	}
	
	public Categoria update(Long codigo, Categoria categoria) {
		Categoria categoriaSalva = categoriaRepository.findById(codigo).get();
		BeanUtils.copyProperties(categoria, categoriaSalva, "categoriaId");
		return categoriaRepository.save(categoriaSalva);
	}
	
	public void excluir(long id) {
		categoriaRepository.deleteById(id);
	}
	
	public Page<Categoria> getListaOrdenadaAsedente(Pageable page){
        return categoriaRepository.findAll(page);
	}
	
	public boolean isCategoriaExist(String descricao) {
		Categoria categoria = categoriaRepository.findCategoriaByDescricao(descricao);
		return categoria ==  null ? false : true;
	}
	
	public Categoria findCategoriaByDescricao(String descricao) {
		Categoria categoria = categoriaRepository.findCategoriaByDescricao(descricao);
		return categoria ==  null ? null: categoria;
	}
		

	public Categoria CategoriafindById(long id) {
		Categoria categoria=categoriaRepository.findById(id).get();
		
		if(categoria == null) {
			 throw new NoSuchElementException();
		}
		return categoria;
	}

	
}
