package com.victorambiel.agendamento.validation;

public class ErroMensagemDto {

	private String mensagem;

	public ErroMensagemDto(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
