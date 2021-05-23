package com.atividade.main.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.atividade.main.model.Editora;
import com.atividade.main.model.Pagamento;
import com.atividade.main.repository.PagamentoRepository;
import com.atividade.main.service.exception.PagamentoExistException;



@Service
public class PagamentoService {
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	
	public Pagamento save(Pagamento pagamento) {
		if(isPagamentoExist(pagamento.getDescricao())) {
			throw new PagamentoExistException();
		}
		return pagamentoRepository.save(pagamento);
	}
	
	
	public Pagamento update(Long codigo ,Pagamento pagamento) {
		Pagamento pagamentoSalvo = pagamentoRepository.findById(codigo).get();
		if (pagamentoSalvo==null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(pagamento, pagamentoSalvo,"pagamentoId");
		pagamentoRepository.save(pagamentoSalvo);
		return pagamentoSalvo;
	}
	
	public void delete(long id) {
		pagamentoRepository.deleteById(id);
	}
	
	public Pagamento PagamentofindById(long id) {
		Optional<Pagamento> Pagamento=pagamentoRepository.findById(id);
		return Pagamento.get();
	}
	
	public boolean isPagamentoExist(String descricao) {
		Pagamento pagamento=pagamentoRepository.findPagamentoByDescricao(descricao);
		return pagamento == null ? false : true;
		
	}
	
}
