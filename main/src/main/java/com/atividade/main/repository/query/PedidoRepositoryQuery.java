package com.atividade.main.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atividade.main.model.Pedido;
import com.atividade.main.repository.dto.BookCart;
import com.atividade.main.repository.filter.PedidoFilter;

public interface PedidoRepositoryQuery {
	
	public Page<Pedido> filter(PedidoFilter pedidoFilter, Pageable page);
	
	public Page<BookCart> listaBookByPedido(Long id, Pageable page);

}
