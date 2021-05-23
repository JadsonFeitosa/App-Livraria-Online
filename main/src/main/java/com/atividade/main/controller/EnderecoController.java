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
import com.atividade.main.model.Endereco;
import com.atividade.main.repository.EnderecoRepository;
import com.atividade.main.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService; 
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	

	@Autowired
	private ApplicationEventPublisher publisher;
	
	
//	metodo de salvar autor
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Endereco> save(@Valid @RequestBody Endereco endereco, HttpServletResponse response) {
		Endereco enderecoSalvo = enderecoService.save(endereco);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, enderecoSalvo.getEndID()));
		return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
	
	}

//	metodo de atualizar entidade
	@PutMapping("/{codigo}")
	public ResponseEntity<Endereco>update(@PathVariable Long codigo, @RequestBody Endereco endereco) {
		Endereco enderecoSalva = enderecoService.update(codigo, endereco);
		return ResponseEntity.ok(enderecoSalva);
	}
	
//	metodo de deletar
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(long id) {
		enderecoService.excluir(id);
	}
	
	
	public Endereco findById(long id) {
		return enderecoService.findById(id);
	}

	@GetMapping
	public Page<Endereco>listaAll(Pageable page){
		return enderecoRepository.findAll(page);
		
	}
}
