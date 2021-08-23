package com.tdd.banco.exceptions;

public class ContaTransferenciaComValorAbaixoDoMinimo extends Exception {

	private static final long serialVersionUID = -2874617261619693817L;
	
	public ContaTransferenciaComValorAbaixoDoMinimo(String msg) {
		super(msg);
	}

}
