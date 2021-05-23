package com.atividade.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atividade.main.model.BookPedido;

@Repository
public interface BookPedidoRepository  extends JpaRepository<BookPedido, Long>{

}
