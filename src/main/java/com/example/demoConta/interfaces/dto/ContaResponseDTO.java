package com.example.demoConta.interfaces.dto;

import com.example.demoConta.domain.conta.model.entidade.Conta;
import lombok.*;

import java.time.LocalDate;

/**
 * @author Dionízio Inácio  on 28/05/2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ContaResponseDTO {

	private Long id;
	private LocalDate dataVencimento;
	private LocalDate dataPagamento;
	private double valor;
	private String descricao;
	private String situacao;

	public static ContaResponseDTO parse(final Conta conta) {

		if (conta == null) {
			return null;
		}

		return ContaResponseDTO.builder()
			.id(conta.getId())
			.dataVencimento(conta.getDataVencimento())
			.dataPagamento(conta.getDataPagamento())
			.valor(conta.getValor())
			.descricao(conta.getDescricao())
			.situacao(conta.getSituacao())
			.build();
	}
}
