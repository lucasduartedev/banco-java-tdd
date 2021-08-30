package com.tdd.banco.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.tdd.banco.exceptions.CartaoNuloException;
import com.tdd.banco.exceptions.CartaoSemContaVinculada;
import com.tdd.banco.services.BancoService;

public class BancoServiceGerarCartaoTest {
	
	private Cartao cartao1;
	private Cliente cliente1;
	private Conta conta1;
	
	private BancoService banco1;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Before
	public void setup() {
		banco1 = new BancoService();
	}
	
	@Test
	public void deveLancarExceptionAoGerarCartaoSemContaVinculada() throws CartaoNuloException {
		try {
//			A��o
			banco1.gerarCartao(cartao1, null);
			fail("N�o deveria executar esta linha");
		} catch (CartaoSemContaVinculada e) {
//			Valida��o
			assertEquals(e.getMessage(), "Cart�o deve ter um conta vinculada");
		}
		
	}
	
	@Test(expected = Exception.class)
	public void deveLancarExceptionAoGerarCartaoCasaOCartaoSejaNulo() throws CartaoSemContaVinculada {
		try {
//			A��o
			banco1.gerarCartao(null, conta1);
			fail("N�o deveria executar esta linha");
		} catch (CartaoNuloException e) {
//			Valida��o
			assertEquals(e.getMessage(), "Cart�o nao deve ser nulo");
		}
	}
	
	

}
