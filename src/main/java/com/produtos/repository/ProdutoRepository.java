package com.produtos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.produtos.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Query(value = "select p from Produto p where upper(trim(p.nome)) like %?1% ") 
	List<Produto> findByNome(String nome);

}
