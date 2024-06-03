package com.example.demoConta.service;

import com.example.demoConta.domain.conta.interfaces.repository.IContaRepository;
import com.example.demoConta.domain.conta.interfaces.service.IContaService;
import com.example.demoConta.domain.conta.model.entidade.Conta;
import com.example.demoConta.domain.conta.service.ContaService;
import com.example.demoConta.infra.adapters.security.util.Util;
import com.example.demoConta.testUtil.utils.TestUtils;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


/**
 * @author Dionízio Inácio on 02/06/2024
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ContaServiceTest {

	@InjectMocks
	private ContaService contaService;

	@Mock
	private IContaRepository contaRepository;

	public ContaServiceTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Before
	public void init() {
		TestUtils.mockSecurityContextHolder();
	}

	private Conta getContaCreditoDefault() {
		return Conta.builder()
			.dataPagamento(LocalDate.of(2024, 6, 3))
			.dataVencimento(LocalDate.of(2024, 6, 3))
			.valor(100.00)
			.descricao("VENDA SHAMPOO")
			.situacao("A VISTA")
			.build();
	}

	private Conta getContaCredito2Default() {
		return Conta.builder()
			.dataPagamento(LocalDate.of(2024, 6, 3))
			.dataVencimento(LocalDate.of(2024, 6, 3))
			.valor(50.00)
			.descricao("VENDA PERFUME")
			.situacao("A VISTA")
			.build();
	}

	@Test
	public void testCadastrarConta() {

		assertNotNull(contaService.cadastrarConta(getContaCreditoDefault()));
		assertNotNull(contaService.save(getContaCredito2Default()));
	}


	@Test
	public void testSomarValoresPorPeriodo() {

		assertNotNull( contaRepository.findContasByPeriodo(LocalDate.now(), LocalDate.now()));
		assertEquals(150.0, contaService.somarValoresPorPeriodo(LocalDate.now(), LocalDate.now()));
	}
}