package com.atividade.main.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atividade.main.model.Book;
import com.atividade.main.repository.query.BookRepositoryQuery;



@Repository
public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryQuery{ 

	
	//buscar por nome do titulo do book   
		public Book findBookByTitulo(String titulo);
		
//buscar por nome do isbn do book   
		public Book findBookByISBN(String ISBN);
		
		
}
