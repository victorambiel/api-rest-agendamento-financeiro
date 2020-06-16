package com.victorambiel.agendamento.operations;

import java.math.BigDecimal;

import com.victorambiel.agendamento.model.Agendamento;
import com.victorambiel.agendamento.util.QuantosDias;
import com.victorambiel.agendamento.validation.TaxaNaoAplicavelException;

public class CalculoTaxaC implements CalculoTaxa {

	/**
	 * Taxas regressivas conforme a quantidade de dias
	 */
	private static final BigDecimal TAXA_PORCENTAGEM_VINTE_DIAS = new BigDecimal("0.08");
	private static final BigDecimal TAXA_PORCENTAGEM_TRINTA_DIAS = new BigDecimal("0.06");
	private static final BigDecimal TAXA_PORCENTAGEM_QUARENTA_DIAS = new BigDecimal("0.04");
	private static final BigDecimal TAXA_PORCENTAGEM_ACIMA_QUARENTA_DIAS = new BigDecimal("0.02");
	private static final BigDecimal VALOR_SUPERIOR = new BigDecimal("100000");

	private Agendamento agendamento;

	public CalculoTaxaC(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	/**
	 * Taxa regressiva conforme a data de transferência
	 *  Acima de 10 até 20 dias da data de agendamento: 8%
	 *  Acima de 20 até 30 dias da data de agendamento: 6%
	 *	Acima de 30 até 40 dias da data de agendamento: 4%
	 *	Acima de 40 dias da data de agendamento e valor superior a 100.000: 2%
	 */
	public BigDecimal calcularTaxa() {
		Long quantosDias = QuantosDias.getQuantosDiasAgendamento(agendamento);
		BigDecimal resultadoTaxa = null;
		BigDecimal valorTransferencia = agendamento.getValorDaTransferencia();
		
		if (quantosDias <= 20) {
			resultadoTaxa = valorTransferencia.multiply(TAXA_PORCENTAGEM_VINTE_DIAS);
		} else if (quantosDias <= 30) {
			resultadoTaxa = valorTransferencia.multiply(TAXA_PORCENTAGEM_TRINTA_DIAS);
		} else if (quantosDias <= 40) {
			resultadoTaxa = valorTransferencia.multiply(TAXA_PORCENTAGEM_QUARENTA_DIAS);
		} else if ((quantosDias > 40) && (valorTransferencia.compareTo(VALOR_SUPERIOR) > 0)) {
			resultadoTaxa = valorTransferencia.multiply(TAXA_PORCENTAGEM_ACIMA_QUARENTA_DIAS);
		} else {
			throw new TaxaNaoAplicavelException();
		}
		
		return resultadoTaxa;
	}

}
