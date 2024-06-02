package com.example.demoConta.interfaces.dto;

import com.example.demoConta.domain.conta.model.entidade.Conta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;

/**
 * @author Dionízio Inácio on 28/05/2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContaRequestDTO {

	private Long id;
	private LocalDate dataVencimento;
	private LocalDate dataPagamento;
	private double valor;
	private String descricao;
	private String situacao;

	public static ContaRequestDTO parse(final Conta conta) {

		if (conta == null) {
			return null;
		}

		return ContaRequestDTO.builder()
			.id(conta.getId())
			.dataVencimento(conta.getDataVencimento())
			.dataPagamento(conta.getDataPagamento())
			.valor(conta.getValor())
			.descricao(conta.getDescricao())
			.situacao(conta.getSituacao())
			.build();
	}
}
