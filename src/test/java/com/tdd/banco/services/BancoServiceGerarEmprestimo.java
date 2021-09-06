package com.tdd.banco.services;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.tdd.banco.exceptions.EmprestimoDeveTerUmaContaAssociadaException;
import com.tdd.banco.models.Conta;

public class BancoServiceGerarEmprestimo {
	
	private BancoService banco1;
	private Conta conta1;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Before
	public void setup() {
		banco1 = new BancoService();
	}

	@Test
	public void deveLancarException_ContaNula() throws EmprestimoDeveTerUmaContaAssociadaException {
		try {
			// Ação
			banco1.gerarEmprestimo(null);
			fail("Deveria lancar exception");
		} catch (EmprestimoDeveTerUmaContaAssociadaException e) {
			// Validação
			Assert.assertEquals(e.getMessage(), "Todo emprestimo deve ter uma conta associada");
		}
	}
	
	@Test(expected = Exception.class)
	public void deveLancarException_ContaNula_2() throws Exception {
		banco1.gerarEmprestimo(null);
	}

}
