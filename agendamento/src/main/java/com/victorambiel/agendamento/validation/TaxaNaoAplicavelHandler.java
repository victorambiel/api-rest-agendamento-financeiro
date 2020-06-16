package com.victorambiel.agendamento.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TaxaNaoAplicavelHandler {
	
	/**
	 * Handler da exception para taxas não aplicáveis
	 */
	
	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(TaxaNaoAplicavelException.class)
	public ErroMensagemDto handler(TaxaNaoAplicavelException exception) {
		ErroMensagemDto dto = new ErroMensagemDto("Taxa não aplicável!");
		
		return dto;
	}
	
}
