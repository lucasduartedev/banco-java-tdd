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

	private Cliente cliente1 = new Cliente("L�cia", "68245");
	private Cliente cliente2 = new Cliente("Jo�o", "98754");

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
			conta1.depositar(-250.5);
			fail("N�o deveria depositar valor negativo");
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
		double saldoInicial = conta1.getSaldo();

		// A��o
		conta1.depositar(valorDeposito);

		// Valida��o
		assertEquals(saldoInicial, (conta1.getSaldo() - valorDeposito), 0.001);

	}

	@Test
	public void deveLancarExceptionAoTentarSacarInformandoValorNegativo() throws Exception {

		try {
			conta1.sacar(-15.9);
			fail("N�o deveria realizar saque com valor negativo");
		} catch (ContaSaqueComValorNegativoException e) {

		}

	}

	@Test
	public void naoDeveRealizarSaqueComSaldoInsuficiente() throws ContaSaqueComValorNegativoException {
		// Cen�rio
		conta1.setSaldo(100.0);

		// A��o
		try {
			conta1.sacar(150.0);
			fail("N�o deveria realizar saque com saldo insuficiente!");
		} catch (ContaSaqueComSaldoInsuficiente e) {
			assertEquals(e.getMessage(), "Saldo insuficiente");
		}

	}

	@Test(expected = Exception.class)
	public void naoDeveRealizarSaqueComSaldoInsuficiente_2() throws Exception {
		// Cen�rio
		conta1.setSaldo(50.0);

		// A��o
		conta1.sacar(100.0);
	}

	@Test
	public void naoDeveRealizarSaqueComSaldoInsuficiente_3() throws ContaSaqueComValorNegativoException {
		// Cen�rio
		conta1.setSaldo(100.0);

		// A��o
		try {
			conta1.sacar(150.0);
		} catch (ContaSaqueComSaldoInsuficiente e) {
			// Valida��o
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
	public void deveLancarExceptionCasoOValorSejaNegativo() throws ContaTranferenciaComContaDestidoInexistente {

		// Cen�rio
		double valorTransferencia = -15.0;

		// A��o
		try {
			conta1.transferir(conta2, valorTransferencia);
			fail("Deveria lan�ar exception");
		} catch (ContaTransferenciaComValorAbaixoDoMinimo e) {
			// Valida��o
			assertEquals(e.getMessage(), "E necessario valor minimo");
		}
	}

	/*
	 * Deve lan�ar excepiton caso o titular informe um numero negativo
	 */
	@Test(expected = Exception.class)
	public void deveLancarExceptionCasoOValorSejaNegativo_2() throws Exception {
		conta1.transferir(conta2, -15.0);
	}

	@Test
	public void deveRealizarTransferenciaEntreDuasContas() throws ContaTransferenciaComValorAbaixoDoMinimo, ContaTranferenciaComContaDestidoInexistente {

		// Cen�rio
		conta1.setSaldo(150.0);
		double valorTransferencia = 50.0;

		// A��o
		conta1.transferir(conta2, valorTransferencia);

		// Valida��o
		assertEquals(100.0, conta1.getSaldo(), 0.001);
		assertEquals(50.0, conta2.getSaldo(), 0.001);

	}

	@Test(expected = Exception.class)
	public void deveLancarExceptionCasoNaoExistaContaDestido() throws Exception {
		// Cen�rio
		conta1.setSaldo(150.0);
		double valorTransferencia = 50.0;

		// A��o
		conta1.transferir(null, valorTransferencia);
		
	}

}
