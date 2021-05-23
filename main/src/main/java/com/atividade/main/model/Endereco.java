package com.atividade.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long endID;
	
	@NotNull
	private String cep;
	
	@NotNull
	private String rua;
	
	@NotNull
	private int numero;
	
	@NotNull
	private String bairro;
	
	@NotNull
	private String cidade;
	
	@Column(length = 2)
	private String UF;
	
	@NotNull
	private String complemento;
	
	@Column(name ="PONTOREFERENCIA")
	private String pontoReferencia;
	
	@ManyToOne
	@JoinColumn(name="USERID")
	private Usuario usuario;
	
	
	
}
