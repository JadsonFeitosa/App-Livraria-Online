package com.atividade.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atividade.main.event.RecursoCriadoEvent;
import com.atividade.main.model.Book;
import com.atividade.main.repository.dto.BookDTO;
import com.atividade.main.repository.dto.BookResumo;
import com.atividade.main.repository.filter.BookFilter;
import com.atividade.main.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private ApplicationEventPublisher publisher;

//	metodo de salvar book

	@PostMapping
	public ResponseEntity<BookDTO> save(@RequestBody @Valid Book book, HttpServletResponse response) {
		BookDTO bookSalvo = bookService.save(book);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, bookSalvo.getLivroId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(bookSalvo);
	}

//	metodo de atualizar entidade

	@PutMapping("/{codigo}")
	public ResponseEntity<Book> update(@PathVariable Long codigo, @RequestBody Book book) {
		Book bookSalvo = bookService.update(codigo, book);
		return ResponseEntity.ok(bookSalvo);
	}

//	metodo de deletar

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long codigo) {
		bookService.delete(codigo);
	}

	@GetMapping(params = "baratos")
	public Page<BookResumo> getListaCincoMaisBaratos(Pageable page) {
		System.err.println("Aqui estou");
		return bookService.findListaCincoMaisBaratos(page);
	}

//	consultar todos os livros (em estoque e sem estoque tb) ordenados de forma ascendente pelo título de forma paginada 
	// (defina um tamanho fixo para a página - ex.: 5 livros). O usuário pode
	// informar a página que deseja consultar.
	@GetMapping(params = "tudo")
	public Page<BookResumo> findListBookOrdenadaTituloComOuSemEstoque(Pageable page) {
		return bookService.findListBookOrdenadaTituloComOuSemEstoque(page);
	}

	// retorna uma lista de livro com filtro
	@GetMapping
	public Page<BookDTO> getListaBookAllPaginada(BookFilter filter, Pageable page) {
		return bookService.getListaBookAllPaginada(filter, page);
	}

}
