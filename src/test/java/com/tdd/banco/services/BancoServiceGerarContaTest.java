package com.tdd.banco.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.tdd.banco.exceptions.GeradorDeContaSemClienteException;
import com.tdd.banco.models.Cliente;
import com.tdd.banco.models.Conta;

public class BancoServiceGerarContaTest {
	
	private BancoService banco1;
	private Cliente cliente1;
	private Conta conta1;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Before
	public void setup() throws GeradorDeContaSemClienteException {
		banco1 = new BancoService();
		cliente1 = new Cliente("H?lio", "15647");
		conta1 = banco1.gerarContaBancaria(cliente1);
	}
	
	@Test(expected = GeradorDeContaSemClienteException.class)
	public void DeveLancarExceptionAoTentarCriarContaSemClienteAssociado() throws GeradorDeContaSemClienteException {
		banco1.gerarContaBancaria(null);
	}
	
	@Test
	public void deveGerarContaBancariaAssociandoCliente() throws GeradorDeContaSemClienteException {
		// Valida??o
		Assert.assertNotNull(conta1.getCliente());
	}
	
	@Test
	public void deveGerarContaBancariaComSaldoZero() throws GeradorDeContaSemClienteException {
		// Valida??o
		Assert.assertEquals(0.0, conta1.getSaldo(), 0.01);
	}

}
