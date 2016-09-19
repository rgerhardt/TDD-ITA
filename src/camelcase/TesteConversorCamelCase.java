package camelcase;

import org.junit.Before;
import org.junit.Test;

import pilha.Pilha;

import static org.junit.Assert.*;

import java.util.List;

public class TesteConversorCamelCase {

	
	@Test
	public void converteNome() {
		String nome = "nome";
		
		List<String> palavras = Conversor.converterCamelCase(nome);
		
		assertEquals(palavras.size(), 1);
		assertEquals("nome", palavras.get(0));
	}
	
	@Test
	public void converteNomeComAPrimeiraLetraMaiuscula() {
		String nome = "Nome";
		
		List<String> palavras = Conversor.converterCamelCase(nome);
		
		assertEquals(palavras.size(), 1);
		assertEquals("nome", palavras.get(0));
	}
	
	@Test
	public void converteNomeComposto() {
		String nomeComposto = "nomeMaisComposto";
		
		List<String> palavras = Conversor.converterCamelCase(nomeComposto);
		
		assertEquals(palavras.size(), 3);
		assertEquals("nome", palavras.get(0));
		assertEquals("mais", palavras.get(1));
		assertEquals("composto", palavras.get(2));
	}
	
	@Test
	public void naoDeveAlterarSigla() {
		String sigla = "CPF";
		
		List<String> palavras = Conversor.converterCamelCase(sigla);
		
		assertEquals(palavras.size(), 1);
		assertEquals("CPF", palavras.get(0));
	}
	
	@Test
	public void converteNomeCompostoComSigla() {
		String nomeComposto = "nomeCPFComposto";
		
		List<String> palavras = Conversor.converterCamelCase(nomeComposto);
		
		assertEquals(palavras.size(), 3);
		assertEquals("nome", palavras.get(0));
		assertEquals("CPF", palavras.get(1));
		assertEquals("composto", palavras.get(2));
	}
	
	@Test
	public void converteNomeComSiglaNoFinal() {
		String nomeComposto = "nomeCPF";
		
		List<String> palavras = Conversor.converterCamelCase(nomeComposto);
		
		assertEquals(palavras.size(), 2);
		assertEquals("nome", palavras.get(0));
		assertEquals("CPF", palavras.get(1));
	}

	@Test
 	public void converteNomeCompostoComNumero() {
		String nomeComposto = "nome12Composto";
		
		List<String> palavras = Conversor.converterCamelCase(nomeComposto);
		
		assertEquals(palavras.size(), 3);
		assertEquals("nome", palavras.get(0));
		assertEquals("12", palavras.get(1));
		assertEquals("composto", palavras.get(2));
 	}
	
	@Test(expected=FraseInvalidaException.class) 
	public void naoPodeAceitarPalavrasVazias() {
		List<String> palavras = Conversor.converterCamelCase("");		
	}

	@Test(expected=FraseInvalidaException.class)
	public void naoDeveIniciarComNumero() {
		List<String> palavras = Conversor.converterCamelCase("12Composto");		
	}
	
	@Test(expected=FraseInvalidaException.class)
	public void naoDeveConterCaracteresInvalidos() {
		Conversor.converterCamelCase("Com posto#xx");		
	}
	
}
