package com.tdd.banco.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class BancoServiceGerarCartaoTest {
	
	private Cartao cartao1;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Test
	public void testTrue() {
		Assert.assertTrue(true);
	}
	
	@Before
	public void setup() {
		cartao1 = new Cartao();
	}
	
	@Test(expected = Exception.class)
	public void deveLancarExceptionAoGerarCartaoSemContaInformada() throws Exception {
		
	}

}
