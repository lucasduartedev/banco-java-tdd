package com.tdd.banco.models;

import java.util.Date;

public class Emprestimo {
	
	private Long id;
	
	private Conta conta;
	
	private double valor;
	
	private Date data;
	
	private int parcelas;

	// Construtores
	public Emprestimo() {
	}
	
	public Emprestimo(Long id, Conta conta, double valor, Date data, int parcelas) {
		this.id = id;
		this.conta = conta;
		this.valor = valor;
		this.data = data;
		this.parcelas = parcelas;
	}

	public Emprestimo(Conta conta, double valor, Date data, int parcelas) {
		this.conta = conta;
		this.valor = valor;
		this.data = data;
		this.parcelas = parcelas;
	}
	
	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getParcelas() {
		return parcelas;
	}

	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}

}
