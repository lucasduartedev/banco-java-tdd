package com.tdd.banco.models;

import java.util.Date;

public class Pagamento {
	
	private Long id;
	
	private Object tipo;
	
	private double valor;
	
	private Date data;
	
	private Cliente cliente;
	
	// Contrutores
	public Pagamento() {
	}

	public Pagamento(Object tipo, double valor, Date data, Cliente cliente) {
		this.tipo = tipo;
		this.valor = valor;
		this.data = data;
		this.cliente = cliente;
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Object getTipo() {
		return tipo;
	}

	public void setTipo(Object tipo) {
		this.tipo = tipo;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
