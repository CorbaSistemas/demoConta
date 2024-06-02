package com.example.demoConta.domain.conta.interfaces.repository;

import com.example.demoConta.domain.conta.model.entidade.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dionízio Inácio on 28/05/2024
 */
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
public interface IContaRepository extends JpaRepository<Conta, Long> {
	@Query("SELECT c FROM Conta c WHERE c.dataVencimento BETWEEN :dataInicial AND :dataFinal")
	List<Conta> findContasByPeriodo(@Param("dataInicial") LocalDate dataInicial,
		@Param("dataFinal") LocalDate dataFinal);

	List<Conta> findByDataVencimentoBetweenAndDescricaoContaining(LocalDate dataInicial, LocalDate dataFinal,
		String descricao);

}
