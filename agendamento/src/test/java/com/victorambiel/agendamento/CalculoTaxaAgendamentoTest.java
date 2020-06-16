package com.victorambiel.agendamento;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.victorambiel.agendamento.model.Agendamento;
import com.victorambiel.agendamento.operations.TipoDaTaxa;

public class CalculoTaxaAgendamentoTest {

	@Test
	public void calcularTaxaTipoATest() {
		Agendamento agendamento = new Agendamento();
		agendamento.setDataDaTransferencia(LocalDate.now());
		agendamento.setValorDaTransferencia(new BigDecimal(100));

		assertThat(new TipoDaTaxa(agendamento).encontrarTaxa(), Matchers.comparesEqualTo(new BigDecimal(6.00)));

	}
	
	@Test
	public void calcularTaxaTipoBTest() {
		Agendamento agendamento = new Agendamento();
		LocalDate dataAgendamento = LocalDate.now().plusDays(5);
		
		agendamento.setDataDaTransferencia(dataAgendamento);
		agendamento.setValorDaTransferencia(new BigDecimal(100));

		assertThat(new TipoDaTaxa(agendamento).encontrarTaxa(), Matchers.comparesEqualTo(new BigDecimal(60.00)));
	}
	
	@Test
	public void calcularTaxaTipoCAteVinteDiasTest() {
		Agendamento agendamento = new Agendamento();
		LocalDate dataAgendamento = LocalDate.now().plusDays(15);
		
		agendamento.setDataDaTransferencia(dataAgendamento);
		agendamento.setValorDaTransferencia(new BigDecimal(100));

		assertThat(new TipoDaTaxa(agendamento).encontrarTaxa(), Matchers.comparesEqualTo(new BigDecimal(8.00)));
	}
	
	@Test
	public void calcularTaxaTipoCAteTrintaDiasTest() {
		Agendamento agendamento = new Agendamento();
		LocalDate dataAgendamento = LocalDate.now().plusDays(25);
		
		agendamento.setDataDaTransferencia(dataAgendamento);
		agendamento.setValorDaTransferencia(new BigDecimal(100));

		assertThat(new TipoDaTaxa(agendamento).encontrarTaxa(), Matchers.comparesEqualTo(new BigDecimal(6.00)));
	}
	
	@Test
	public void calcularTaxaTipoCAteQuarentaDiasTest() {
		Agendamento agendamento = new Agendamento();
		LocalDate dataAgendamento = LocalDate.now().plusDays(35);
		
		agendamento.setDataDaTransferencia(dataAgendamento);
		agendamento.setValorDaTransferencia(new BigDecimal(100));

		assertThat(new TipoDaTaxa(agendamento).encontrarTaxa(), Matchers.comparesEqualTo(new BigDecimal(4.00)));
	}
	
	@Test
	public void calcularTaxaTipoCAcimaDeQuarentaDiasTest() {
		Agendamento agendamento = new Agendamento();
		LocalDate dataAgendamento = LocalDate.now().plusDays(45);
		
		agendamento.setDataDaTransferencia(dataAgendamento);
		agendamento.setValorDaTransferencia(new BigDecimal(200000));

		assertThat(new TipoDaTaxa(agendamento).encontrarTaxa(), Matchers.comparesEqualTo(new BigDecimal(4000)));
	}
	
}
