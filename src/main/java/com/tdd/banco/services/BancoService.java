package com.tdd.banco.services;

import com.tdd.banco.exceptions.CartaoNuloException;
import com.tdd.banco.exceptions.CartaoSemContaVinculada;
import com.tdd.banco.exceptions.GeradorDeContaSemClienteException;
import com.tdd.banco.models.Cartao;
import com.tdd.banco.models.Cliente;
import com.tdd.banco.models.Conta;

public class BancoService {
	
	public Conta gerarContaBancaria(Cliente cliente) throws GeradorDeContaSemClienteException {
		// conta sem titular
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

	public Cartao gerarCartao(Cartao cartao, Conta conta) throws CartaoSemContaVinculada, CartaoNuloException {
		// Cartão sem conta vinculada
		if(conta == null) {
			throw new CartaoSemContaVinculada("Cartão deve ter um conta vinculada");
		}
		if(cartao == null) {
			throw new CartaoNuloException("Cartão nao deve ser nulo");
		}
		
		return cartao;
	}
	
}
