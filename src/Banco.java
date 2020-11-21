import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Banco extends Remote {
	
	Conta abrirConta(BigDecimal saldoInicial, Titular titularConta)  throws RemoteException;

	
}
