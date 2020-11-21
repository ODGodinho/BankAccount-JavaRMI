import java.io.Serializable;

public class Titular implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String nome;

	public Titular(String nome) {
		this.nome = nome;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return getNome();
	}

}
