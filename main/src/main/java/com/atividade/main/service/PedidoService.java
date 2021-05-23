package com.atividade.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atividade.main.model.BookPedido;
import com.atividade.main.model.Pedido;
import com.atividade.main.repository.PedidoRepository;
import com.atividade.main.repository.dto.BookCart;
import com.atividade.main.repository.dto.PedidoDTO;
import com.atividade.main.repository.filter.PedidoFilter;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BookPedidoService bookPedidoService;
	
	public PedidoDTO save(Pedido pedido) {
		
		List<BookPedido>listaBook = pedido.getListaBook();
		
		Pedido pedidoSalvo = pedidoRepository.save(pedido);
		PedidoDTO pedidoDTO = new PedidoDTO(pedidoSalvo);
		
		
		for (int i = 0; i < listaBook.size(); i++) {
			pedido.getListaBook().get(i).setPedidoId(pedidoSalvo);	
			bookPedidoService.save(pedido.getListaBook().get(i));
		}
		
		return pedidoDTO;
	}

	public Pedido update(Long codigo, Pedido pedido) {
		Pedido pedidoSalvo = pedidoRepository.findById(codigo).get();
		if (pedidoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(pedido, pedidoSalvo, "pedidoId");
		pedidoRepository.save(pedido);
		return pedidoSalvo;

	}

	public void excluir(long id) {
		pedidoRepository.deleteById(id);
	}

	public Pedido findById(long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.get();
	}

	public Page<PedidoDTO> getListaOrdenadaAsedente(PedidoFilter pedidoFilter, Pageable page) {
		return pedidoRepository.filter(pedidoFilter, page).map(e -> new PedidoDTO(e));
	}
	public Page<BookCart> listaBookByPedido(Long codigo,Pageable page){
		return pedidoRepository.listaBookByPedido(codigo, page);
	}

}
