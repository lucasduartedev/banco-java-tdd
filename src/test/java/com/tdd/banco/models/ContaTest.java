package com.tdd.banco.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.tdd.banco.exceptions.ContaDepositoValorNegativoException;
import com.tdd.banco.exceptions.ContaSaqueComValorNegativoException;

public class ContaTest {
	
	private Cliente cliente = new Cliente("Lúcia", "68245");
	
	private Conta conta1 = new Conta("1234", "9879", cliente);
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Test
	public void deveLancarExceptionAoDepositarValorNegativo() throws Exception {
		
		try {
			conta1.depositar(-250.5);
			fail("Deveria lancar exception!");
		} catch (ContaDepositoValorNegativoException e) {
			
		}

	}
	
	@Test
	public void deveRealizarDepositoNaConta() throws Exception {
		
		// Ação
		double valorDeposito = conta1.depositar(350.56);
		
		// Validação
		assertEquals(350.56, valorDeposito, 0.001);
		
	}
	
	@Test
	public void deveLancarExceptionAoTentarSacarValorNegativo() throws Exception {
		
		try {
			conta1.sacar(-15.9);
			fail("Deveria lancar excetion");
		} catch (ContaSaqueComValorNegativoException e) {
			
		}
		
	}

}
