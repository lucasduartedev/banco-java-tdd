package com.tdd.banco.exceptions;

public class GeradorDeContaSemClienteException extends Exception {

	private static final long serialVersionUID = -2336271864569022289L;
	
	public GeradorDeContaSemClienteException(String msg) {
		super(msg);
	}

}
