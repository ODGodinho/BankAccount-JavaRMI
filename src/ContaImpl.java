import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ContaImpl extends UnicastRemoteObject implements Conta {

	private static final long serialVersionUID = 1L;
	
	private Titular titular;
	private String identificadorConta;
	private BigDecimal saldo;

	public ContaImpl(String idConta, BigDecimal saldo, Titular titular) throws RemoteException { 
		super();
		this.identificadorConta = idConta;
		this.saldo = saldo;
		this.titular = titular;
	} 


	public void aplicarCorrecao(BigDecimal indiceCorrecao) throws RemoteException {
		this.saldo = (getSaldo().add(getSaldo().multiply(indiceCorrecao)));
		System.out.println("Correção aplicada");
	}


	public void transferirValor(BigDecimal valor, Conta contaASerCreditada) throws RemoteException {
		if (this.debitarValor(valor)) {
			contaASerCreditada.creditarValor(valor);
			
			System.out.println(					
				getTitular().getNome() + 
				", é o dono da conta `" +
						this.identificadorConta + "` e transferiu R$ " +
				getSaldo() + " para a conta: " + contaASerCreditada.getIdentificadorConta()
			);
			
		} 
	}
	
	

	public BigDecimal obterSaldo() throws RemoteException {
		System.out.println(getSaldo());
		return getSaldo();
	}



	public String obterTitular() throws RemoteException {
		System.err.println(getTitular().getNome());
		return getTitular().getNome();
	}


	
	public boolean debitarValor(BigDecimal valor) throws RemoteException {
		if (getSaldo().compareTo(valor) != -1) {
			this.saldo = (getSaldo().subtract(valor));
			System.out.println("Valor debitado");
			return true;
		} else {
			System.out.println("Não há saldo suficiente");
			return false;
		}
	}
	

	public String getIdentificadorConta() {
		System.err.println(getTitular().getNome() + ", seu número da conta é: " + this.identificadorConta + ". ");
		return identificadorConta;
	}
	
	
	private Titular getTitular() {
		return titular;
	}
	


	public void encerrarConta() throws RemoteException {
		try {
			System.out.println("Encerrando conta: " + identificadorConta);
			Naming.unbind(this.identificadorConta);
			System.out.println("Conta encerrada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void creditarValor(BigDecimal valor) throws RemoteException {
		this.saldo = (getSaldo().add(valor));
		System.out.println("Valor Creditado");
	}

	

	private BigDecimal getSaldo() {
		return saldo;
	}

}
