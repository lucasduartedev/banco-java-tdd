package com.tdd.banco.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.tdd.banco.exceptions.ContaException;
import com.tdd.banco.exceptions.GeradorDeContaSemClienteException;
import com.tdd.banco.models.Cartao;
import com.tdd.banco.models.Cliente;
import com.tdd.banco.models.Conta;

public class BancoServiceGerarCartaoTest {
	
	private Cliente cliente1;
	private Conta conta1;
	
	private Cartao cartao1;
	private Cartao cartao2;
	private Cartao cartao3;
	private Cartao cartao4;
	private Cartao cartao5;
	
	private List<Cartao> cartoesConta1 = new ArrayList<Cartao>();
	
	private double limiteUm;
	
	private BancoService banco1;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Before
	public void setup() throws GeradorDeContaSemClienteException {
		cliente1 = new Cliente("Jo?o Pereira", "894765");
		banco1 = new BancoService();
		conta1 = banco1.gerarContaBancaria(cliente1);
		
		cartao1 = new Cartao(cliente1.getNome(), conta1, 2500.0);
		cartao2 = new Cartao(cliente1.getNome(), conta1, 2200.0);
		cartao3 = new Cartao(cliente1.getNome(), conta1, 1800.0);
		cartao4 = new Cartao(cliente1.getNome(), conta1, 1600.0);
		cartao5 = new Cartao(cliente1.getNome(), conta1, 1850.0);
		
		cartoesConta1.add(cartao1);
		cartoesConta1.add(cartao2);
		cartoesConta1.add(cartao3);
		cartoesConta1.add(cartao4);
		cartoesConta1.add(cartao5);
		
		conta1.setCartoes(cartoesConta1);
		
		limiteUm = 2500.0;
	}
	
	@Test
	public void deveLancarExceptionAoGerarCartaoSemContaVinculada() throws ContaException {
		try {
//			A??o
			banco1.gerarCartao(null, limiteUm);
			fail("N?o deveria executar esta linha");
		} catch (ContaException e) {
//			Valida??o
			assertEquals(e.getMessage(), "Cart?o deve ter um conta vinculada");
		}
	}
	
	@Test(expected = ContaException.class)
	public void deveLancarExceptionAoGerarCartaoSemContaVinculada_2() throws ContaException {
		banco1.gerarCartao(null, limiteUm);
	}
	
	@Test
	public void deveLancarExceptionCasoOClienteNaoInformeOLimite() throws ContaException {
		try {
			// A??o
			banco1.gerarCartao(conta1, 0.0);
			fail("N?o deveria dar continuidade");
		} catch (ContaException e) {
			// Valida??o
			assertEquals(e.getMessage(), "O cliente deve informar o limite");
		}
	}
	
	@Test(expected = ContaException.class)
	public void deveLancarExceptionCasoOClienteNaoInformeOLimite_2() throws ContaException {
		banco1.gerarCartao(conta1, 0.0);
	}
	
	@Test
	public void deveLancarExceptionCasoAlgumaContaComCincoCartoesJaCriadosTenteCriarMainUm() throws ContaException {
		try {
			// A??o
			banco1.gerarCartao(conta1, limiteUm);
			fail("Deveria lan?as exception");
		} catch (ContaException e) {
			// Valida??o
			assertEquals(e.getMessage(), "Limite de cart?es atingido");
		}
	}
	
	@Test(expected = ContaException.class)
	public void deveLancarExceptionCasoAlgumaContaComCincoCartoesJaCriadosTenteCriarMainUm_2() throws ContaException {
		banco1.gerarCartao(conta1, limiteUm);
	}
	
	@Test
	public void deveGerarUmCartaoESomarUmTotalDeCincoCartoes() throws ContaException {
		// Cen?rio
		// Removendo um cart?o - Reajuste de cen?rio
		conta1.getCartoes().remove(1);
		
		// A??o
		banco1.gerarCartao(conta1, limiteUm);
		
		// Valida??o
		assertEquals(5, conta1.getCartoes().size());
		
	}

}
