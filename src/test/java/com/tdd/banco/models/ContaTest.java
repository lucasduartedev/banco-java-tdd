package com.tdd.banco.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.tdd.banco.exceptions.ContaDepositoValorNegativoException;
import com.tdd.banco.exceptions.ContaSaqueComSaldoInsuficiente;
import com.tdd.banco.exceptions.ContaSaqueComValorNegativoException;
import com.tdd.banco.exceptions.ContaTranferenciaComContaDestidoInexistente;
import com.tdd.banco.exceptions.ContaTransferenciaComValorAbaixoDoMinimo;

public class ContaTest {

	private Cliente cliente1 = new Cliente("Lúcia", "68245");
	private Cliente cliente2 = new Cliente("João", "98754");

	private Conta conta1;
	private Conta conta2;

	@Before
	public void setup() {
		conta1 = new Conta("1234", "9879", cliente1);
		conta2 = new Conta("9875", "1545", cliente2);
	}

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Test
	public void deveLancarExceptionAoDepositarValorNegativo() throws Exception {
		try {
			conta1.depositar(-0.1);
			fail("Não deveria depositar valor negativo");
		} catch (ContaDepositoValorNegativoException e) {
			Assert.assertEquals(e.getMessage(), "Deposito com valor negativo");
		}
	}
	
	@Test(expected = Exception.class)
	public void deveLancarExceptionAoDepositarValorNegativo_2() throws Exception {
		conta1.depositar(-250.5);
	}

	@Test
	public void deveRealizarDepositoNaConta() throws Exception {
		// Cenario
		double valorDeposito = 350.56;
		double saldoAposDeposito = 0.0;
		// Ação
		conta1.depositar(valorDeposito);
		saldoAposDeposito = conta1.getSaldo();
		// Validação
		assertEquals(valorDeposito, saldoAposDeposito, 0.001);
	}

	@Test
	public void deveLancarExceptionAoTentarSacarValorNegativo() throws Exception {
		try {
			conta1.sacar(-15.9);
			fail("Não deveria realizar saque com valor negativo");
		} catch (ContaSaqueComValorNegativoException e) {
			assertEquals(e.getMessage(), "Saque com valor negativo");
		}
	}

	@Test
	public void naoDeveRealizarSaqueComSaldoInsuficiente() throws ContaSaqueComValorNegativoException {
		// Cenário
		conta1.setSaldo(100.0);

		// Ação
		try {
			conta1.sacar(150.0);
			fail("Não deveria realizar saque com saldo insuficiente!");
		} catch (ContaSaqueComSaldoInsuficiente e) {
			assertEquals(e.getMessage(), "Saldo insuficiente");
		}
	}

	@Test(expected = Exception.class)
	public void naoDeveRealizarSaqueComSaldoInsuficiente_2() throws Exception {
		// Ação
		conta1.sacar(100.0);
	}

	@Test
	public void naoDeveRealizarSaqueComSaldoInsuficiente_3() throws ContaSaqueComValorNegativoException {

		// Ação
		try {
			conta1.sacar(150.0);
		} catch (ContaSaqueComSaldoInsuficiente e) {
			// Validação
			assertEquals(e.getMessage(), "Saldo insuficiente");
		}
	}

	@Test
	public void deveDescontarDiferencaNoSaldoAoRealizarSaque()
			throws ContaSaqueComValorNegativoException, ContaSaqueComSaldoInsuficiente {

		// Cenario
		conta1.setSaldo(125.5);
		double valorDoSaque = 25.5;

		// Acao
		conta1.sacar(valorDoSaque);

		// Validacao
		assertEquals(100.0, conta1.getSaldo(), 0.01);
	}

	@Test
	public void deveLancarExceptionCasoOValorDaTranferenciaSejaNegativo() throws ContaTranferenciaComContaDestidoInexistente {

		// Ação
		try {
			conta1.transferir(conta2, -5.0);
			fail("Deveria lançar exception");
		} catch (ContaTransferenciaComValorAbaixoDoMinimo e) {
			// Validação
			assertEquals(e.getMessage(), "E necessario valor minimo");
		}
	}

	/*
	 * Deve lançar excepiton caso o titular informe um numero negativo
	 */
	@Test(expected = Exception.class)
	public void deveLancarExceptionCasoOValorDaTranferenciaSejaNegativo_2() throws Exception {
		conta1.transferir(conta2, -15.0);
	}

	@Test
	public void deveRealizarTransferenciaEntreDuasContas() throws ContaTransferenciaComValorAbaixoDoMinimo, ContaTranferenciaComContaDestidoInexistente {

		// Cenário
		conta1.setSaldo(150.0);
		double valorTransferencia = 50.0;

		// Ação
		conta1.transferir(conta2, valorTransferencia);

		// Validação
		assertEquals(100.0, conta1.getSaldo(), 0.001);
		assertEquals(50.0, conta2.getSaldo(), 0.001);

	}

	@Test(expected = Exception.class)
	public void deveLancarExceptionCasoNaoExistaContaDestido() throws Exception {
		// Ação
		conta1.transferir(null, 50.0);
	}

}
