package com.produtos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.produtos.dto.ProdutoDto;
import com.produtos.service.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping
	public ResponseEntity<ProdutoDto>criarProduto(@RequestBody @Valid  ProdutoDto produtoDto){
		var criando = produtoService.criarProduto(produtoDto);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
				.buildAndExpand(criando.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDto(criando));
	}
	
	@GetMapping
	public ResponseEntity<List<ProdutoDto>>listarProdutos(){
		var listar = produtoService.listarProdutos();
		return ResponseEntity.ok(listar);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProdutoDto>buscarPorId(@PathVariable Long id){
		var busca = produtoService.buscarPorId(id);
		return ResponseEntity.ok().body(new ProdutoDto(busca));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ProdutoDto>atualizarProduto(@RequestBody @Valid ProdutoDto produtoDto,@PathVariable Long id){
		var atualizando = produtoService.AtualizarProduto(produtoDto, id);
		return ResponseEntity.ok().body(new ProdutoDto(atualizando));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void>excluir(@PathVariable Long id){
		produtoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("buscarProdutos")
	public ResponseEntity<List<ProdutoDto>>buscarPorNome(@RequestParam(name = "nome")String nome){
		var buscar = produtoService.buscarPorNome(nome).stream().map(ProdutoDto::new).toList();
		return ResponseEntity.ok(buscar);
	}
	
	@PatchMapping("atualizar/{id}")
	public ResponseEntity<ProdutoDto>atualizarCampo(@RequestBody ProdutoDto produtoDto,@PathVariable Long id){
		var atualize = produtoService.atualizarCampo(produtoDto,id);
		return ResponseEntity.ok().body(new ProdutoDto(atualize));
	}
}
