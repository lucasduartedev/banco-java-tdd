package com.tdd.banco.models;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.tdd.banco.exceptions.GeradorDeContaSemClienteException;
import com.tdd.banco.services.BancoService;

public class BancoServiceGerarContaTest {
	
	private BancoService banco1;
	private Cliente cliente1;
	private Conta conta1;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Before
	public void setup() throws GeradorDeContaSemClienteException {
		banco1 = new BancoService();
		cliente1 = new Cliente("Hélio", "15647");
		conta1 = banco1.gerarContaBancaria(cliente1);
	}
	
//	@Test
	public void deveRealizarAssertivaVerdadeira() {
		assertTrue(true);
	}
	
	@Test(expected = Exception.class)
	public void DeveLancarExceptionAoTentarCriarContaSemClienteAssociado() throws Exception {
		banco1.gerarContaBancaria(null);
	}
	
	@Test
	public void deveGerarContaBancariaAssociandoCliente() throws GeradorDeContaSemClienteException {
		// Validação
		Assert.assertNotNull(conta1.getCliente());
	}
	
	@Test
	public void deveGerarContaBancariaComSaldoZero() throws GeradorDeContaSemClienteException {
		// Validação
		Assert.assertEquals(0.0, conta1.getSaldo(), 0.01);
	}

}
