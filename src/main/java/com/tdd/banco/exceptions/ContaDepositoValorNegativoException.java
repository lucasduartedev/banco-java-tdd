package com.tdd.banco.exceptions;

public class ContaDepositoValorNegativoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ContaDepositoValorNegativoException(String msg) {
		super(msg);
	}

}
