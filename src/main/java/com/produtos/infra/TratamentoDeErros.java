package com.produtos.infra;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.produtos.dto.MensagemDeErros;
import com.produtos.dto.Validando;

@ControllerAdvice
public class TratamentoDeErros {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<MensagemDeErros>idNaoEncontrado(){
		var erros = new MensagemDeErros(HttpStatus.NOT_FOUND,"ID não encontrado");
		return new ResponseEntity<>(erros,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?>validacao(MethodArgumentNotValidException exception){		
		var erros = exception.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(Validando::new).toList());
	}
}
