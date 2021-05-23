package com.atividade.main.repository.filter;

import java.math.BigDecimal;
import java.util.Date;

import com.atividade.main.model.Pagamento;
import com.atividade.main.model.StatusPedido;
import com.atividade.main.model.Usuario;

import lombok.Data;

@Data
public class PedidoFilter {
	
	private long pedidoID;
	private Usuario user;
	private BigDecimal total;
	private Date dataCriacao;
	private StatusPedido status;
	private Pagamento pagamento;
	private Date dataFechamento;

}
