package com.tdd.banco.services;

import com.tdd.banco.exceptions.GeradorDeContaSemClienteException;
import com.tdd.banco.models.Cliente;
import com.tdd.banco.models.Conta;

public class BancoService {
	
	public Conta gerarContaBancaria(Cliente cliente) throws GeradorDeContaSemClienteException {
		// Não pode gerar conta sem um titular
		if(cliente == null) {
			throw new GeradorDeContaSemClienteException("Não foi informado o cliente");
		}

		// Acessar banco de dados e reornar numero da conta
		int n2 = (int) (Math.random() * 5461);
		String numeroGerado =  Integer.toString(n2);
		
		Conta conta = new Conta(numeroGerado, "1548", cliente);
		
		// inserir no banco de dados conta gerada
		
		return conta;
	}
	
}
