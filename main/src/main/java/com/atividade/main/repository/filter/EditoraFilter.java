package com.atividade.main.repository.filter;

public class EditoraFilter {

	private long editoraId;
	private String nome;
	
	
	public EditoraFilter(Long editoraId, String nome){
		this.editoraId= editoraId;
		this.nome=nome;
		
	}


	public long getEditoraId() {
		return editoraId;
	}


	public void setEditoraId(long editoraId) {
		this.editoraId = editoraId;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	
	



	
	
	
	
}


