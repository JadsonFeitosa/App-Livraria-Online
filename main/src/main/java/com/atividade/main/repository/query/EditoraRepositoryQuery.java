package com.atividade.main.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atividade.main.model.Editora;
import com.atividade.main.repository.filter.EditoraFilter;

public interface EditoraRepositoryQuery {
	
	public Page<EditoraFilter>  listaNomes(Pageable page);
	
	public Page<Editora> buscaByNome(String nome);


}
