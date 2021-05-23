package com.atividade.main.model;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;



@Entity
@Data
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="AUTORID")
	private Long autorId;
	
	@NotNull
	@Size(min=4, max=255)
	private String nome;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	//@Size(min=1)
	private Sexo sexo;
	
	@NotNull
	@Size(min=3, max=50)
	private String nacionalidade;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name="DATANASCIMENTO")
	private Date dtNascimento;
	
	@ManyToMany(mappedBy = "listAutor")
	private List<Book>listLivro;

	

}