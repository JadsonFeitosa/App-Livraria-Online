package com.atividade.main.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atividade.main.model.Categoria;



@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{ 
	
	public Categoria findCategoriaByDescricao(String descricao);

		
}
