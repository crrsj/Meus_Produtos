package com.produtos.entity;

import com.produtos.dto.ProdutoDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;	
	private String nome;
	private String descricao;
	private Integer quantidade;	
	private Double preco;
	

	public Produto(ProdutoDto produtoDto) {
		this.id = produtoDto.id();
		this.nome = produtoDto.nome();
		this.descricao = produtoDto.descricao();
		this.quantidade = produtoDto.quantidade();
		this.preco = produtoDto.preco();
	}


	public void atualize(ProdutoDto produtoDto) {
	 if(produtoDto.nome()!=null) {
		 this.nome = produtoDto.nome();
	 }
	 if(produtoDto.descricao()!= null) {
		 this.descricao = produtoDto.descricao();
	 }
	 if(produtoDto.quantidade()!= null) {
		 this.quantidade = produtoDto.quantidade();
	 }
		 
		 if(produtoDto.preco()!= null) {
			 this.preco = produtoDto.preco();
		 }
	 
	 
	
}
}