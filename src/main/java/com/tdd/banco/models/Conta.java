package com.tdd.banco.models;

import java.util.List;

import com.tdd.banco.exceptions.ContaDepositoValorNegativoException;
import com.tdd.banco.exceptions.ContaSaqueComSaldoInsuficiente;
import com.tdd.banco.exceptions.ContaSaqueComValorNegativoException;
import com.tdd.banco.exceptions.ContaTranferenciaComContaDestidoInexistente;
import com.tdd.banco.exceptions.ContaTransferenciaComValorAbaixoDoMinimo;

public class Conta {

	private Long id;

	private String conta;

	private String agencia;

	private double saldo;

	private Cliente cliente;
	
	private List<Cartao> cartoes;

	// Contrutoress
	public Conta() {
	}

	public Conta(Long id, String conta, String agencia, Cliente cliente) {
		this.id = id;
		this.conta = conta;
		this.agencia = agencia;
		this.saldo = 0.0;
		this.cliente = cliente;
	}

	public Conta(String conta, String agencia, Cliente cliente) {
		this.conta = conta;
		this.agencia = agencia;
		this.saldo = 0.0;
		this.cliente = cliente;
	}

	// Apenas para teste, será apagado
//	public Conta(String conta, String agencia, double saldo, Cliente cliente) {
//		this.conta = conta;
//		this.agencia = agencia;
//		this.saldo = saldo;
//		this.cliente = cliente;
//	}

	// Métodos Especiais

	private void inserirSaldo(double valor) {
		this.setSaldo(getSaldo() + valor);
	}

	private void removerSaldo(double valor) {
		this.setSaldo(this.getSaldo() - valor);
	}

	public void depositar(double valorDeposito) throws ContaDepositoValorNegativoException {
		if (valorDeposito < 0.0) {
			throw new ContaDepositoValorNegativoException("Deposito com valor negativo");
		}
		this.inserirSaldo(valorDeposito);
	}

	public void sacar(double valorSaque) throws ContaSaqueComValorNegativoException, ContaSaqueComSaldoInsuficiente {
		if (valorSaque < 0.0) {
			throw new ContaSaqueComValorNegativoException("Saque com valor negativo");
		} else if (this.getSaldo() < valorSaque) {
			throw new ContaSaqueComSaldoInsuficiente("Saldo insuficiente");
		}
		this.removerSaldo(valorSaque);
	}

	public void transferir(Conta conta, double valorTransferencia) throws ContaTransferenciaComValorAbaixoDoMinimo, ContaTranferenciaComContaDestidoInexistente {
		// Verificar valor minimo informado
		if(conta == null) {
			throw new ContaTranferenciaComContaDestidoInexistente("É necessario uma conta de destino");
		} else if (valorTransferencia <= 0.0) {
			throw new ContaTransferenciaComValorAbaixoDoMinimo("E necessario valor minimo");
		}
		this.removerSaldo(valorTransferencia);
		conta.inserirSaldo(valorTransferencia);
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(saldo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (agencia == null) {
			if (other.agencia != null)
				return false;
		} else if (!agencia.equals(other.agencia))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(saldo) != Double.doubleToLongBits(other.saldo))
			return false;
		return true;
	}

}
