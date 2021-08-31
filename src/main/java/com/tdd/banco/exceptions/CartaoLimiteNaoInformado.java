package com.tdd.banco.exceptions;

public class CartaoLimiteNaoInformado extends Exception {

	private static final long serialVersionUID = 4115764773064524645L;
	
	public CartaoLimiteNaoInformado(String msg) {
		super(msg);
	}

}
