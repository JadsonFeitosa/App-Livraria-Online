package com.atividade.main.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name="BOOKPEDIDO")

//classe intermediaria entre pedido e livro fazendo uym reação de n to n  
public class BookPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BOOKPEDIDOID")
	private long bookPedidoId;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATAVENDA")
	private Date dataVenda;
	
	@NotNull
	@Column(name="QUANTIDADE")
	private int quantidadeVendida;
	
	@ManyToOne
	@JoinColumn(name="LIVROID")
	private Book bookId;
	
	@ManyToOne
	@JoinColumn(name="PEDIDOID")
	private Pedido pedidoId;
	
	@PrePersist
	private void setDateAtual() {
		this.dataVenda = new Date();
	}
	
}
