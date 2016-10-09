package gamification;

import java.util.List;

import static org.junit.Assert.*;
import org.junit.*;

public class TesteIntegracaoPlacar {

	private Placar placar;	
	private UsuarioRepositorio repositorio;
	
	@Before
	public void setUp() {
		repositorio = new UsuarioRepositorio("usuarios.dat");
		placar = new Placar(new Armazenamento(repositorio));
	}
	
	@After
	public void limparRepositorio() {
		repositorio.limparRepositorio();
	}
	
	@Test
	public void testRegistrarPonto() {	
		placar.adicionarPonto("guerra", "estrela",10);
		
		List<Ponto> pontos = placar.pontosPorUsuario("guerra");
		assertEquals(1, pontos.size());
	}
	
	@Test
	public void testRetornarTodosPontosMaioresQueZero() {
		placar.adicionarPonto("guerra", "estrela",10);
		placar.adicionarPonto("guerra", "moeda",0);
		
		List<Ponto> pontos = placar.pontosPorUsuario("guerra");
		assertEquals(1, pontos.size());
		assertEquals(10, pontos.get(0).getQuantidade());
	}
	
	
	@Test
	public void testRetornarRankingDeUsuariosPorTipoDePonto() {
		placar.adicionarPonto("guerra", "estrela", 10);
		placar.adicionarPonto("joao", "estrela", 15);
		placar.adicionarPonto("guerrinha", "moeda", 10);

		List<Usuario> usuarios = placar.rankingPorTipo("estrela");
		assertEquals(3, usuarios.size());
		assertEquals("joao", usuarios.get(0).getNome());
		assertEquals("guerra", usuarios.get(1).getNome());
	}
	
	@Test
	public void testNaoDeveRetornarPontosQuandoUsuarioNaoExiste() {
		List<Ponto> pontos = placar.pontosPorUsuario("guerra");
		assertEquals(0, pontos.size());
	}
}
