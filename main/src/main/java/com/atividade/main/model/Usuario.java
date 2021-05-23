package com.atividade.main.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userID;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String email;
	
	
	private String celular;
	
	@NotNull
	private String CPF;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	
	private Sexo sexo;
	
	@NotNull
	private String senha;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "USER_PAPEL")
	private UserPapel userPapel;
	
//	@NotNull
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.MERGE)
	private List<Endereco> enderecos;
	
	//@PrePersist
	private void inclusao() {
		Email email = new Email();
		email.sendEmail(this.email, "Oi, "+this.nome+" Bem vindo a CJL Livraria online!"
				, "Seu dados de acesso são:\n"
						+ "Email:"+this.email+"\n"
								+ "Senha:"+this.senha);
	}
//	@PreUpdate
	private void atulizacao() {
		Email email = new Email();
		String end = null;
		for(Endereco e: this.enderecos) {
			end = "Cep:"+e.getCep()+"\n"
				 +"Rua:"+e.getRua()+"\n"
				 +"Numero:"+e.getNumero()+"\n"
				 +"Rua:"+e.getComplemento()+"\n"
				 +"Bairro:"+e.getBairro()+"\n"
				 +"Cidade:"+e.getCidade()+"\n"
				 +"UF:"+e.getUF()+"\n"
				 +"PontoReferencia:"+e.getPontoReferencia()+"\n"
				 +"==========*==========";
		}
		email.sendEmail(this.email, "Oi, "+this.nome+" Seu dados foram Atualizados!"
				, "Seu atualizado foram:\n"
						+ "CPF:"+this.CPF+"\n"
						+ "Nome:"+this.nome+"\n"
						+ "Celular:"+this.celular+"\n"
						+ "Sexo:"+this.email+"\n"
						+ "Email:"+this.email+"\n"
						+ "Senha:"+this.senha+"\n"
						+ "============*Seus endereços*===========\n"
						+ end);
	}
	
	
}
