package com.tdd.banco.services;

import com.tdd.banco.exceptions.CartaoLimiteNaoInformado;
import com.tdd.banco.exceptions.CartaoSemContaVinculada;
import com.tdd.banco.exceptions.ContaLimiteDeCartoesAtingido;
import com.tdd.banco.exceptions.EmprestimoException;
import com.tdd.banco.exceptions.GeradorDeContaSemClienteException;
import com.tdd.banco.models.Cartao;
import com.tdd.banco.models.Cliente;
import com.tdd.banco.models.Conta;
import com.tdd.banco.models.Emprestimo;

public class BancoService {
	
	public Conta gerarContaBancaria(Cliente cliente) throws GeradorDeContaSemClienteException {
		// conta sem titular
		if(cliente == null) {
			throw new GeradorDeContaSemClienteException("N�o foi informado o cliente");
		}

		// Acessar banco de dados e reornar numero da conta
		int n2 = (int) (Math.random() * 5461);
		String numeroGerado =  Integer.toString(n2);
		
		Conta conta = new Conta(numeroGerado, "1548", cliente);
		
		// inserir no banco de dados conta gerada
		
		return conta;
	}

	public Cartao gerarCartao(Conta conta, double limite) throws CartaoSemContaVinculada, CartaoLimiteNaoInformado, ContaLimiteDeCartoesAtingido {
		// Cart�o sem conta vinculada
		if(conta == null) {
			throw new CartaoSemContaVinculada("Cart�o deve ter um conta vinculada");
		}
		// LIMITE Informado pelo cliente
		if(limite <= 0.0) {
			throw new CartaoLimiteNaoInformado("O cliente deve informar o limite");
		}
		if(conta.getCartoes().size() == 5) {
			throw new ContaLimiteDeCartoesAtingido("Limite de cart�es atingido");
		}
		
		Cartao c = new Cartao(conta.getCliente().getNome(), conta, limite);
		
		conta.getCartoes().add(c);
		
		// Banco de dados = Inserir dados
		
		return c;
	}
	
	public Emprestimo gerarEmprestimo(Conta conta, double valor, int parcelas) throws EmprestimoException {
		
		if(conta == null) {
			throw new EmprestimoException("Conta n�o pode ser nula");
		}
		if(valor < 150.0) {
			throw new EmprestimoException("Valor minimo necessario");
		}
		if(conta.getEmprestimos().size() >= 2) {
			throw new EmprestimoException("Limite de emprestimos atingido");
		}
		
		Emprestimo e = new Emprestimo();
		
		// Associar emprestimo a conta
//		conta.getEmprestimos().add(e);
		
		// Criar teste deve retornar objeto do tipo emprestimo
		return e;
	}
	
}
