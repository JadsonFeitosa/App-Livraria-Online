package com.atividade.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Editora {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="editoraid")
	private long editoraId;

	@NotNull
	private String CNPJ;

	@NotNull
	private String nome;

	@NotNull
	private String cep;

	@NotNull
	private String rua;

	
	private int numero;

	@NotNull
	private String bairro;

	@NotNull
	private String cidade;

	@NotNull
	@Size(min=2,max=2)
	private String UF;

}
