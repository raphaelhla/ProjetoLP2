package psquiza;

public class Problema {

	private String descricao;
	private int viabilidade;
	
	public Problema(String descricao, int viabilidade) {
		this.descricao = descricao;
		this.viabilidade = viabilidade;
		
	}

	@Override
	public String toString() {
		return this.descricao + " - " + this.viabilidade;
	}
	
	
	
	
}
