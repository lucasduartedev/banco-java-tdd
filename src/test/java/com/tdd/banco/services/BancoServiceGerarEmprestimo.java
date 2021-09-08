package com.tdd.banco.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.tdd.banco.exceptions.EmprestimoException;
import com.tdd.banco.models.Conta;
import com.tdd.banco.models.Emprestimo;

public class BancoServiceGerarEmprestimo {
	
	private BancoService banco1;
	private Conta conta1;

	private List<Emprestimo> emprestimosConta1 = new ArrayList<Emprestimo>();
	
	private Emprestimo emprestimo1;
	private Emprestimo emprestimo2;
//	private Emprestimo emprestimo3;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Before
	public void setup() {
		banco1 = new BancoService();
		conta1 = new Conta();
		
		emprestimo1 = new Emprestimo(1500.0, 12, 8.9);
		emprestimo2 = new Emprestimo(1250.0, 15, 8.9);
//		emprestimo3 = new Emprestimo(1850.0, 13, 8.9);
		
	}
	
	@Test
	public void deveLancarException_ContaNula() {
		try {
			// Ação
			banco1.gerarEmprestimo(null, 1500.0, 15);
			fail("Deveria lancar exception");
		} catch (EmprestimoException e) {
			// Validação
			assertEquals(e.getMessage(), "Conta não pode ser nula");
		}
	}
	
	@Test(expected = EmprestimoException.class)
	public void deveLancarException_ContaNula_2() throws EmprestimoException {
		banco1.gerarEmprestimo(null, 1500.0, 15);
	}
	
	@Test
	public void deveLancarException_ValorMinimoNecessario() {
		try {
			banco1.gerarEmprestimo(conta1, 140.0, 2);
			fail("Deveria lancar exception");
		} catch (EmprestimoException e) {
			assertEquals(e.getMessage(), "Valor minimo necessario");
		}
	}
	
	@Test(expected = EmprestimoException.class)
	public void deveLancarException_ValorMinimoNecessario_2() throws EmprestimoException {
		banco1.gerarEmprestimo(conta1, 140.0, 2);
	}
	
	@Test
	public void deveLancarException_LimiteDeEmprestimosAtingido() throws EmprestimoException {
		
		// Cenário
		emprestimosConta1.add(emprestimo1);
		emprestimosConta1.add(emprestimo2);
		conta1.setEmprestimos(emprestimosConta1);
		
		try {
			// Ação
			banco1.gerarEmprestimo(conta1, 1500.0, 5);
			fail("Deveria lancar exception");
		} catch (Exception e) {
			// Validação
			assertEquals(e.getMessage(), "Limite de emprestimos atingido");
		}
		
	}
	
	@Test(expected = EmprestimoException.class)
	public void deveLancarException_LimiteDeEmprestimosAtingido_2() throws EmprestimoException {
		// Cenário
		emprestimosConta1.add(emprestimo1);
		emprestimosConta1.add(emprestimo2);
		conta1.setEmprestimos(emprestimosConta1);
		
		// Ação
		banco1.gerarEmprestimo(conta1, 1500.0, 5);
	}
	
}
