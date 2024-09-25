package com.produtos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produtos.dto.ProdutoDto;
import com.produtos.entity.Produto;
import com.produtos.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto criarProduto(ProdutoDto produtoDto) {
		var criar = new Produto(produtoDto);
		return produtoRepository.save(criar);
	}
	
	public List<ProdutoDto>listarProdutos(){
		return produtoRepository.findAll().stream().
				map(ProdutoDto::new).toList();		
		
	}
	
	public Produto buscarPorId(Long id) {
		Optional<Produto> busca = produtoRepository.findById(id);
		return busca.get();
		
	}
	
	public Produto AtualizarProduto(ProdutoDto produtoDto,Long id) {
		var atualizar = new Produto(produtoDto);
		atualizar.setId(id);
		return produtoRepository.save(atualizar);
	}
	
	public void excluir(Long id) {
		produtoRepository.deleteById(id);
	}
	
	public List<Produto> buscarPorNome(String nome) {
	return	produtoRepository.findByNome(nome.trim().toUpperCase());
	}
	
	@Transactional
	public Produto atualizarCampo(ProdutoDto produtoDto,Long id) {			
		var atualizar =  produtoRepository.getReferenceById(id);		
	    atualizar.atualize(produtoDto);
		return produtoRepository.save(atualizar);
	}
}
