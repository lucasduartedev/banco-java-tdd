package com.tdd.banco.exceptions;

public class ContaSaqueComValorNegativoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ContaSaqueComValorNegativoException(String msg) {
		super(msg);
	}

}
