package com.victorambiel.agendamento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;


import org.junit.jupiter.api.Test;

import com.victorambiel.agendamento.model.Agendamento;
import com.victorambiel.agendamento.util.QuantosDias;

public class UtilTest {

	@Test
	public void diferencaDeDiasTest() {
		LocalDate dataAgendamento = LocalDate.now().plusDays(20);
		
		Agendamento agendamento = new Agendamento();
		agendamento.setDataDaTransferencia(dataAgendamento);
		agendamento.setDataDeAgendamento(LocalDateTime.now());

		assertEquals(new QuantosDias().getQuantosDiasAgendamento(agendamento), new Long(20));
	}
	
}
