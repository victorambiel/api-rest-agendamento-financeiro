package com.victorambiel.agendamento.operations;

import java.math.BigDecimal;

import com.victorambiel.agendamento.model.Agendamento;
import com.victorambiel.agendamento.util.QuantosDias;

public class CalculoTaxaB implements CalculoTaxa {

	/**
	 * Taxa fixa de $12 a ser multiplicado pela quantidade de dias
	 */
	private static final BigDecimal TAXA_FIXA = new BigDecimal(12);

	private Agendamento agendamento;

	public CalculoTaxaB(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	/**
	 * Transferências até 10 dias da data de agendamento possuem uma taxa de $12 
	 * multiplicado pela quantidade de dias da data de agendamento até a data de transferência.
	 */
	public BigDecimal calcularTaxa() {
		BigDecimal multiplicador = BigDecimal.valueOf(QuantosDias.getQuantosDiasAgendamento(agendamento));
		BigDecimal resultadoTaxa = TAXA_FIXA.multiply(multiplicador);
		return resultadoTaxa;
	}

}
