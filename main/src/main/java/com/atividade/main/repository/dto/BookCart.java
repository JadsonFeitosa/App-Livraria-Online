package com.atividade.main.repository.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class BookCart implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long bookPedidoId;
	private Long livroId;
	private String titulo;
	private BigDecimal price;
	private Integer quantidadeVendida;
	private String capa;
	private String categoria;
	private BigDecimal total;
	
	
	
	public BookCart(){
		
	}



	public BookCart(long bookPedidoId, Long livroId, String titulo, BigDecimal price, Integer quantidadeVendida, String capa,
			String categoria, BigDecimal total ) {
		super();
		this.bookPedidoId = bookPedidoId;
		this.livroId = livroId;
		this.titulo = titulo;
		this.price = price.multiply(new BigDecimal(quantidadeVendida));
		this.quantidadeVendida = quantidadeVendida;
		this.capa = capa;
		this.categoria = categoria;
		this.total = total;
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



	public BigDecimal getPrice() {
		return price;
	}



	public void setPrice(BigDecimal price) {
		this.price = price;
	}



	public Integer getQuantidadeVendida() {
		return quantidadeVendida;
	}



	public void setQuantidadeVendida(Integer quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}



	public String getCapa() {
		return capa;
	}



	public void setCapa(String capa) {
		this.capa = capa;
	}



	public String getCategoria() {
		return categoria;
	}



	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}



	public BigDecimal getTotal() {
		return total;
	}



	public void setTotal(BigDecimal total) {
		this.total = total;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public long getBookPedidoId() {
		return bookPedidoId;
	}



	public void setBookPedidoId(long bookPedidoId) {
		this.bookPedidoId = bookPedidoId;
	}


	



}
