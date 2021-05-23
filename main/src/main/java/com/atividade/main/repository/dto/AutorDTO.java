package com.atividade.main.repository.dto;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.atividade.main.model.Autor;
import com.atividade.main.model.Autor_;
import com.atividade.main.model.Book;
import com.atividade.main.model.Sexo;

import lombok.Data;

public class AutorDTO {

	private Long autorId;

	private String nome;

	private Sexo sexo;

	private String nacionalidade;

	private Date dtNascimento;

	private List<Book> listLivro;
	
    public AutorDTO (Autor autor) {
    	BeanUtils.copyProperties(autor, Autor_.listLivro);
    }
    
    
    

	public AutorDTO(Long autorId, String nome, Sexo sexo, String nacionalidade, Date dtNascimento) {
		this.autorId = autorId;
		this.nome = nome;
		this.sexo = sexo;
		this.nacionalidade = nacionalidade;
		this.dtNascimento = dtNascimento;
	}




	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public List<Book> getListLivro() {
		return listLivro;
	}

	public void setListLivro(List<Book> listLivro) {
		this.listLivro = listLivro;
	}

    
    
}
