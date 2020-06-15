package com.victorambiel.agendamento.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.victorambiel.agendamento.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{

	List<Agendamento> findByValorDaTransferenciaAndDataDaTransferencia(BigDecimal valorDaTransferencia, 
			LocalDate dataDaTransferencia);
	
	List<Agendamento> findByValorDaTransferenciaOrDataDaTransferencia(BigDecimal valorDaTransferencia, 
			LocalDate dataDaTransferencia);
}
