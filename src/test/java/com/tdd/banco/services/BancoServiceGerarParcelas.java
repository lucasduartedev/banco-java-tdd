package com.tdd.banco.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class BancoServiceGerarParcelas {
	
	private BancoService banco1;
	double valor;
	int nParcelas;
	
	@Before
	public void setup() {
		banco1 = new BancoService();
		valor = 1500.0;
		nParcelas = 5;
	}
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	
	@Test
	public void deveChecarNumerosDeParcelas() {
		// A��o
		List<Double> listaParcelas = banco1.gerarParcelas(valor, nParcelas, 0);
		
		// Valida��o
		Assert.assertEquals(listaParcelas.size(), nParcelas);
	}
	
	@Test
	public void deveAtribuirDesconto_10Porcento() {
		// Cen�rio
		int desconto = 10;
		
		// A��o
		List<Double> listaParcelas = banco1.gerarParcelas(valor, nParcelas, desconto);
//		double valorParcela = listaParcelas.get(0);
		
		// Valida��o
		Assert.assertEquals(listaParcelas.get(0), 270.0, 0.01);
		
	}

}
