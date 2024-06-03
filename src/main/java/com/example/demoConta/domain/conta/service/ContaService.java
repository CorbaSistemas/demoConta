package com.example.demoConta.domain.conta.service;

import com.example.demoConta.infra.configuration.Util;
import com.example.demoConta.domain.conta.interfaces.repository.IContaRepository;
import com.example.demoConta.domain.conta.interfaces.service.IContaService;
import com.example.demoConta.domain.conta.model.entidade.Conta;
import com.example.demoConta.infra.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

import java.io.IOException;


/**
 * @author Dionízio Inácio on 28/05/2024
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContaService implements IContaService {

	private final IContaRepository contaRepository;

	public Page<Conta> findAll(final Pageable pageable) {

		log.info("{} Buscando todos registros.", Util.LOG_PREFIX);
		return contaRepository.findAll(pageable);
	}

	@Transactional
	public Long save(final Conta conta) {

		log.info("{} Cadastrando novo registro.", Util.LOG_PREFIX);
		try {
			return contaRepository.save(conta)
				.getId();
		} catch (Exception e) {
			throw new BusinessException("Erro ao realizar cadastro", e);
		}
	}

	public Conta getById(final Long id) {
		log.info("{} Buscando um determinado registro.", Util.LOG_PREFIX);
		return contaRepository.findById(id)
			.orElseThrow(() -> new BusinessException("Não foi encontrado registro para esses ID: " + id));
	}



	public double somarValoresPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
		List<Conta> contas = contaRepository.findContasByPeriodo(dataInicial, dataFinal);
		return contas.stream()
			.mapToDouble(Conta::getValor)
			.sum();
	}

	@Transactional
	public Conta cadastrarConta(Conta conta) {
		return contaRepository.save(conta);
	}


	public Conta atualizarConta(Long id, Conta contaAtualizada) {
		return contaRepository.findById(id)
			.map(conta -> {
				conta.setDataVencimento(contaAtualizada.getDataVencimento());
				conta.setDataPagamento(contaAtualizada.getDataPagamento());
				conta.setValor(contaAtualizada.getValor());
				conta.setDescricao(contaAtualizada.getDescricao());
				conta.setSituacao(contaAtualizada.getSituacao());
				return contaRepository.save(conta);
			})
			.orElseThrow(() -> new RuntimeException("Conta não encontrada"));
	}

	public Conta alterarSituacao(Long id, String novaSituacao) {
		return contaRepository.findById(id)
			.map(conta -> {
				conta.setSituacao(novaSituacao);
				return contaRepository.save(conta);
			})
			.orElseThrow(() -> new RuntimeException("Conta não encontrada"));
	}

	public List<Conta> obterContasAPagar(LocalDate dataInicial, LocalDate dataFinal, String descricao) {
		return contaRepository.findByDataVencimentoBetweenAndDescricaoContaining(dataInicial, dataFinal, descricao);
	}

	public Conta obterContaPorId(Long id) {
		return contaRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Conta não encontrada"));
	}

	public void carregarDadosCsv(MultipartFile file) {


		try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				Conta conta = new Conta();
				conta.setDataVencimento(LocalDate.parse(data[0]));
				conta.setDataPagamento(LocalDate.parse(data[1]));
				conta.setValor(Double.parseDouble(data[2]));
				conta.setDescricao(data[3]);
				conta.setSituacao(data[4]);
				contaRepository.save(conta);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}