package com.victorambiel.agendamento.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.victorambiel.agendamento.controller.dto.AgendamentoDto;
import com.victorambiel.agendamento.model.Agendamento;
import com.victorambiel.agendamento.repository.AgendamentoRepository;


@RestController
@RequestMapping(value = "/agendamento")
public class AgendamentoController {
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	/* Precisa de refatoração */
	@GetMapping
	public List<AgendamentoDto> listarAgendamentos(@RequestParam(required = false) BigDecimal valorDaTransferencia, 
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataDaTransferencia) {
		
		if (valorDaTransferencia == null && dataDaTransferencia == null) {
			List<Agendamento> agendamento = agendamentoRepository.findAll();
			return AgendamentoDto.converter(agendamento);
		} else if (valorDaTransferencia != null && dataDaTransferencia != null) {
			List<Agendamento> agendamento = agendamentoRepository.findByValorDaTransferenciaAndDataDaTransferencia(
					valorDaTransferencia, dataDaTransferencia);
			return AgendamentoDto.converter(agendamento);
		} else {
			List<Agendamento> agendamento = agendamentoRepository.findByValorDaTransferenciaOrDataDaTransferencia(
					valorDaTransferencia, dataDaTransferencia);
			return AgendamentoDto.converter(agendamento);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<AgendamentoDto> cadastrarAgendamento(@RequestBody AgendamentoDto agendamentoDto) {
		Agendamento agendamento = agendamentoDto.converter();
		agendamentoRepository.save(agendamento);
		return ResponseEntity.ok().build();
	}

}
