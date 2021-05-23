package com.atividade.main.repository.query;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atividade.main.repository.dto.UsuarioDTO;

public interface UsuarioRepositoryQuery {
	
	public List<UsuarioDTO> filter();

}
