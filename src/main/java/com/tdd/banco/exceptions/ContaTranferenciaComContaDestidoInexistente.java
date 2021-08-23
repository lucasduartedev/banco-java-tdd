package com.tdd.banco.exceptions;

public class ContaTranferenciaComContaDestidoInexistente extends Exception {

	private static final long serialVersionUID = -567795259205256591L;
	
	public ContaTranferenciaComContaDestidoInexistente(String msg) {
		super(msg);
	}

}
