package com.tdd.banco.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.tdd.banco.exceptions.ContaDepositoValorNegativoException;
import com.tdd.banco.exceptions.ContaSaqueComSaldoInsuficiente;
import com.tdd.banco.exceptions.ContaSaqueComValorNegativoException;

public class ContaTest {
	
	private Cliente cliente = new Cliente("Lúcia", "68245");
	
	private Conta conta;
	
	@Before
	public void inicioTeste() {
		conta = new Conta("1234", "9879", cliente);
	}
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Test
	public void deveLancarExceptionAoDepositarValorNegativo() throws Exception {
		
		try {
			conta.depositar(-250.5);
			fail("Não deveria depositar valor negativo");
		} catch (ContaDepositoValorNegativoException e) {
			
		}

	}
	
	@Test
	public void deveRealizarDepositoNaConta() throws Exception {
		
		//Cenario
		double valorDeposito = 350.56;
		double saldoInicial = conta.getSaldo();
		
		// Ação
		conta.depositar(valorDeposito);
		
		// Validação
		assertEquals(saldoInicial, (conta.getSaldo() - valorDeposito), 0.001);
		
	}
	
	@Test
	public void deveLancarExceptionAoTentarSacarInformandoValorNegativo() throws Exception {
		
		try {
			conta.sacar(-15.9);
			fail("Não deveria realizar saque com valor negativo");
		} catch (ContaSaqueComValorNegativoException e) {
			
		}
		
	}
	
	@Test
	public void naoDeveRealizarSaqueComSaldoInsuficiente() throws ContaSaqueComValorNegativoException {
		// Cenário
		conta.setSaldo(100.0);
		
		// Ação
		try {			
			conta.sacar(150.0);
			fail("Não deveria realizar saque com saldo insuficiente!");
		} catch (ContaSaqueComSaldoInsuficiente e) {
			assertEquals(e.getMessage(), "Saldo insuficiente");
		}
		
	}
	
	@Test
	public void deveDescontarDiferencaNoSaldoAoRealizarSaque() throws ContaSaqueComValorNegativoException, ContaSaqueComSaldoInsuficiente {
		
		// Cenario
		conta.setSaldo(125.5);
		double valorDoSaque = 25.5;
		
		// Acao
		conta.sacar(valorDoSaque);
		
		// Validacao
		assertEquals(100.0, conta.getSaldo(), 0.01);
	}
	
	public void deveRealizarTransferenciaEntraContas() {
		
		
		
	}

}
