package sab;


@SuppressWarnings("serial")
public class LivroIndisponivelParaEmprestimoException extends Exception {
	public LivroIndisponivelParaEmprestimoException(String message)
    {
       super(message);
    }
}