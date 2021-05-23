package com.atividade.main.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atividade.main.event.RecursoCriadoEvent;
import com.atividade.main.model.BookPedido;
import com.atividade.main.service.BookPedidoService;


@RestController
@RequestMapping("/bookpedidos")
public class BookPedidoController {
	
	@Autowired
	private BookPedidoService bookPedidoService;

	@Autowired
	private ApplicationEventPublisher publisher;
	
//	metodo salvar bookPedido
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<BookPedido> save(@Valid @RequestBody BookPedido bookPedido, HttpServletResponse response){
		BookPedido bookPedidoSalve = bookPedidoService.save(bookPedido);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, bookPedido.getBookPedidoId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(bookPedidoSalve);
	}
	
//	metodo de atualizar entidade
	@PostMapping("/{codigo}")
	public ResponseEntity<BookPedido> updade(@PathVariable long codigo, @RequestBody BookPedido bookPedido){
		BookPedido bookPedidoSalve = bookPedidoService.update(codigo, bookPedido);
		return ResponseEntity.ok(bookPedidoSalve);
	}
	
//	metodo de deletar
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(long id) {
		bookPedidoService.delete(id);
	}
	
	public BookPedido findById(long id) {
		return bookPedidoService.findById(id);
	}
	
	public Page<BookPedido> getListaOrdenadaAsedente(Pageable page){
        return bookPedidoService.getListaOrdenadaAsedente(page);
	}
	
//	consultar os 5 livros mais baratos disponíveis no BookPedido;
	public Page<BookPedido> getListaCincoMaisBaratos(Pageable page){
        return bookPedidoService.getListaOrdenadaAsedente(page);
	}
}
	
