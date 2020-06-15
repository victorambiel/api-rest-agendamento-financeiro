package com.victorambiel.agendamento.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Agendamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long contaDeOrigem;
	private Long contaDeDestino;
	private BigDecimal taxa;
	private BigDecimal valorDaTransferencia;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataDaTransferencia;
	
	private LocalDateTime dataDeAgendamento = LocalDateTime.now();

	public Agendamento() {
	}

	public Agendamento(Long contaDeOrigem, Long contaDeDestino, BigDecimal valorDaTransferencia, 
			BigDecimal taxa, LocalDate dataDaTransferencia) {
		super();
		this.contaDeOrigem = contaDeOrigem;
		this.contaDeDestino = contaDeDestino;
		this.valorDaTransferencia = valorDaTransferencia;
		this.taxa = taxa;
		this.dataDaTransferencia = dataDaTransferencia;
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

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

	public BigDecimal getValorDaTransferencia() {
		return valorDaTransferencia;
	}

	public void setValorDaTransferencia(BigDecimal valorDaTransferencia) {
		this.valorDaTransferencia = valorDaTransferencia;
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

}
