package com.atividade.main.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atividade.main.model.Editora;
import com.atividade.main.repository.EditoraRepository;
import com.atividade.main.service.exception.EditoraExistException;


@Service
public class EditoraService {
	
	@Autowired
	private EditoraRepository editoraRepository;

	public Editora save(Editora editora) {
		if(isEditoraExist(editora.getCNPJ())){
			throw new EditoraExistException();
		}
		return editoraRepository.save(editora);
	}
	
	public Editora update(Long codigo, Editora editora) {
		Editora editoraSalvo = editoraRepository.findById(codigo).get();
		if (editoraSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(editora, editoraSalvo, "editoraId");
		editoraRepository.save(editoraSalvo);
		return editoraSalvo;
	}
	
	public void excluir(long id) {
		editoraRepository.deleteById(id);
	}
	
	public Editora findById(long id) {
		Optional<Editora> editora=editoraRepository.findById(id);
		return editora.get();
	}
	
	public boolean isEditoraExist(String cnpj) {
		Editora editora=editoraRepository.findEditoraByCNPJ(cnpj);
		return editora == null ? false : true;
		
	}
	
	public Editora findByCNPJ(String cnpj) {
		Editora editora=editoraRepository.findEditoraByCNPJ(cnpj);
		return editora;
	}
	public Editora getEditoraPorNome(String nome) {
		return editoraRepository.findEditoraByNome(nome);
	}	
	public Page<Editora> getListaOrdenadaAsedente(Pageable page){
		return editoraRepository.findAll(page);
	}
	
}
