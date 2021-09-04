package com.tdd.banco.models;

import java.util.Date;

public class Emprestimo {
	
	private Long id;
	
	private Conta conta;
	
	private double valor;
	
	private int parcelas;
	
	private Date dataInicio;
	
	private double juros;

	// Construtores
	public Emprestimo() {
	}
	
	public Emprestimo(Long id, Conta conta, double valor, int parcelas, Date data, double juros) {
		this.id = id;
		this.conta = conta;
		this.valor = valor;
		this.parcelas = parcelas;
		this.dataInicio = data;
		this.juros = juros;
	}

	public Emprestimo(Conta conta, double valor, int parcelas, Date data, double juros) {
		this.conta = conta;
		this.valor = valor;
		this.parcelas = parcelas;
		this.dataInicio = data;
		this.juros = juros;
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
		return dataInicio;
	}

	public void setData(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public int getParcelas() {
		return parcelas;
	}

	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public double getJuros() {
		return juros;
	}

	public void setJuros(double juros) {
		this.juros = juros;
	}

}
