package com.example.demoConta.interfaces.controller;

import com.example.demoConta.domain.conta.interfaces.controller.IContaController;
import com.example.demoConta.domain.conta.interfaces.service.IContaService;
import com.example.demoConta.domain.conta.model.entidade.Conta;
import com.example.demoConta.infra.adapters.security.util.Util;
import com.example.demoConta.interfaces.dto.ContaRequestDTO;
import com.example.demoConta.interfaces.dto.ContaResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Dionízio Inácio on 28/05/2024
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContaController implements IContaController {

	private IContaService service;
	@Override public ResponseEntity<Page<ContaRequestDTO>> list(Pageable pageable) {

		log.info("{} Listando todos os faturamentos", Util.LOG_PREFIX);
		final Page<ContaRequestDTO> faturamentos = service.findAll(pageable)
			.map(ContaRequestDTO::parse);
		return ResponseEntity.ok(faturamentos);
	}

	@Override public ResponseEntity<ContaRequestDTO> getConta(Long id) {

		log.info("{} Buscando Continente", Util.LOG_PREFIX);
		return ResponseEntity.ok(ContaRequestDTO.parse(service.getById(id)));
	}

	@Override public ResponseEntity<Long> postConta(
		ContaResponseDTO contaResponseDTO) {

		log.info("{} Realizando cadastro do Continente", Util.LOG_PREFIX);
		return ResponseEntity.ok(service.save(Conta.parse(
			contaResponseDTO)));
	}

	public double somaValores(@RequestParam("dataInicial") String dataInicial,
		@RequestParam("dataFinal") String dataFinal) {
		LocalDate inicio = LocalDate.parse(dataInicial);
		LocalDate fim = LocalDate.parse(dataFinal);
		return service.somarValoresPorPeriodo(inicio, fim);
	}

	public Conta cadastrarConta(@RequestBody Conta conta) {
		return service.cadastrarConta(conta);
	}

	public Conta atualizarConta(@PathVariable Long id, @RequestBody Conta conta) {
		return service.atualizarConta(id, conta);
	}

	public Conta alterarSituacao(@PathVariable Long id, @RequestParam("situacao") String situacao) {
		return service.alterarSituacao(id, situacao);
	}

	public List<Conta> obterContasAPagar(@RequestParam("dataInicial") String dataInicial,
		@RequestParam("dataFinal") String dataFinal,
		@RequestParam(value = "descricao", required = false) String descricao) {
		LocalDate inicio = LocalDate.parse(dataInicial);
		LocalDate fim = LocalDate.parse(dataFinal);
		return service.obterContasAPagar(inicio, fim, descricao != null ? descricao : "");
	}

	public Conta obterContaPorId(@PathVariable Long id) {
		return service.obterContaPorId(id);
	}

	public void uploadCsv(@RequestParam("file") MultipartFile file) {
		service.carregarDadosCsv(file);
	}
}