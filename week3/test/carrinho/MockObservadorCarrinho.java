package carrinho;

import static org.junit.Assert.*;

public class MockObservadorCarrinho  implements ObservadorCarrinho {

	private String nomeRecebido;
	private int valorRecebido;
	
	@Override
	public void produtoAdicionado(String nome, int valor) {
		nomeRecebido = nome;
		valorRecebido = valor;
	}

	public void verificaRecebimentoProduto(String nomeEsperado, int valorEsperado) {
		assertEquals(nomeRecebido, nomeEsperado);
		assertEquals(valorRecebido, valorEsperado);	
	}


}
