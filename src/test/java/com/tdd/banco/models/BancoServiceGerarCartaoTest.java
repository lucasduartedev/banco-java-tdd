package com.tdd.banco.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.tdd.banco.exceptions.CartaoLimiteNaoInformado;
import com.tdd.banco.exceptions.CartaoSemContaVinculada;
import com.tdd.banco.exceptions.GeradorDeContaSemClienteException;
import com.tdd.banco.services.BancoService;

public class BancoServiceGerarCartaoTest {
	
	private Cliente cliente1;
	private Conta conta1;
	
	private double limiteUm;
	
	private BancoService banco1;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Before
	public void setup() throws GeradorDeContaSemClienteException {
		cliente1 = new Cliente("Jo�o Pereira", "894765");
		banco1 = new BancoService();
		conta1 = banco1.gerarContaBancaria(cliente1);
		
		limiteUm = 2500.0;
	}
	
	@Test
	public void deveLancarExceptionAoGerarCartaoSemContaVinculada() throws CartaoLimiteNaoInformado {
		try {
//			A��o
			banco1.gerarCartao(null, limiteUm);
			fail("N�o deveria executar esta linha");
		} catch (CartaoSemContaVinculada e) {
//			Valida��o
			assertEquals(e.getMessage(), "Cart�o deve ter um conta vinculada");
		}
	}
	
	@Test(expected = Exception.class)
	public void deveLancarExceptionAoGerarCartaoSemContaVinculada_2() throws Exception {
		banco1.gerarCartao(null, limiteUm);
	}
	
//	@Test
//	public void deveLancarExceptionAoGerarCartaoCasaOCartaoSejaNulo() throws CartaoSemContaVinculada, CartaoLimiteNaoInformado {
//		try {
////			A��o
//			banco1.gerarCartao(conta1, limiteUm);
//			fail("N�o deveria executar esta linha");
//		} catch (CartaoNuloException e) {
////			Valida��o
//			assertEquals(e.getMessage(), "Cart�o nao deve ser nulo");
//		}
//	}
//	
//	@Test(expected = Exception.class)
//	public void deveLancarExceptionAoGerarCartaoCasaOCartaoSejaNulo_2() throws Exception {
//		banco1.gerarCartao(conta1, limiteUm);
//	}
	
	@Test
	public void deveLancarExceptionCasoOClienteNaoInformeOLimite() throws CartaoSemContaVinculada {
		try {
			// A��o
			banco1.gerarCartao(conta1, 0.0);
			fail("N�o deveria dar continuidade");
		} catch (CartaoLimiteNaoInformado e) {
			// Valida��o
			assertEquals(e.getMessage(), "O cliente deve informar o limite");
		}
	}
	
	@Test(expected = Exception.class)
	public void deveLancarExceptionCasoOClienteNaoInformeOLimite_2() throws Exception {
		banco1.gerarCartao(conta1, 0.0);
	}

}
