package caixaeletronico;

public class ContaCorrente {
	
	private String numero;
	private String senha;
	private double saldo;
	
	public ContaCorrente(String numero, String senha, double saldo) {
		this.numero = numero;
		this.senha = senha;
		this.saldo = saldo;
	}
	
	private boolean saldoSuficiente(double valor) {
		return saldo >= valor;
	}
	
	public boolean sacar(double valor) {
		if(saldoSuficiente(valor)) {
			saldo -= valor;
			return true;
		}
		return false;
	}
	
	public void depositar(double valor) {
		saldo += valor;
	}
	
	
	public String getNumero() {
		return numero;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public String getSenha() {
		return senha;
	}

}
