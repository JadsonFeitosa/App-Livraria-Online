package com.atividade.main.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atividade.main.model.Endereco;
import com.atividade.main.model.Usuario;
import com.atividade.main.repository.UsuarioRepository;
import com.atividade.main.repository.dto.UsuarioDTO;
import com.atividade.main.service.exception.UsuarioExistException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario save(Usuario usuario) {
		if(isUsuarioExist(usuario.getCPF())) {
			throw new UsuarioExistException();
		}
		
		return usuarioRepository.save(usuario);
	}

	public Usuario update(Long codigo ,Usuario usuario) {
		Usuario usuarioSalvo = usuarioRepository.findById(codigo).get();
		if(usuarioSalvo==null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(usuario, usuarioSalvo,"userID");
		usuarioRepository.save(usuarioSalvo);
		return usuarioSalvo;	
		
	}
	
	public void delete(long id) {
		usuarioRepository.deleteById(id);
	}
	
	public Usuario findById(long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.get();
	}
	
	public boolean isUsuarioExist(String cpf) {
		Usuario usuario=usuarioRepository.findUsuarioByCPF(cpf);
		return usuario == null ? false : true;
 	}
	
	public Usuario getUsuarioPorNome(String nome) {
		return usuarioRepository.findUsuarioByNome(nome);
	}

	public List<UsuarioDTO> getListaOrdenadaAsedente() {
		return usuarioRepository.filter();
	}

	public Usuario getUserPorEmail(String email) {

		return usuarioRepository.findUsuarioByEmail(email);
	}

//	 enviar uma notificação para finalização do pedido*
	public void sendEmailNotficacao(long id) {
//		Luan ficou de fazer
		
	}
	
	public Page<Endereco> buscarListaEnderecos(Usuario usuario, Pageable page) {
		return usuarioRepository.findUsuarioByEnderecos(usuario, page);
		
		
	}

}
