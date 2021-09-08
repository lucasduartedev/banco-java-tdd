package com.tdd.banco.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.tdd.banco.exceptions.CartaoLimiteNaoInformado;
import com.tdd.banco.exceptions.CartaoSemContaVinculada;
import com.tdd.banco.exceptions.ContaLimiteDeCartoesAtingido;
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
		cliente1 = new Cliente("João Pereira", "894765");
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
	public void deveLancarExceptionAoGerarCartaoSemContaVinculada() throws CartaoLimiteNaoInformado, ContaLimiteDeCartoesAtingido {
		try {
//			Ação
			banco1.gerarCartao(null, limiteUm);
			fail("Não deveria executar esta linha");
		} catch (CartaoSemContaVinculada e) {
//			Validação
			assertEquals(e.getMessage(), "Cartão deve ter um conta vinculada");
		}
	}
	
	@Test(expected = CartaoSemContaVinculada.class)
	public void deveLancarExceptionAoGerarCartaoSemContaVinculada_2() throws CartaoSemContaVinculada, CartaoLimiteNaoInformado, ContaLimiteDeCartoesAtingido {
		banco1.gerarCartao(null, limiteUm);
	}
	
	@Test
	public void deveLancarExceptionCasoOClienteNaoInformeOLimite() throws CartaoSemContaVinculada, ContaLimiteDeCartoesAtingido {
		try {
			// Ação
			banco1.gerarCartao(conta1, 0.0);
			fail("Não deveria dar continuidade");
		} catch (CartaoLimiteNaoInformado e) {
			// Validação
			assertEquals(e.getMessage(), "O cliente deve informar o limite");
		}
	}
	
	@Test(expected = CartaoLimiteNaoInformado.class)
	public void deveLancarExceptionCasoOClienteNaoInformeOLimite_2() throws CartaoLimiteNaoInformado, CartaoSemContaVinculada, ContaLimiteDeCartoesAtingido {
		banco1.gerarCartao(conta1, 0.0);
	}
	
	@Test
	public void deveLancarExceptionCasoAlgumaContaComCincoCartoesJaCriadosTenteCriarMainUm() throws CartaoSemContaVinculada, CartaoLimiteNaoInformado {
		try {
			// Ação
			banco1.gerarCartao(conta1, limiteUm);
			fail("Deveria lanças exception");
		} catch (ContaLimiteDeCartoesAtingido e) {
			// Validação
			assertEquals(e.getMessage(), "Limite de cartões atingido");
		}
	}
	
	@Test(expected = ContaLimiteDeCartoesAtingido.class)
	public void deveLancarExceptionCasoAlgumaContaComCincoCartoesJaCriadosTenteCriarMainUm_2() throws ContaLimiteDeCartoesAtingido, CartaoSemContaVinculada, CartaoLimiteNaoInformado {
		banco1.gerarCartao(conta1, limiteUm);
	}
	
	@Test
	public void deveGerarUmCartaoESomarUmTotalDeCincoCartoes() throws CartaoSemContaVinculada, CartaoLimiteNaoInformado, ContaLimiteDeCartoesAtingido {
		// Cenário
		// Removendo um cartão - Reajuste de cenário
		conta1.getCartoes().remove(1);
		
		// Ação
		banco1.gerarCartao(conta1, limiteUm);
		
		// Validação
		assertEquals(5, conta1.getCartoes().size());
		
	}

}
