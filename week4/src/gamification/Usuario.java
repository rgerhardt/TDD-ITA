package gamification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Usuario implements Serializable {
	

	private static final long serialVersionUID = -342854453806765307L;

	private String nome;
	private HashMap<String, Ponto> pontos;
	
	public Usuario(String nome) {
		this.nome = nome;
		this.pontos = new HashMap<>();
	}
	
	public String getNome() {
		return nome;
	}

	public void adicionarPonto(Ponto ponto) {
		if(pontos.containsKey(ponto.getTipo()))
			pontos.get(ponto.getTipo()).adicionar(ponto.getQuantidade());
		else
			pontos.put(ponto.getTipo(), ponto);
	}
	
	public void adicionarPonto(String tipo, int quantidade) {
		if(pontos.containsKey(tipo))
			pontos.get(tipo).adicionar(quantidade);
		else
			pontos.put(tipo, new Ponto(tipo, quantidade));
	}
	
	public void adicionarPontos(List<Ponto> pontos) {
		for(Ponto ponto: pontos) 
			this.pontos.put(ponto.getTipo(), ponto);
	}

	public int totalDePontos(String tipo) {
		if(pontos.containsKey(tipo))
			return pontos.get(tipo).getQuantidade();
		else
			return 0;
	}
	
	public List<Ponto> getPontos() {
		return new ArrayList(pontos.values());
	}
	

	public boolean marcouPontos() {
		for(Ponto ponto: getPontos()) {
			if(ponto.getQuantidade() > 0)
				return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Usuario) {
			Usuario usuario = (Usuario)obj;
			if(nome.compareTo(usuario.nome) == 0)
				return true;
		}
		return false;
	}


	
}
