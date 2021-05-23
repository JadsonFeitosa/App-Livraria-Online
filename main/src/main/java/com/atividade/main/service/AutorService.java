package com.atividade.main.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atividade.main.model.Autor;
import com.atividade.main.repository.AutorRepository;
import com.atividade.main.repository.dto.AutorDTO;
import com.atividade.main.repository.filter.AutorFilter;


@Service
public class AutorService {
	
	@Autowired
	private AutorRepository autorRepository;

	public Autor save(Autor autor) {
		return autorRepository.save(autor);
	}
	
	public  Autor update(Long codigo, Autor autor) {
		Autor autorSalvo = buscaAutorById(codigo);
		BeanUtils.copyProperties(autor, autorSalvo,"autorId");
		autorRepository.save(autorSalvo);
		return autorSalvo;	
	}

	private Autor buscaAutorById(Long codigo) {
		Autor autorSalvo = autorRepository.findById(codigo).get();
		if(autorSalvo==null) {
			throw new EmptyResultDataAccessException(1);
		}
		return autorSalvo;
	}
	
	
	public void delete(long id) {
		buscaAutorById(id);
		autorRepository.deleteById(id);
	}
	
	public Autor findById(long id) {
		Optional<Autor> autorSalvo = autorRepository.findById(id);
	
		
		return autorSalvo != null ? autorSalvo.get() : null;
	}
	
	public Autor getAutorPorNome(String nome){
		return autorRepository.findAutorByNome(nome);
	}
	
	public Page<AutorDTO> getListaOrdenadaAsedente(Pageable page){
        return autorRepository.listaAll(page);
	}
	
}
