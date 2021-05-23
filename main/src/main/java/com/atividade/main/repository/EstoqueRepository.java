package com.atividade.main.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atividade.main.model.Book;
import com.atividade.main.model.Estoque;




@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long>{ 

			
}
