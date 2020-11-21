import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BancoImpl extends UnicastRemoteObject implements Banco {

	private static final long serialVersionUID = 1L;
	private static int countUltimaConta = 1;

	
	protected BancoImpl() throws RemoteException {
		super();
	}

	public Conta abrirConta(BigDecimal saldo, Titular titular) throws RemoteException {
		String contaCorrente = "Conta" + countUltimaConta; 
		Conta c = new ContaImpl(contaCorrente, saldo, titular);
		try {
			Naming.rebind(contaCorrente.toUpperCase(), c); // registra este objeto
			countUltimaConta++;
			System.err.println(titular.getNome() + " conta `" + contaCorrente + "` criada com sucesso!" );  
		} catch (Exception e) {
			System.err.println("ERRO Desconhecido..");
		}
		return c;
	}
	
	public static void main(String[] args)	throws RemoteException, MalformedURLException {
		Banco b = new BancoImpl();
		Naming.rebind("//localhost/banco001", b);
		System.out.println("Banco ativo!");  
	}
}
