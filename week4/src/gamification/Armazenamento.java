package gamification;

import java.util.ArrayList;
import java.util.List;



public class Armazenamento implements ArmazenamentoPontuacao {
	
	private UsuarioRepositorio repositorio;
	
	public Armazenamento(UsuarioRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	public void armazenarPontuacao(String nomeUsuario, String tipo, int quantidade) {
		Usuario usuario = repositorio.buscarOuCriar(nomeUsuario);
		usuario.adicionarPonto(tipo, quantidade);
		repositorio.salvar(usuario);
	}

	@Override
	public int pontosPorUsuarioETipo(String nomeUsuario, String tipo) {
		Usuario usuario = porNome(nomeUsuario);
		if(usuario == null)
			return 0;
		else
			return usuario.totalDePontos(tipo);
	}


	@Override
	public List<Usuario> usuariosComPontos() {
		List<Usuario> usuariosComPontos = new ArrayList<>();
		for(Usuario usuario: repositorio.listar().values()) {
			if(usuario.marcouPontos())
				usuariosComPontos.add(usuario);
		}
		return usuariosComPontos;
	}


	@Override
	public List<Ponto> pontosPorUsuario(String nomeUsuario) {
		Usuario usuario = porNome(nomeUsuario);
		if(usuario == null) {
			return new ArrayList<>();
		}
		return usuario.getPontos();
	}
	
	
	private Usuario porNome(String nomeUsuario) {
	    return repositorio.listar().get(nomeUsuario);
	}

	
}
