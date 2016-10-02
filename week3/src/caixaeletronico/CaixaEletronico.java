package caixaeletronico;

public class CaixaEletronico {
	
	private ServicoRemoto servicoRemoto;
	private ContaCorrente conta;
	private Hardware hardware;
	
	public CaixaEletronico(ServicoRemoto servicoRemoto, Hardware hardware) {
		this.servicoRemoto = servicoRemoto;
		this.hardware = hardware;
	}
	
	private void verificarLogin() {
		if(conta == null)
			throw new CaixaEletronicoException("Você deve fazer o login antes de fazer uma operação");
	}
	
	public String logarComCartao(String senha) {
		String numero = hardware.pegarNumeroDaContaCartao();
		return logar(numero, senha);
	}
	
	public String logar(String numero, String senha) {
		ContaCorrente conta = servicoRemoto.recuperarConta(numero);
		if(conta != null && conta.getSenha().equals(senha)) {
			this.conta = conta;
			return "Usuário Autenticado";
		} else
			return "Não foi possível autenticar o usuário";
	}
	
	public String depositar(double valor) {
		verificarLogin();
		hardware.lerEnvelope();
		conta.depositar(valor);
		servicoRemoto.persistirConta(conta);
		return "Depósito recebido com sucesso";
	}
	
	public String sacar(double valor) {
		verificarLogin();
		
		if(conta.sacar(valor)) { 
			servicoRemoto.persistirConta(conta);
			hardware.entregarDinheiro();
			return  "Retire seu dinheiro";
		} else
			return "Saldo insuficiente";

	}
	
	public String saldo() {
		verificarLogin();
		
		return String.format("O saldo é R$ %.2f", conta.getSaldo());
	}

}
