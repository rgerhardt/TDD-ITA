package caixaeletronico;

import static org.junit.Assert.*;

public class MockHardware implements Hardware {

	private String numeroDaConta;
	private Double valorEnvelope;
	private boolean entregouDinheiro;
	private boolean leuEnvelope;
	
	public MockHardware() {
		this.numeroDaConta = "";
		this.valorEnvelope = 0.0;
		this.entregouDinheiro = false;
		this.leuEnvelope = false;
	}
	
	public void setNumeroDaConta(String numeroDaConta) {
		this.numeroDaConta = numeroDaConta;
	}
	
	public void setValorEnvelope(double valorEnvelope) {
		this.valorEnvelope = valorEnvelope;
	}
	
	
	@Override
	public String pegarNumeroDaContaCartao() {
		return numeroDaConta;
	}

	@Override
	public void entregarDinheiro() {	
		entregouDinheiro = true;
	}

	@Override
	public void lerEnvelope() {
		leuEnvelope = true;
	}
	
	public void verificaSeLeuOEnvelope() {
		assertTrue(leuEnvelope);
	}
	
	public void verificaSeEntregouDinheiro() {
		assertTrue(entregouDinheiro);
	}

}
