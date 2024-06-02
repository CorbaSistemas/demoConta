package com.example.demoConta.controller;

/**
 * @author <seu nome> on 02/06/2024
 */
import com.example.demoConta.domain.conta.service.ContaService;
import com.example.demoConta.interfaces.controller.ContaController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@WebAppConfiguration
@Transactional
public class ContaControllerTest {
	@InjectMocks
	private ContaController contaController;

	@Mock
	private ContaService contaService;

	private MockMvc mockMvc;

	public ContaControllerTest() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(contaController).build();
	}

	@Test
	public void testSomaValores() throws Exception {
		when(contaService.somarValoresPorPeriodo(LocalDate.now(), LocalDate.now())).thenReturn(300.0);

		this.mockMvc.perform(get("/contas/soma-valores")
				.param("dataInicial", LocalDate.now().toString())
				.param("dataFinal", LocalDate.now().toString()))
			.andExpect(status().isOk())
			.andExpect(content().string("300.0"));
	}
}
