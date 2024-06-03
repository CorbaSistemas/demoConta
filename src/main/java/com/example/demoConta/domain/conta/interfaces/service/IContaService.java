package com.example.demoConta.domain.conta.interfaces.service;

import com.example.demoConta.domain.conta.model.entidade.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Dionízio Inácio on 24/05/2024
 */
public interface IContaService {

	@Transactional
	public Page<Conta> findAll(final Pageable pageable);

	public Long save(final Conta conta);

	public Conta getById(final Long id);

	public double somarValoresPorPeriodo(LocalDate dataInicial, LocalDate dataFinal);

	@Transactional
	public Conta cadastrarConta(Conta conta);

	public Conta atualizarConta(Long id, Conta contaAtualizada);

	public Conta alterarSituacao(Long id, String novaSituacao);

	public List<Conta> obterContasAPagar(LocalDate dataInicial, LocalDate dataFinal, String descricao);

	public Conta obterContaPorId(Long id);
	public void carregarDadosCsv(MultipartFile file);

	}
