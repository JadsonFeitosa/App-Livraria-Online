package com.atividade.main.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;

import lombok.Data;

@Entity
@Table(name = "LIVRO")
@Data
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="LIVROID")
	private long livroId;

	@NotNull
	private String titulo;

	@NotNull
	private String descricao;

	@NotNull
	private BigDecimal price;

	@NotNull
	private String ISBN;

	@NotNull
	private String capa;

	@NotNull
	private String edicao;
	
	@NotNull
	@Column(name="ANOPUBLICACAO")
	private String anoPublicacao;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "CATEGORIA")
	private Categoria categoria;

	@NotNull
	@OneToOne
	@JoinColumn(name = "EDITORAID")
	private Editora editora;

	@OneToOne(mappedBy = "livroid")
	private Estoque estoque;

	@OneToMany(mappedBy = "bookId")
	private List<BookPedido> listaPedido;
	

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE})
	@JoinTable(name = "LIVROAUTOR", joinColumns = @JoinColumn(name = "LIVROID"), 
	inverseJoinColumns = @JoinColumn(name = "AUTORID"))
	private List<Autor> listAutor;

}
