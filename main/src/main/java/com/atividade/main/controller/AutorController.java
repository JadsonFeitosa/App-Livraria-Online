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
import com.atividade.main.model.Autor;
import com.atividade.main.repository.dto.AutorDTO;
import com.atividade.main.service.AutorService;

@RestController
@RequestMapping("/autor")
public class AutorController {

	@Autowired
	private AutorService autorService;

	@Autowired
	private ApplicationEventPublisher publisher;

//	metodo de salvar autor
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Autor> save(@Valid @RequestBody Autor autor, HttpServletResponse response) {
		Autor autorSalvo = autorService.save(autor);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, autorSalvo.getAutorId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(autorSalvo);
	}

//	metodo de atualizar entidade
	@PutMapping("/{codigo}")
	public ResponseEntity<Autor> update(@PathVariable Long codigo, @RequestBody Autor autor) {
		Autor autorSalvo = autorService.update(codigo, autor);
		return ResponseEntity.ok(autorSalvo);
	}

//	metodo de deletar
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long codigo) {
		autorService.delete(codigo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Autor> findById(@PathVariable Long codigo) {
		Autor autor = autorService.findById(codigo);
		return autor != null ? ResponseEntity.ok(autor) : ResponseEntity.notFound().build();
	}

	@GetMapping("/buscanome/{nome}")
	public ResponseEntity<Autor> getAutorPorNome(@PathVariable String nome) {
		Autor autor = autorService.getAutorPorNome(nome);
		return autor != null ? ResponseEntity.ok(autor) : ResponseEntity.notFound().build();
	}

	@GetMapping
	public Page<AutorDTO> getListaOrdenadaAsedente(Pageable page) {
		return autorService.getListaOrdenadaAsedente(page);
	}

}
