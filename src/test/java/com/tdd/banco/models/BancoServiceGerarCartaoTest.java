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
//			Ação
			banco1.gerarCartao(cartao1, null);
			fail("Não deveria executar esta linha");
		} catch (CartaoSemContaVinculada e) {
//			Validação
			assertEquals(e.getMessage(), "Cartão deve ter um conta vinculada");
		}
		
	}
	
	@Test(expected = Exception.class)
	public void deveLancarExceptionAoGerarCartaoCasaOCartaoSejaNulo() throws CartaoSemContaVinculada {
		try {
//			Ação
			banco1.gerarCartao(null, conta1);
			fail("Não deveria executar esta linha");
		} catch (CartaoNuloException e) {
//			Validação
			assertEquals(e.getMessage(), "Cartão nao deve ser nulo");
		}
	}
	
	

}
