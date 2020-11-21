import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClienteImpl {
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, InterruptedException {
		Banco banco = (Banco) Naming.lookup("//localhost/banco001");
		Scanner scanner = new Scanner(System.in);
		String st = "s";
		while (st.equalsIgnoreCase("s")) {
			st = abrirConta(scanner, banco);
		}
		scanner.close();
	}
	

	private static String abrirConta(Scanner scanner, Banco banco) throws RemoteException, InterruptedException {
		System.out.print("nome completo: ");
		String nome = scanner.next();
		System.out.print("Qual o saldo Inicial: ");
		BigDecimal saldoInicial = scanner.nextBigDecimal();
		Conta conta = banco.abrirConta(saldoInicial, new Titular(nome.toUpperCase()));
		System.out.println(conta.obterTitular() + " conta `" + conta.getIdentificadorConta() + "` possui saldo de: R$" + conta.obterSaldo());
		System.out.print("Deseja cadastrar outra conta? ");
		return scanner.next();
	}

}