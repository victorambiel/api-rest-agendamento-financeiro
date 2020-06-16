package com.victorambiel.agendamento.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victorambiel.agendamento.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{

	/**
	 * Busca os registros por valor da transferencia e data da transferencia
	 */
	List<Agendamento> findByValorDaTransferenciaAndDataDaTransferencia(BigDecimal valorDaTransferencia, 
			LocalDate dataDaTransferencia);
	
	/**
	 * Busca os registros por valor da transferencia ou data da transferencia
	 */
	List<Agendamento> findByValorDaTransferenciaOrDataDaTransferencia(BigDecimal valorDaTransferencia, 
			LocalDate dataDaTransferencia);
}
