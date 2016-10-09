package gamification;

import java.util.List;

import static org.junit.Assert.*;
import org.junit.*;

public class TesteUnitarioPlacar {

	private Placar placar;	
	private ArmazenamentoMock armazenamentoMock;
	
	@Before
	public void setUp() {
		armazenamentoMock = new ArmazenamentoMock();
		placar = new Placar(armazenamentoMock);
	}
	
	@Test
	public void registrarPontuacao() {
	    placar.adicionarPonto("guerra", "estrela", 10);
	    armazenamentoMock.armazenouPontuacao("guerra", "estrela", 10);
	}

	@Test
	public void pontosPorUsuario() {
	   placar.adicionarPonto("guerra", "estrela", 10);	
		
	   List<Ponto> pontos = placar.pontosPorUsuario("guerra");
	   
	   assertEquals(1, pontos.size());
	   assertEquals("estrela", pontos.get(0).getTipo());
	 }
}
