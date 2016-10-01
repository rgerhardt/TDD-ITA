package carrinho;

public class Produto {

	private String nome;
	private int valor;
	
	public Produto(String nome, int valor) {
		this.nome = nome;
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}
	
	public String getNome() {
		return nome;
	}

}
