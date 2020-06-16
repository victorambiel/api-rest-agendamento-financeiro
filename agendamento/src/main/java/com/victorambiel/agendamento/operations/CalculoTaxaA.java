package com.victorambiel.agendamento.operations;

import java.math.BigDecimal;

import com.victorambiel.agendamento.model.Agendamento;

public class CalculoTaxaA implements CalculoTaxa {
	/**
	 * Taxa fixa de $3 no caso de transferencia no mesmo dia
	 */
	private static final BigDecimal TAXA_FIXA = new BigDecimal(3);
	
	/**
	 * Taxa percentual de 3% sobre o valor a ser transferido no caso de transferencia
	 * no mesmo dia
	 */
	private static final BigDecimal TAXA_PORCENTAGEM = new BigDecimal("0.03");
	
	private Agendamento agendamento;
	
	public CalculoTaxaA(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	
	/**
	 * Transferências no mesmo dia do agendamento tem uma taxa de $3 mais 3% do valor a ser transferido.
	 */
	public BigDecimal calcularTaxa() {
		BigDecimal resultadoTaxa = agendamento.getValorDaTransferencia().multiply(TAXA_PORCENTAGEM).add(TAXA_FIXA);
		return resultadoTaxa;
	}

}
