package caixaeletronico;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TesteCaixaEletronico {

	private MockServicoRemoto servicoRemoto;
	private MockHardware hardware;
	private CaixaEletronico caixa;
	private ContaCorrente conta;
	
	@Before
	public void inicializa() {
		conta = new ContaCorrente("1234", "4444", 100.0);
		
		hardware = new MockHardware();
		hardware.setNumeroDaConta("1234");
		hardware.setValorEnvelope(100.0);
		
		servicoRemoto = new MockServicoRemoto();
		servicoRemoto.persistirConta(conta);
		
		caixa = new CaixaEletronico(servicoRemoto, hardware);
	}
	
	private void logarNoCaixa() {
		caixa.logar(conta.getSenha());
	}

	
	@Test
	public void logarComUsuarioValido() {
		assertEquals(caixa.logar(conta.getSenha()), "Usuário Autenticado");
	}
	
	@Test
	public void tentarLogarComSenhaInValida() {
		assertEquals(caixa.logar("121"), "Não foi possível autenticar o usuário");
	}
	
	@Test(expected=CaixaEletronicoException.class)
	public void precisaLogarAntesDeVerifcarSaldo() {
		caixa.saldo();
	}
	
	@Test
	public void verificarSaldo() {
		logarNoCaixa();
		assertEquals(caixa.saldo(), "O saldo é R$ 100.00");
	}
	
	@Test(expected=CaixaEletronicoException.class)
	public void precisaLogarAntesDeDepositar() {
		caixa.depositar(0.0);
	}
	
	@Test
	public void depositar() {
		logarNoCaixa();
		assertEquals(caixa.depositar(100.0), "Depósito recebido com sucesso");
		assertEquals(caixa.saldo(), "O saldo é R$ 200.00");
	}
	
	@Test
	public void depositaEPersisteConta() {
		logarNoCaixa();
		caixa.depositar(100.0);
		servicoRemoto.verificaSaldoConta(conta.getNumero(), 200.0);
	}
	
	@Test 
	public void depositaELeEnvelope() {
		logarNoCaixa();
		caixa.depositar(100.0);
		hardware.verificaSeLeuOEnvelope();
	}
	
	@Test(expected=CaixaEletronicoException.class)
	public void precisaLogarAntesDeSecar() {
		caixa.depositar(0.0);
	}
	
	@Test
	public void sacaAbaixoDoLimiteDisponivel() {
		logarNoCaixa();
		assertEquals(caixa.sacar(conta.getSaldo()), "Retire seu dinheiro");
		assertEquals(caixa.saldo(), "O saldo é R$ 0.00");
	}
	
	@Test
	public void sacaAcimaDoLimiteDisponivel() {
		logarNoCaixa();
		assertEquals(caixa.sacar(conta.getSaldo() * 2), "Saldo insuficiente");
	}
	
	@Test
	public void sacaEPersisteConta() {
		logarNoCaixa();
		caixa.sacar(conta.getSaldo());
		servicoRemoto.verificaSaldoConta(conta.getNumero(), 0.0);
	}
	
	
	@Test(expected=HardwareException.class)
	public void tentarLogarComCartaoQuandoHardwareEstaDefeituoso() {
		caixa = new CaixaEletronico(servicoRemoto, new MockHardwareDefeituso());
		caixa.logar(conta.getSenha());
	}
	

	@Test(expected=HardwareException.class)
	public void tentarDepositarQuandoHardwareEstaDefeituoso() {
		caixa = new CaixaEletronico(servicoRemoto, new MockHardwareDefeituso());
		caixa.logar(conta.getSenha());
		caixa.depositar(10.0);
	}
	
	@Test
	public void entregarDinheiro() {
		logarNoCaixa();
		caixa.sacar(conta.getSaldo());
		hardware.verificaSeEntregouDinheiro();
	}
	
	@Test(expected=HardwareException.class)
	public void tentarEntregarDinheiroQuandoHardwareEstaDefeituoso() {
		caixa = new CaixaEletronico(servicoRemoto, new MockHardwareDefeituso());
		caixa.logar(conta.getSenha());
	}
	
	
}
