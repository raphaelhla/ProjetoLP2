package psquiza;

public class Objetivo {

	private String tipo;
	private String descricao;
	private int aderencia;
	private int viabilidade;

	public Objetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		this.tipo = tipo;
		this.descricao = descricao;
		this.aderencia = aderencia;
		this.viabilidade = viabilidade;
		System.out.println("TESTE");
	}

	@Override
	public String toString() {
		int valor = aderencia + viabilidade;
		return this.tipo + " - " + this.descricao + " - " + valor;
	}
	
	
	
	
}
