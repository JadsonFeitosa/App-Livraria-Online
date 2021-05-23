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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atividade.main.event.RecursoCriadoEvent;
import com.atividade.main.model.Estoque;
import com.atividade.main.service.EstoqueService;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
	
	@Autowired
	private EstoqueService estoqueService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

//	metodo de salvar Estoque
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Estoque> save(@Valid @RequestBody Estoque estoque, HttpServletResponse response) {
		Estoque estoqueSalvo = estoqueService.save(estoque);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, estoqueSalvo.getEstoqueid()));
		return ResponseEntity.status(HttpStatus.CREATED).body(estoqueSalvo);
	}

//	metodo de atualizar entidade
	@PutMapping("/{codigo}")
	public ResponseEntity<Estoque> update(@PathVariable Long codigo, @RequestBody Estoque estoque) {
		Estoque estoqueSalvo = estoqueService.update(codigo, estoque);
		return ResponseEntity.ok(estoqueSalvo);
	}

//	metodo de deletar
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(long id) {
		estoqueService.delete(id);
	}
	
	public Estoque findById(long id) {
		return estoqueService.findById(id);
	}
	
	@GetMapping
	public Page<Estoque> getListaOrdenadaAsedente(Pageable page){
        return estoqueService.getListaOrdenadaAsedente(page);
	}
	
	
//	consultar os 5 livros mais baratos disponíveis no estoque;
	public Page<Estoque> getListaCincoMaisBaratos(Pageable page){
        return estoqueService.getListaCincoMaisBaratos(page);
	}
	
}
