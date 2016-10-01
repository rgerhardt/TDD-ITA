package carrinho;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoCompras {
	
	private List<Produto> produtos;
	private List<ObservadorCarrinho> observadores;
	
	public CarrinhoCompras() {
		produtos = new ArrayList<>();
		observadores = new ArrayList<>();
	}

	public int total() {
		int total = 0;
		for(Produto p: produtos)
			total += p.getValor();
		return total;
	}

	public void adicionaProduto(Produto produto) {
		produtos.add(produto);
		
		for(ObservadorCarrinho observador: observadores) {
			try {
				observador.produtoAdicionado(produto.getNome(), produto.getValor());
			} catch (Exception e) {}
		}
		
	}

	public void adicionarObservador(ObservadorCarrinho observador) {
		this.observadores.add(observador);
		
	}

}
