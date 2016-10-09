package gamification;

import java.io.Serializable;

public class Ponto implements Serializable {


	private static final long serialVersionUID = -6256275690585763123L;
	
	private String tipo;
	private int quantidade;
		
	public Ponto(String tipo, int quantidade) {
		this.tipo = tipo;
		this.quantidade = quantidade;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
    public String getTipo() {
		return tipo;
	}

	public void adicionar(int quantidade) {
		this.quantidade += quantidade;	
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Ponto) {
			Ponto ponto = (Ponto)obj;
			if(tipo.compareTo(ponto.tipo) == 0)
				return true;
		}
		return false;
	}
}
