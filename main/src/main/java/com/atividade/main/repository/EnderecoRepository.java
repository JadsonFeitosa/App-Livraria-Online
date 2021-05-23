package com.atividade.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atividade.main.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

//	Busca de endereco por cep, rua, bairro
	public Endereco findEnderecoByCep(String CEP);


}
