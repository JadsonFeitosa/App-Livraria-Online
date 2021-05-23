package com.atividade.main.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atividade.main.model.Book;
import com.atividade.main.model.Estoque;
import com.atividade.main.repository.EstoqueRepository;


@Service
public class EstoqueService {
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	public Estoque save( Estoque estoque) {
		return estoqueRepository.save(estoque);
		
	}
	
	public Estoque update(long codigo, Estoque estoque) {
		Estoque estoqueSalvo = estoqueRepository.findById(codigo).get();
		if (estoqueSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(estoque, estoqueSalvo, "estoqueID");
		estoqueRepository.save(estoque);
		return estoqueSalvo;
	}

	public void delete(long id) {
		estoqueRepository.deleteById(id);
	}
	
	
	
	
	public Estoque findById(long id) {
		Optional<Estoque> b =estoqueRepository.findById(id);
		return b.get();
	}
	
	public Page<Estoque> getListaOrdenadaAsedente(Pageable page){
        return estoqueRepository.findAll(page);
	}
	
//	consultar os 5 livros mais baratos disponíveis no estoque;
	public Page<Estoque> getListaCincoMaisBaratos(Pageable page){
        return estoqueRepository.findAll(page);
	}
}
