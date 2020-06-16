package com.victorambiel.agendamento.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DataDaTransferenciaInvalidaHandler {

	/*
	 * Handler da exception para datas inválidas
	 */
	
	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(DataDaTransferenciaInvalidaException.class)
	public ErroMensagemDto handler(DataDaTransferenciaInvalidaException exception) {
		ErroMensagemDto dto = new ErroMensagemDto("Data de transferência inválida!");
		
		return dto;
	}
	
}
