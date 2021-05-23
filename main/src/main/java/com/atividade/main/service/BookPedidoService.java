package com.atividade.main.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atividade.main.model.BookPedido;
import com.atividade.main.repository.BookPedidoRepository;

@Service
public class BookPedidoService {
	
	
	@Autowired
	private BookPedidoRepository bookPedidoRepository;
	

	public BookPedido save(BookPedido bookPedido) {
		return bookPedidoRepository.save(bookPedido);
	}
	
	public BookPedido update(Long codigo, BookPedido bookPedido) {
		BookPedido bookPedidoSalvo = bookPedidoRepository.findById(codigo).get();
		if (bookPedidoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(bookPedido, bookPedidoSalvo, "bookPedidoID");
		bookPedidoRepository.save(bookPedido);
		return bookPedidoSalvo;
	}
	
	public void delete(long id) {
		bookPedidoRepository.deleteById(id);
	}
	
	public BookPedido findById(long id) {
		Optional<BookPedido> bookPedido =bookPedidoRepository.findById(id);
		return bookPedido.get();
	}
	
	
	public Page<BookPedido> getListaOrdenadaAsedente(Pageable page){
        return bookPedidoRepository.findAll(page);
	}

	
	

}
