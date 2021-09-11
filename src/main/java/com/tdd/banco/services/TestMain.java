package com.tdd.banco.services;

import java.util.List;

public class TestMain {
	
	public static void main(String[] args) {
		
		BancoService banco1 = new BancoService();
		
		List<Double> parcelas = banco1.gerarParcelas(1500, 5, 10);
		
		for (Double valor : parcelas) {
			System.out.println(valor);
		}
		
	}

}
