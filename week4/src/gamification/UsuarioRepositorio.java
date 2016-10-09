package gamification;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UsuarioRepositorio {
	
	private static final String FILE_DELIMITER = ",";
	private static final String END_LINE_DELIMITER = "\n";
	
    private String arquivo;

    public UsuarioRepositorio(String arquivo) {
        this.arquivo = arquivo;
        inicializarArquivoSeNaoExistir(arquivo);
    }

    public void limparRepositorio() {
        salvarLista(new HashMap<>());
    }
   

    public Map<String, Usuario> listar() {
    	
    	Map<String, Usuario> map = new HashMap<>();
    	try {
			Scanner scanner = new Scanner(new File(arquivo));
			while(scanner.hasNext()) {
				Usuario usuario = parseLine(scanner.nextLine());
				
				if(map.containsKey(usuario.getNome()))
					map.get(usuario.getNome()).adicionarPontos(usuario.getPontos());
				else
					map.put(usuario.getNome(), usuario);
			}
			scanner.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

        return map;
    }

    public void salvar(Usuario usuario) {
        Map<String, Usuario> usuarios = listar();
        usuarios.put(usuario.getNome(), usuario);

        salvarLista(usuarios);
    }

    public Usuario buscarOuCriar(String nomeUsuario) {
        Map<String, Usuario> usuarios = listar();
        Usuario usuario = usuarios.get(nomeUsuario);
        if(usuario == null)
        	usuario = new Usuario(nomeUsuario);

        return usuario;
    }

    private Usuario parseLine(String line) {
    	String data[] = line.split(FILE_DELIMITER);
		String nomeUsuario = data[0];
		String tipoPontuacao = data[1];
		int quantidade = Integer.valueOf(data[2]);
		
		Ponto ponto = new Ponto(tipoPontuacao, quantidade);
		Usuario usuario = new Usuario(nomeUsuario);
		usuario.adicionarPonto(ponto);
		
		return usuario;
    }
    
    private void inicializarArquivoSeNaoExistir(String fileName) {
        File file = new File(fileName);
        if (file.length() == 0) {
            limparRepositorio();
        }
    }

    private void salvarLista(Map<String, Usuario> usuarios) {
  	
    	try {
			FileWriter writer = new FileWriter(new File(arquivo));
			for(Usuario usuario: usuarios.values()) {
				for(Ponto ponto: usuario.getPontos()) {
					gravarPonto(writer, usuario, ponto);
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void gravarPonto(FileWriter writer, Usuario usuario, Ponto ponto) throws IOException {
		writer.write(usuario.getNome());
		writer.write(FILE_DELIMITER);
		writer.write(ponto.getTipo());
		writer.write(FILE_DELIMITER);
		writer.write(String.valueOf(ponto.getQuantidade()));
		writer.write(FILE_DELIMITER);
		writer.write(END_LINE_DELIMITER);
    }
}