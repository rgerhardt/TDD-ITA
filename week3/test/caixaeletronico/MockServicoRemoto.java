package caixaeletronico;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class MockServicoRemoto implements ServicoRemoto {

	private Map<String, ContaCorrente> contas;
	
	
	public MockServicoRemoto() {
		contas = new HashMap<>();
	}
	
	
	@Override
	public ContaCorrente recuperarConta(String numero) {
		return contas.get(numero);
	}

	@Override
	public void persistirConta(ContaCorrente conta) {
		contas.put(conta.getNumero(), conta);
	}
	
	public void verificaSaldoConta(String numero, double valorEsperado) {
		ContaCorrente conta = contas.get(numero);
		assertNotNull(conta);
		assertEquals(conta.getNumero(), numero);
		assertEquals(conta.getSaldo(), valorEsperado, 3);	
	}

}
