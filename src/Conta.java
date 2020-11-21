import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Conta extends Remote {
	
	
	BigDecimal obterSaldo() throws RemoteException;
	String obterTitular() throws RemoteException;
	void creditarValor(BigDecimal valor) throws RemoteException;
	boolean debitarValor(BigDecimal valor) throws RemoteException;
	void aplicarCorrecao(BigDecimal indiceCorrecao) throws RemoteException;
	void transferirValor(BigDecimal valor, Conta contaASerCreditada) throws RemoteException;
	void encerrarConta() throws RemoteException;
	String getIdentificadorConta() throws RemoteException;
 
}
