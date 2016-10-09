package gamification;

import java.util.List;

public interface ArmazenamentoPontuacao  {

	public void armazenarPontuacao(String nomeUsuario, String tipoPonto, int pontuacao);
	public int pontosPorUsuarioETipo(String nomeUsuario, String tipo);
	public List<Usuario> usuariosComPontos();
	public List<Ponto> pontosPorUsuario(String nomeUsuario);
}
