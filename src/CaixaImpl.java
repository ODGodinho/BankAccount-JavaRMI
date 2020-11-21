import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class CaixaImpl {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Qual número da sua conta? ");
		String conta = scanner.next();

		
		Conta c;
		try {
			c = getConta(conta.toUpperCase());
			while(true) {
				System.out.println("Escolha uma opção");
				System.out.println("1. Obter Saldo'");
				System.out.println("2. Obter Titular'");
				System.out.println("3. Creditar Valor'");
				System.out.println("4. Debitar Valor'");
				System.out.println("5. Aplicar Correcao'");
				System.out.println("6. Transferir Valor'");
				System.out.println("7. Encerrar Conta'");
				int option = scanner.nextInt();
				boolean exit = false;
				switch (option) {
					case 1: 
						obterSaldo(c);
						break;
					case 2: 
						obterTitular(c);
						break;
					case 3: 
						creditarValor(c, scanner);
						break;
					case 4: 
						debitarValor(c, scanner);
						break;
					case 5: 
						aplicarCorrecao(c, scanner);
						break;
					case 6: 
						tranferirValor(c, scanner);
						break;
					case 7: 
						encerrarConta(c, scanner);
						break;
					case 8:
						exit = true;
					default : 
						System.err.println("Escolha uma opção valida da proxima vez..");
						break;
				}
				if(exit) break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		scanner.close();
	}

	private static Conta getConta(String conta) throws MalformedURLException, RemoteException {
		
		try {
			return (Conta) Naming.lookup("//localhost/" + conta.toUpperCase());
		} catch (NotBoundException e) {
			throw new RuntimeException("Não foi possível recuperar essa conta.");
		}
	}

	private static void tranferirValor(Conta c, Scanner scanner) {
		System.out.print("Para qual conta deseja transferir valor? ");
		String conta = scanner.next();
		try {
			Conta contaASerCreditada = getConta(conta);
			System.out.print("Qual valor deseja transferir? ");
			BigDecimal valor = scanner.nextBigDecimal();
			c.transferirValor(valor, contaASerCreditada);
			msgBase(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void encerrarConta(Conta c, Scanner scanner) throws RemoteException {
		System.out.print("Encerrar essa conta? S/N: ");
		String escolha = scanner.next();
		if (escolha.equalsIgnoreCase("S")) c.encerrarConta();
		
	}

	private static void aplicarCorrecao(Conta c, Scanner scanner) throws RemoteException {
		System.out.print("Qual índice da correção? ");
		BigDecimal valor = scanner.nextBigDecimal();
		c.aplicarCorrecao(valor);
		msgBase(c);
	}

	private static void debitarValor(Conta c, Scanner scanner) throws RemoteException {
		System.out.print("Qual valor deseja debitar? ");
		BigDecimal valor = scanner.nextBigDecimal();
		c.debitarValor(valor);
		msgBase(c);
	}

	private static void creditarValor(Conta c, Scanner scanner) throws RemoteException {
		System.out.print("Qual valor deseja creditar? ");
		BigDecimal valor = scanner.nextBigDecimal();
		c.creditarValor(valor);
		msgBase(c);
	}

	private static void obterTitular(Conta c) throws RemoteException {
		c.obterTitular();
		msgBase(c);
	}

	private static void obterSaldo(Conta c) throws RemoteException {
		c.obterSaldo();
		msgBase(c);
	}
	
	private static void msgBase(Conta c) throws RemoteException {
		System.out.println(c.obterTitular() + " a conta \"" + c.getIdentificadorConta() + "\" possui R$" + c.obterSaldo() + " de saldo");
	}

}
