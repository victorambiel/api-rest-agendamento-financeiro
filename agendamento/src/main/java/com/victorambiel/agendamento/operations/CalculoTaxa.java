package com.victorambiel.agendamento.operations;

import java.math.BigDecimal;

/**
 * Interface para o calculo da taxa, cada tipo de taxa implementa a interface
 * e sobrescreve o método calcularTaxa conforme a própria regra de cálculo.
 * @author victor ambiel
 *
 */

public interface CalculoTaxa {
	public BigDecimal calcularTaxa();
}
