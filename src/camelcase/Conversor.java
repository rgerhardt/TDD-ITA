package camelcase;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Conversor {
	
	public static List<String> converterCamelCase(String frase) {
		validarFrase(frase);
		List<String> palavras = new ArrayList<String>();
		
		Matcher m = constroiExpressao().matcher(frase);
		while(m.find()) {
			String palavra = m.group();
			palavras.add(convertePalavraParaMinusculo(palavra));
		}
		return palavras; 
	}
	
	private static Pattern constroiExpressao() {
		String minusculasNoComeco = "(^[a-z]+)";
		String primeiraLetraMaiuscula = "([A-Z]{1}[a-z]+)";
		String numeros = "([0-9]+)";
		String siglas = "([A-Z]+(?=([A-Z][a-z])|($)))";
		return Pattern.compile(minusculasNoComeco+"|"+primeiraLetraMaiuscula+"|"+numeros+"|"+siglas);
	}
	
	private static String convertePalavraParaMinusculo(String palavra) {
		return ehSigla(palavra)? palavra : palavra.toLowerCase();
	}
	
	private static void validarFrase(String frase) {
		if(frase == null || frase.isEmpty())
			throw new FraseInvalidaException("A frase fornecida está vazia");
		if(comecaComNumero(frase))
			throw new FraseInvalidaException("A frase começa com um número");
		if(contemCaractereEspecial(frase))
			throw new FraseInvalidaException("A frase possui caracteres especiais");
	}
	

	private static boolean ehSigla(String palavra)
	{
		return Pattern.matches("[A-Z]*", palavra);
	}
	
	private static boolean comecaComNumero(String frase) {
		return Character.isDigit(frase.charAt(0));
	}
	
	private static  boolean contemCaractereEspecial(String frase) {
		 return !Pattern.matches("[A-Za-z0-9]*", frase);
	}

	
	
}
