package com.example.demoConta.infra.csv;

/**
 * @author Dionízio Inácio on 24/05/2024
 */
public class CsvPais {

	private String nome;

	public CsvPais(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "CsvPais{nome='" + nome  + "\'}";
	}
}
