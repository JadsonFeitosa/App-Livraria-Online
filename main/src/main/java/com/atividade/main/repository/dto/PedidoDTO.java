package com.atividade.main.repository.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.atividade.main.model.Endereco;
import com.atividade.main.model.Pagamento;
import com.atividade.main.model.Pedido;
import com.atividade.main.model.Pedido_;
import com.atividade.main.model.StatusPedido;
import com.atividade.main.model.Usuario;

import lombok.Data;


@Data
public class PedidoDTO {
	
	
	private long pedidoID;

	private Usuario user;

	private BigDecimal total;
	

	private Date dataCriacao;

	private StatusPedido status;


	private Pagamento pagamento;

	private Date dataFechamento;
	
	public PedidoDTO(Pedido pedido) {
		BeanUtils.copyProperties(pedido, this, Pedido_.LISTA_BOOK);
	}
	

}
