package gamification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Placar {
	
	private ArmazenamentoPontuacao armazenamento;
	
	public Placar(ArmazenamentoPontuacao armazenamento) {
		this.armazenamento = armazenamento;
	}

	public void adicionarPonto(String nomeUsuario, String tipo, int quantidade) {
		armazenamento.armazenarPontuacao(nomeUsuario, tipo, quantidade);	
	}

	public List<Ponto> pontosPorUsuario(String nomeUsuario) {
		List<Ponto> pontosPorUsuario = new ArrayList<>();
		for(Ponto ponto: armazenamento.pontosPorUsuario(nomeUsuario)) {
			if(ponto.getQuantidade() > 0 ) {
				pontosPorUsuario.add(ponto);
			}
		}
		return pontosPorUsuario;
	}
	
	public List<Usuario> rankingPorTipo(String tipo) {
		List<Usuario> usuarios = armazenamento.usuariosComPontos();
		Collections.sort(usuarios, new RankingPorTipoComparator(tipo));
		return usuarios;
	}
	


	private class RankingPorTipoComparator implements Comparator<Usuario>{

		private String tipo;
		
		public RankingPorTipoComparator(String tipo) {
			this.tipo = tipo;
		}
		
		@Override
		public int compare(Usuario usuario, Usuario outroUsuario) {
			return outroUsuario.totalDePontos(tipo) - usuario.totalDePontos(tipo);
		}
		
	}

	
	
	
	
}
