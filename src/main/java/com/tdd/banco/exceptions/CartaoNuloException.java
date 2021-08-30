package com.tdd.banco.exceptions;

public class CartaoNuloException extends Exception {

	private static final long serialVersionUID = 2166597211121898351L;
	
	public CartaoNuloException(String msg) {
		super(msg);
	}

}
