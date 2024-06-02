package com.example.demoConta.domain.conta.model.entidade;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;

import com.example.demoConta.interfaces.dto.ContaRequestDTO;
import com.example.demoConta.interfaces.dto.ContaResponseDTO;
import lombok.*;

import java.time.LocalDate;

/**
 * @author Dionízio Inácio on 24/05/2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "conta")
public class Conta {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;
	@Column(name = "data_pagamento")
	private LocalDate dataPagamento;
	private double valor;
	private String descricao;
	private String situacao;

	public static Conta parse(final ContaResponseDTO dto) {

		if (dto == null) {
			return null;
		}

		return Conta.builder()
			.id(dto.getId())
			.dataVencimento(dto.getDataVencimento())
			.dataPagamento(dto.getDataPagamento())
			.valor(dto.getValor())
			.descricao(dto.getDescricao())
			.situacao(dto.getSituacao())
			.build();
	}

	public static Conta parse(final ContaRequestDTO dto) {

		if (dto == null) {
			return null;
		}

		return Conta.builder()
			.id(dto.getId())
			.dataVencimento(dto.getDataVencimento())
			.dataPagamento(dto.getDataPagamento())
			.valor(dto.getValor())
			.descricao(dto.getDescricao())
			.situacao(dto.getSituacao())
			.build();
	}
}
