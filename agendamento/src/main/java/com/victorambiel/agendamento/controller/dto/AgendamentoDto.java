package com.victorambiel.agendamento.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;

import com.victorambiel.agendamento.model.Agendamento;

public class AgendamentoDto {

	private Long id;
	private Long contaDeOrigem;
	private Long contaDeDestino;
	private BigDecimal valorDaTransferencia;
	private BigDecimal taxa;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataDaTransferencia;
	
	private LocalDateTime dataDeAgendamento = LocalDateTime.now();

	public AgendamentoDto() {
	}
	
	public AgendamentoDto(Agendamento agendamento) {
		this.id = agendamento.getId();
		this.contaDeOrigem = agendamento.getContaDeOrigem();
		this.contaDeDestino = agendamento.getContaDeDestino();
		this.valorDaTransferencia = agendamento.getValorDaTransferencia();
		this.taxa = agendamento.getTaxa();
		this.dataDaTransferencia = agendamento.getDataDaTransferencia();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContaDeOrigem() {
		return contaDeOrigem;
	}

	public void setContaDeOrigem(Long contaDeOrigem) {
		this.contaDeOrigem = contaDeOrigem;
	}

	public Long getContaDeDestino() {
		return contaDeDestino;
	}

	public void setContaDeDestino(Long contaDeDestino) {
		this.contaDeDestino = contaDeDestino;
	}

	public BigDecimal getValorDaTransferencia() {
		return valorDaTransferencia;
	}

	public void setValorDaTransferencia(BigDecimal valorDaTransferencia) {
		this.valorDaTransferencia = valorDaTransferencia;
	}
	
	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

	public LocalDate getDataDaTransferencia() {
		return dataDaTransferencia;
	}

	public void setDataDaTransferencia(LocalDate dataDaTransferencia) {
		this.dataDaTransferencia = dataDaTransferencia;
	}

	public LocalDateTime getDataDeAgendamento() {
		return dataDeAgendamento;
	}

	public void setDataDeAgendamento(LocalDateTime dataDeAgendamento) {
		this.dataDeAgendamento = dataDeAgendamento;
	}
	
	public Agendamento converter() {
		return new Agendamento(contaDeOrigem, contaDeDestino, valorDaTransferencia, taxa, dataDaTransferencia);
	}
	
	public static List<AgendamentoDto> converter(List<Agendamento> agendamento) {
		return agendamento.stream().map(AgendamentoDto::new).collect(Collectors.toList());
	}

}
