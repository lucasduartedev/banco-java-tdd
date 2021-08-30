package com.tdd.banco.models;

import java.io.Serializable;
import java.util.Date;

public class Cartao implements Serializable {

	private static final long serialVersionUID = 7604888052685029853L;

	private Long id;
	
	private String nome;
	
	private Conta conta;
	
	private double limite;
	
	private Date dataCriacao;

	// Contrutores
	public Cartao() {}
	
	public Cartao(Long id, String nome, Conta conta, double limite) {
		this.id = id;
		this.nome = nome;
		this.conta = conta;
		this.limite = limite;
		dataCriacao = new Date();
	}

	public Cartao(String nome, Conta conta, double limite) {
		this.nome = nome;
		this.conta = conta;
		this.limite = limite;
		dataCriacao = new Date();
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

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
