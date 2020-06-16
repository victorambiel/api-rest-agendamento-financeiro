package com.victorambiel.agendamento.controller;

import java.math.BigDecimal;
import java.net.URI;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.victorambiel.agendamento.controller.dto.AgendamentoDto;
import com.victorambiel.agendamento.model.Agendamento;
import com.victorambiel.agendamento.operations.TipoDaTaxa;
import com.victorambiel.agendamento.repository.AgendamentoRepository;

/**
 * Classe de controle do agendamento.
 * Recebe as requisições de GET e retorna a lista de agendamentos
 * Recebe as requisições de POST e retorna o agendamento inserido
 * @author victor ambiel
 *
 */

@RestController
@RequestMapping(value = "/agendamento")
public class AgendamentoController {
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	/**
	 * Retorna a lista de agendamentos conforme os parametros recebidos. Os filtros podem ser
	 * a data da transferencia e/ou o valor da transferencia.
	 *  */
	@GetMapping
	public List<AgendamentoDto> listarAgendamentos(@RequestParam(required = false) BigDecimal valorDaTransferencia, 
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataDaTransferencia) {
		
		// Nenhum parametro foi passado, então busca todos os registros
		if (valorDaTransferencia == null && dataDaTransferencia == null) {
			List<Agendamento> agendamento = agendamentoRepository.findAll();
			return AgendamentoDto.converter(agendamento);
			
		// Filtra pela data de transferencia e pelo valor da transferencia	
		} else if (valorDaTransferencia != null && dataDaTransferencia != null) {
			List<Agendamento> agendamento = agendamentoRepository.findByValorDaTransferenciaAndDataDaTransferencia(
					valorDaTransferencia, dataDaTransferencia);
			return AgendamentoDto.converter(agendamento);
		
		/**
		 *  Filtra somente por um parametro, data de transferencia ou valor da transferencia, dependendo do que
		 *  foi passado por parametro.
		 */
			
		} else {
			List<Agendamento> agendamento = agendamentoRepository.findByValorDaTransferenciaOrDataDaTransferencia(
					valorDaTransferencia, dataDaTransferencia);
			return AgendamentoDto.converter(agendamento);
		}
	}
	
	/**
	 * Recebe a requisição de agendamento de uma transferencia
	 * @param agendamentoDto mapeia os dados da requisição com a classe de modelo de agendamento
	 * @param uriBuilder constroi uri para devolver o agendamento inserido
	 * @return o objeto de agendamento criado
	 * @throws Exception 
	 */
	
	@PostMapping
	@Transactional
	public ResponseEntity<AgendamentoDto> cadastrarAgendamento(@RequestBody AgendamentoDto agendamentoDto, 
			UriComponentsBuilder uriBuilder) {
		
		// Chama o método para converter o objeto agendamentoDto para um objeto da classe Agendamento
		Agendamento agendamento = agendamentoDto.converter();
		
		/**
		 *  Cria um objeto de TipoDaTaxa e chama o método encontrarTaxa para calcular
		 *  o valor da taxa com base na data da transferencia e insere esse valor no
		 *  campo de taxa no agendamento
		 */
		agendamento.setTaxa(new TipoDaTaxa(agendamento).encontrarTaxa());
		
		agendamentoRepository.save(agendamento);
		
		URI uri = uriBuilder.path("/agendamento/{id}").buildAndExpand(agendamento.getId()).toUri();
		return ResponseEntity.created(uri).body(new AgendamentoDto(agendamento));
	}

}
