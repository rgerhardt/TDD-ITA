package gamification;

import java.util.ArrayList;
import java.util.List;


public class ArmazenamentoMock  implements ArmazenamentoPontuacao {

	private String nomeUsuario;
	private String tipoPonto;
	private int quantidade;
	
	@Override
	public void armazenarPontuacao(String nomeUsuario, String tipoPonto, int quantidade) {
		this.nomeUsuario = nomeUsuario;
		this.tipoPonto = tipoPonto;
		this.quantidade = quantidade;
	}

	@Override
	public int pontosPorUsuarioETipo(String nomeUsuario, String tipo) {
		return quantidade;
	}

	@Override
	public List<Usuario> usuariosComPontos() {
		Usuario usuario = new Usuario(nomeUsuario);
		usuario.adicionarPonto(new Ponto(tipoPonto, quantidade));
		
		List<Usuario> usuarios = new ArrayList();
		usuarios.add(usuario);
		return usuarios;
	}

	@Override
	public List<Ponto> pontosPorUsuario(String nomeUsuario) {
		ArrayList<Ponto> list = new ArrayList();
		if(this.nomeUsuario.equals(nomeUsuario)) {
			list.add(new Ponto(tipoPonto, quantidade));
		}
		return list;
	}

	public boolean armazenouPontuacao(String nomeUsuario, String tipoPonto, int quantidade)
	{
		return (this.nomeUsuario.equals(nomeUsuario) && this.tipoPonto.equals(tipoPonto) && quantidade == quantidade);
	}

}
