package caixaeletronico;

public class MockHardwareDefeituso implements Hardware {

	@Override
	public String pegarNumeroDaContaCartao() {
		throw new HardwareException("Falha de funcionamento do hardware");
	}

	@Override
	public void entregarDinheiro() {
		throw new HardwareException("Falha de funcionamento do hardware");	
	}

	@Override
	public void lerEnvelope() {
		throw new HardwareException("Falha de funcionamento do hardware");
	}

}
