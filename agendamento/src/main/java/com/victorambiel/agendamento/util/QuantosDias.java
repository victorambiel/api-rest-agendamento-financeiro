package com.victorambiel.agendamento.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.victorambiel.agendamento.model.Agendamento;
import com.victorambiel.agendamento.validation.DataDaTransferenciaInvalidaException;

public class QuantosDias {

	/**
	 * Retorna a diferença de dias entre a data de agendamento e a data de transferencia
	 * @param agendamento
	 * @return
	 */
	public static Long getQuantosDiasAgendamento(Agendamento agendamento) {
		Long diferencaEmDias = ChronoUnit.DAYS.between(LocalDate.now(), agendamento.getDataDaTransferencia());
		verificarDiferencaEmDias(diferencaEmDias);
		return diferencaEmDias;
	}
	
	/**
	 * Verifica se a diferença em dias é valida, se por acaso foi informado uma
	 * data no passado
	 * @param diferencaEmDias
	 */
	private static void verificarDiferencaEmDias(Long diferencaEmDias) {
		if (diferencaEmDias < 0) {
			throw new DataDaTransferenciaInvalidaException();
		}
	}
	
}
