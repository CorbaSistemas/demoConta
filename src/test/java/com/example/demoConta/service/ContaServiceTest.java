package com.example.demoConta.service;

import com.example.demoConta.domain.conta.interfaces.repository.IContaRepository;
import com.example.demoConta.domain.conta.model.entidade.Conta;
import com.example.demoConta.domain.conta.service.ContaService;
import com.example.demoConta.infra.adapters.security.util.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


/**
 * @author Dionízio Inácio on 02/06/2024
 */
@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
public class ContaServiceTest {

	@InjectMocks
	private ContaService contaService;

	@Mock
	private IContaRepository contaRepository;

	private Conta conta;

	public ContaServiceTest() {
		MockitoAnnotations.openMocks(this);
	}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		conta = new Conta();
		conta.setId(1L);
		conta.setDataVencimento(LocalDate.now().plusDays(30));
		conta.setDataPagamento(null);
		conta.setValor(100.0);
		conta.setDescricao("Conta de teste");
		conta.setSituacao("Pendente");
	}

	private Conta getContaCreditoDefault() {
		return Conta.builder()
			.dataPagamento(Utils.getDataAtual().toLocalDate())
			.dataVencimento(Utils.getDataAtual().toLocalDate())
			.valor(100.00)
			.descricao("VENDA SHAMPOO")
			.situacao("A VISTA")
			.build();
	}

	private Conta getContaCredito2Default() {
		return Conta.builder()
			.dataPagamento(Utils.getDataAtual().toLocalDate())
			.dataVencimento(Utils.getDataAtual().toLocalDate())
			.valor(50.00)
			.descricao("VENDA PERFUME")
			.situacao("PAGA")
			.build();
	}


	@Test
	public void testCadastrarConta() {


		assertNotNull(contaService.cadastrarConta(getContaCredito2Default()));
	}

	@Test
	public void testSomarValoresPorPeriodo() {

		assertNotNull( contaRepository.findContasByPeriodo(LocalDate.now(), LocalDate.now()));
		assertEquals(80.0, contaService.somarValoresPorPeriodo(LocalDate.of(2024, 03, 01), LocalDate.now()));
	}

	@Test
	public void testAtualizarConta() {
		when(contaRepository.findById(anyLong())).thenReturn(Optional.of(conta));
		when(contaRepository.save(getContaCreditoDefault())).thenReturn(conta);

		conta.setDescricao("Conta atualizada");
		Conta contaAtualizada = contaService.atualizarConta(conta.getId(), conta);

		assertNotNull(contaAtualizada);
		assertEquals("Conta atualizada", contaAtualizada.getDescricao());
	}

	@Test
	public void testAlterarSituacaoConta() {
		when(contaRepository.findById(1l)).thenReturn(Optional.of(conta));
		when(contaRepository.save(getContaCredito2Default())).thenReturn(conta);

		Conta contaAlterada = contaService.alterarSituacao(conta.getId(), "Paga");

		assertNotNull(contaAlterada);
		assertEquals("Paga", contaAlterada.getSituacao());
	}

	@Test
	public void testObterListaContasAPagarComFiltro() {
		List<Conta> contas = new ArrayList<>();
		contas.add(conta);

		when(contaRepository.findByDataVencimentoBetweenAndDescricaoContaining(eq(LocalDate.now()), eq(LocalDate.now().plusDays(30)), any(String.class)))
			.thenReturn(contas);

		List<Conta> contasFiltradas = contaService.obterContasAPagar(LocalDate.now(), LocalDate.now().plusDays(30), "PENDENTE");

		assertNotNull(contasFiltradas);
		assertEquals(1, contasFiltradas.size());
	}

	@Test
	public void testObterContaPorId() {
		when(contaRepository.findById(anyLong())).thenReturn(Optional.of(conta));

		Conta contaEncontrada = contaService.obterContaPorId(conta.getId());

		assertNotNull(contaEncontrada);
		assertEquals(conta.getId(), contaEncontrada.getId());
	}

}