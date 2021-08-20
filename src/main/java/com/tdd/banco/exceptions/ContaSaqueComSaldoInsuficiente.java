package com.tdd.banco.exceptions;

public class ContaSaqueComSaldoInsuficiente extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ContaSaqueComSaldoInsuficiente(String msg) {
		super(msg);
	}

}
