package com.atividade.main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atividade.main.model.Endereco;
import com.atividade.main.model.Usuario;
import com.atividade.main.repository.query.UsuarioRepositoryQuery;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery{
	

//	Buscar por nome do Usuario
	public Usuario findUsuarioByNome(String nome);
	
//	retorna uma listapaginadar ordenada por nome
	public Page<Usuario>findAllByNome(String nome, Pageable pagina);
	
//	Buscar por nome do Usuario
	public Usuario findUsuarioByCPF(String CPF);
	
//	Buscar por email
	public Usuario findUsuarioByEmail(String email);
	
//	Buscar enderecos de usuario
	public Page<Endereco> findUsuarioByEnderecos(Usuario usuario, Pageable page);
		

}
