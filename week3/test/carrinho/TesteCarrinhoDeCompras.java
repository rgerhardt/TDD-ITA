package carrinho;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteCarrinhoDeCompras {

	
	
	@Test
	public void totalCarrinho() {
		CarrinhoCompras c = new CarrinhoCompras();
		c.adicionaProduto(new Produto("tenis", 100));
		c.adicionaProduto(new Produto("camisa", 50));
		c.adicionaProduto(new Produto("bermuda", 70));
		assertEquals(220, c.total());
	}
	
	@Test
	public void escutaAdicaoDeProduto() {
		CarrinhoCompras c = new CarrinhoCompras();
		MockObservadorCarrinho mock = new MockObservadorCarrinho();
		c.adicionarObservador(mock);
		c.adicionaProduto(new Produto("bermuda", 70));
		mock.verificaRecebimentoProduto("bermuda", 70);
	}
	
	@Test
	public void adicionarDoisObservadores() {
		CarrinhoCompras c = new CarrinhoCompras();
		MockObservadorCarrinho mock1 = new MockObservadorCarrinho();
		MockObservadorCarrinho mock2 = new MockObservadorCarrinho();
		c.adicionarObservador(mock1);
		c.adicionarObservador(mock2);	
		c.adicionaProduto(new Produto("bermuda", 70));
		mock1.verificaRecebimentoProduto("bermuda", 70);
		mock2.verificaRecebimentoProduto("bermuda", 70);
	}
	
	
	@Test
	public void continuaNotificandoComErrorEmObservador() {
		CarrinhoCompras c = new CarrinhoCompras();
		MockObservadorCarrinho mock1 = new MockObservadorCarrinho();
		ObservadorCarrinho mock2 = new MockObservadorComProblema();
		MockObservadorCarrinho mock3 = new MockObservadorCarrinho();
		c.adicionarObservador(mock1);
		c.adicionarObservador(mock2);
		c.adicionarObservador(mock3);	
		c.adicionaProduto(new Produto("bermuda", 70));
		mock1.verificaRecebimentoProduto("bermuda", 70);
		mock3.verificaRecebimentoProduto("bermuda", 70);
	}
}
