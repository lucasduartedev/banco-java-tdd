package com.tdd.banco.services;

import java.util.ArrayList;
import java.util.List;

import com.tdd.banco.exceptions.ContaException;
import com.tdd.banco.exceptions.EmprestimoException;
import com.tdd.banco.exceptions.GeradorDeContaSemClienteException;
import com.tdd.banco.models.Cartao;
import com.tdd.banco.models.Cliente;
import com.tdd.banco.models.Conta;
import com.tdd.banco.models.Emprestimo;

public class BancoService {
	
	public List<Double> gerarParcelas(double valor, int nParcelas, int desconto) {
		
		List<Double> p = new ArrayList<Double>();
		
		double valorParcela = valor / nParcelas;
		
		for(int i = 0; i < nParcelas; i++) {
			if(desconto == 0) {
				p.add(valorParcela);
			} else {
				p.add(valorParcela - (valorParcela / 100 * desconto));
			}
			
		}
		
		return p;
	}
	
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

	public Cartao gerarCartao(Conta conta, double limite) throws ContaException {
		// Cartão sem conta vinculada
		if(conta == null) {
			throw new ContaException("Cartão deve ter um conta vinculada");
		}
		// LIMITE Informado pelo cliente
		if(limite <= 0.0) {
			throw new ContaException("O cliente deve informar o limite");
		}
		if(conta.getCartoes().size() == 5) {
			throw new ContaException("Limite de cartões atingido");
		}
		
		Cartao c = new Cartao(conta.getCliente().getNome(), conta, limite);
		
		conta.getCartoes().add(c);
		
		// Banco de dados = Inserir dados
		
		return c;
	}
	
	public Emprestimo gerarEmprestimo(Conta conta, double valor, int parcelas) throws EmprestimoException {
		
		if(conta == null) {
			throw new EmprestimoException("Conta não pode ser nula");
		} else if(valor < 150.0) {
			throw new EmprestimoException("Valor minimo necessario");
		} else if(conta.getEmprestimos().size() >= 2) {
			throw new EmprestimoException("Limite de emprestimos atingido");
		}
		
		double juros = 8.9;
		
		Emprestimo e = new Emprestimo(valor, parcelas, juros);
		
		// Associar emprestimo a conta
		conta.getEmprestimos().add(e);
		
		return e;
	}
	
}
