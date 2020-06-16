package com.victorambiel.agendamento.operations;

import java.math.BigDecimal;

import com.victorambiel.agendamento.model.Agendamento;
import com.victorambiel.agendamento.util.QuantosDias;

public class TipoDaTaxa {

	private static Long ZERO = 0l;
	private static Long DEZ = 10l;
	
	Agendamento agendamento;
	CalculoTaxa calculo;
	
	public TipoDaTaxa(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	
	/* Possivel aplicar Strategy??? */
	
	/**
	 * Encontra a taxa do agendamento com base na diferença de dias entre a data
	 * de agendamento e a data de transferencia
	 */
	public BigDecimal encontrarTaxa() {
		Long quantosDias = QuantosDias.getQuantosDiasAgendamento(agendamento);
		
		if (quantosDias.equals(ZERO)) {
			calculo = new CalculoTaxaA(agendamento);
			return calculo.calcularTaxa();
		} else if (quantosDias.compareTo(DEZ) < 0) {
			calculo = new CalculoTaxaB(agendamento);
			return calculo.calcularTaxa();
		} else {
			calculo = new CalculoTaxaC(agendamento);
			return calculo.calcularTaxa();
		}
	}
	
}
