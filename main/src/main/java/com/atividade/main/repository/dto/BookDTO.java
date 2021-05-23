package com.atividade.main.repository.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.atividade.main.model.Book;
import com.atividade.main.model.Book_;
import com.atividade.main.model.Categoria;
import com.atividade.main.model.Editora;
import com.atividade.main.model.Estoque;

import lombok.Data;

@Data
public class BookDTO {
	
	private long livroId;
	private String titulo;
	private String descricao;
	private BigDecimal price;
	private String ISBN;
	private String capa;
	private String edicao;
	private String anoPublicacao;
	private Categoria categoria;
	private Editora editora;
	private Estoque estoque;
	private List<AutorDTO> autores = new ArrayList<AutorDTO>();
	
	
	
    public BookDTO (Book book) {
    	BeanUtils.copyProperties(book, Book_.LISTA_PEDIDO,Book_.LIST_AUTOR);
    	this.autores = book.getListAutor().stream().map(e-> new AutorDTO(e)).collect(Collectors.toList());
    }

	public BookDTO() {
	}
	
	public static Book convertBookDTOForBook(BookDTO dto) {
		Book book = new Book();
		book.setPrice(dto.getPrice());
		BeanUtils.copyProperties(dto, book);
		
		return book;
	}


}
