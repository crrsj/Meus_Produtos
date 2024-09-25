package com.produtos.dto;

import com.produtos.entity.Produto;

public record ProdutoDto(
		Long id,
		String nome,
		String descricao,
		Integer quantidade,
		Double preco) {

	public ProdutoDto(Produto criar) {
	this(
			criar.getId(),
			criar.getNome(),
			criar.getDescricao(),
			criar.getQuantidade(), 
			criar.getPreco());
	}

}
