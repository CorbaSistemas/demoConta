package com.example.demoConta.domain.conta.interfaces.controller;

import com.example.demoConta.domain.conta.model.entidade.Conta;
import com.example.demoConta.interfaces.dto.ContaRequestDTO;
import com.example.demoConta.interfaces.dto.ContaResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author Dionízio Inácio on 24/05/2024
 */
@RestController
@RequestMapping("/api/conta")
@CrossOrigin(origins = "*")
@Tag(name = "Contas")
public interface IContaController {

	@GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<Page<ContaRequestDTO>> list(
		@PageableDefault(sort = "nomeContinente", direction = Sort.Direction.DESC) Pageable pageable);


	@GetMapping(value =  "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<ContaRequestDTO> getConta(@PathVariable Long id);


	@PostMapping( produces = APPLICATION_JSON_UTF8_VALUE)
	@Transactional
	ResponseEntity<Long> postConta( @RequestBody ContaResponseDTO contaResponseDTO);

	@GetMapping("/soma-valores")
	public double somaValores(@RequestParam("dataInicial") String dataInicial,
		@RequestParam("dataFinal") String dataFinal);

	@PostMapping
	public Conta cadastrarConta(@RequestBody Conta conta);

	@PutMapping("/{id}")
	public Conta atualizarConta(@PathVariable Long id, @RequestBody Conta conta);

	@PatchMapping("/{id}/situacao")
	public Conta alterarSituacao(@PathVariable Long id, @RequestParam("situacao") String situacao);

	@GetMapping("/a-pagar")
	public List<Conta> obterContasAPagar(@RequestParam("dataInicial") String dataInicial,
		@RequestParam("dataFinal") String dataFinal,
		@RequestParam(value = "descricao", required = false) String descricao);

	@GetMapping("/{id}")
	public Conta obterContaPorId(@PathVariable Long id);

	@PostMapping("/upload-csv")
	public void uploadCsv(@RequestParam("file") MultipartFile file);
}