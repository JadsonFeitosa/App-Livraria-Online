package com.atividade.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Estoque {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long estoqueid;
	
	@OneToOne
	@JoinColumn(name = "LIVROID")
	private Book livroid;
	
	@NotNull
	private int quantidade;
	
	private String prateleira;
	

}
