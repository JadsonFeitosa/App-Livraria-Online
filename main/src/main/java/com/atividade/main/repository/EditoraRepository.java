package com.atividade.main.repository;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.atividade.main.model.Editora;
import com.atividade.main.repository.query.EditoraRepositoryQuery;



@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long>, EditoraRepositoryQuery{ 

//		buscar por nome da editora   
		public Editora findEditoraByNome(String nome);
		
//		buscar por CNPJ
		public Editora findEditoraByCNPJ(String cnpj);
		
//		retorna uma listapaginadar ordenada por nome
		public List<Editora>findAllByNome(String nome, Pageable pagina);

}
