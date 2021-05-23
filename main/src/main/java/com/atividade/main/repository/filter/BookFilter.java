package com.atividade.main.repository.filter;

import com.atividade.main.model.Categoria;
import com.atividade.main.model.Editora;

import lombok.Data;

@Data
public class BookFilter {

	private String titulo;
	private double priceDe;
	private double priceAte;
	private String anoPublicacao;
	private String edicao;
	private Categoria categoria;
	private Editora editora;
	
	
}
