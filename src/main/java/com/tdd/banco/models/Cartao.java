package com.tdd.banco.models;

public class Cartao {
	
	private Long id;
	
	private String nome;
	
	private Conta conta;
	
	private double limite;

	// Contrutores
	public Cartao() {}
	
	public Cartao(Long id, String nome, Conta conta, double limite) {
		this.id = id;
		this.nome = nome;
		this.conta = conta;
		this.limite = limite;
	}

	public Cartao(String nome, Conta conta, double limite) {
		this.nome = nome;
		this.conta = conta;
		this.limite = limite;
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}
	
}
