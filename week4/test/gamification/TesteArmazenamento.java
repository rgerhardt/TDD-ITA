package gamification;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesteArmazenamento {

	
	private Armazenamento a;
	private UsuarioRepositorio repositorio;
	
	@Before
	public void setUp() {
		repositorio = new UsuarioRepositorio("usuarios.dat");
		a = new Armazenamento(repositorio);
	}
	
	@After
	public void limparRepositorio() {
		repositorio.limparRepositorio();
	}
	
	@Test
	public void testPersistirERecuperarPontos() {
		String usuario = "guerra";
		Map<String, Integer> pontos = new HashMap<>();
		pontos.put("estrela", 10);
		pontos.put("moeda", 30);
		
		for(Map.Entry<String, Integer> entry: pontos.entrySet()) {
			a.armazenarPontuacao(usuario, entry.getKey(), entry.getValue());
		}
		
		List<Ponto> pontosDoGuerra = a.pontosPorUsuario("guerra");
		assertEquals(pontos.size(), 2);
		for(Ponto p: pontosDoGuerra) {
			assertEquals(new Integer(p.getQuantidade()), pontos.get(p.getTipo()));
		}	
	}
	
	
	@Test
	public void testRegistraPontoParaUsuario() {
		String nome = "guerra";
		String tipo = "estrela";
		int quantidade = 10;
		
		a.armazenarPontuacao(nome, tipo, quantidade);
		
		List<Ponto> pontos = a.pontosPorUsuario(nome);
		assertEquals(quantidade, pontos.get(0).getQuantidade());
		assertEquals(tipo, pontos.get(0).getTipo());
	}
	

	@Test
	public void testRetornaTodosOsPontosDeUmUsuario() {
		String nome = "guerra";
		a.armazenarPontuacao(nome, "estrela", 10);
		a.armazenarPontuacao(nome, "moeda", 15);
		
		List<Ponto> pontos = a.pontosPorUsuario(nome);
		assertEquals(2, pontos.size());
	}
	
	@Test
	public void testRetornaTotalDePontosPorTipo() {	
		String nome = "guerra";
		a.armazenarPontuacao(nome, "estrela", 10);
		a.armazenarPontuacao(nome, "moeda", 15);
		
		assertEquals(10, a.pontosPorUsuarioETipo(nome, "estrela"));
		assertEquals(15, a.pontosPorUsuarioETipo(nome, "moeda"));
	}
	
	
	@Test
	public void testRetornaSomenteUsuariosComPontos() {
		a.armazenarPontuacao("guerra", "estrela", 10);
		a.armazenarPontuacao("joao", "estrela", 0);
		
		List<Usuario> usuarios = a.usuariosComPontos();
		assertEquals(1, usuarios.size());
		assertEquals("guerra", usuarios.get(0).getNome());
	}
	
}
