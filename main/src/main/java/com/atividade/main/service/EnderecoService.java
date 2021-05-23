package com.atividade.main.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.atividade.main.model.Endereco;
import com.atividade.main.repository.EnderecoRepository;


@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Endereco save(Endereco endereco) {
		return enderecoRepository.save(endereco);
		
	}
	
	public Endereco update(Long codigo,Endereco endereco) {
		Endereco enderecoSalvo = enderecoRepository.findById(codigo).get();
		if(enderecoSalvo==null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(endereco, enderecoSalvo,"enderecoId");
		enderecoRepository.save(endereco);
		return enderecoSalvo;
	}
	
	public void excluir(long id) {
		enderecoRepository.deleteById(id);
	}
	
	public Endereco findById(long id) {
		Optional<Endereco> endereco =enderecoRepository.findById(id);
		return endereco.get();
	}
	
	public Endereco getEnderecoPorNome(String CEP){
		return enderecoRepository.findEnderecoByCep(CEP);
	}
	
	public Page<Endereco> getListaOrdenadaAsedente(Pageable page){
        return enderecoRepository.findAll(page);
	}


}
