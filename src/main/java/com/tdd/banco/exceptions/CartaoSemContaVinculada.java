package com.tdd.banco.exceptions;

public class CartaoSemContaVinculada extends Exception {

	private static final long serialVersionUID = -5994945482167170048L;
	
	public CartaoSemContaVinculada(String msg) {
		super(msg);
	}

}
