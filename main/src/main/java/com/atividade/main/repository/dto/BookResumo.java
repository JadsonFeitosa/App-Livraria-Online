package com.atividade.main.repository.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class BookResumo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long livroId;
	private String titulo;
	private String descricao;
	private BigDecimal price;
	private String ISBN;
	private String capa;
	private String edicao;
	private String anoPublicacao;
	private String categoria;
	private String editora;
	private Integer estoque;
	
	public BookResumo(){
		
	}

	public BookResumo(Long livroId, String titulo, String descricao, BigDecimal price, String iSBN, String capa,
			String edicao, String anoPublicacao, String categoria, String editora, Integer estoque) {
		super();
		this.livroId = livroId;
		this.titulo = titulo;
		this.descricao = descricao;
		this.price = price;
		this.ISBN = iSBN;
		this.capa = capa;
		this.edicao = edicao;
		this.anoPublicacao = anoPublicacao;
		this.categoria = categoria;
		this.editora = editora;
		this.estoque = estoque;
		

	}
	public BookResumo(Long livroId, String titulo, String descricao, BigDecimal price, String iSBN, String capa,
			String edicao, String anoPublicacao, String categoria, String editora) {
		this.livroId = livroId;
		this.titulo = titulo;
		this.descricao = descricao;
		this.price = price;
		ISBN = iSBN;
		this.capa = capa;
		this.edicao = edicao;
		this.anoPublicacao = anoPublicacao;
		this.categoria = categoria;
		this.editora = editora;
	}

	public Long getLivroId() {
		return livroId;
	}

	public void setLivroId(Long livroId) {
		this.livroId = livroId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public String getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(String anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	


}
