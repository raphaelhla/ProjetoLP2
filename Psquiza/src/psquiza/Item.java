package psquiza;

public class Item {

	private String descricao;
	private String status;
	private int duracao;
	
	public Item(String descricao) {
		this.descricao = descricao;
		this.status = "PENDENTE";
		this.duracao = 0;
	}
	
	public String toString() {
		return this.status + " - " + this.descricao;
	}
	
	public void executaItem(int duracao) {
		this.status = "REALIZADO";
		this.duracao = duracao;
	}
	
	public String getStatus() {
		return this.status;
	}

	public int getDuracao() {
		return this.duracao;
	}
}
