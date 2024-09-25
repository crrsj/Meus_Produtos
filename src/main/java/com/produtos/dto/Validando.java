package com.produtos.dto;

import org.springframework.validation.FieldError;

public record Validando(String campo,String mensagem) {
  public Validando(FieldError erros) {
	  this(erros.getField(),erros.getDefaultMessage());
  }
}
