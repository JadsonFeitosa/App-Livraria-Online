package com.atividade.main.repository.dto;

import org.springframework.beans.BeanUtils;

import com.atividade.main.model.Sexo;
import com.atividade.main.model.Usuario;
import com.atividade.main.model.Usuario_;

import lombok.Data;

public class UsuarioDTO {
	
	private Long userId;
	
	private String nome;
	
	private String email;
	
	private String celular;
	
	private String CPF;
	
	private Sexo sexo;
	
	
	
	
	
	public UsuarioDTO(Long userId, String nome, String email, String celular, String cPF, Sexo sexo) {
		super();
		this.userId = userId;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		CPF = cPF;
		this.sexo = sexo;
	}



	public UsuarioDTO (Usuario usuario) {
		BeanUtils.copyProperties(usuario, this, Usuario_.USER_ID, Usuario_.SENHA);
	}



	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getCelular() {
		return celular;
	}



	public void setCelular(String celular) {
		this.celular = celular;
	}



	public String getCPF() {
		return CPF;
	}



	public void setCPF(String cPF) {
		CPF = cPF;
	}



	public Sexo getSexo() {
		return sexo;
	}



	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	
}
