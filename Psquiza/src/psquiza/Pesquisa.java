package psquiza;

import java.util.HashMap;
import java.util.Map;

public class Pesquisa {

	private String descricao;
	private String campoDeInteresse;
	private boolean statusPesquisa;
	private Validador validador;
	private Map<String, Atividade> atividadesAssociadas;

	public Pesquisa(String descricao, String campoDeInteresse) {
		validador = new Validador();
		validador.verificaEntradaNulaVazia(descricao, "Descricao nao pode ser nula ou vazia.");
		this.statusPesquisa = true;
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.atividadesAssociadas = new HashMap<String, Atividade>();
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCampoDeInteresse() {
		return campoDeInteresse;
	}

	public void setCampoDeInteresse(String campoDeInteresse) {
		this.campoDeInteresse = campoDeInteresse;
	}

	public boolean getStatusPesquisa() {
		return statusPesquisa;
	}

	public void ativaPesquisa() {
		this.statusPesquisa = true;
	}
	
	public void desativaPesquisa() {
		this.statusPesquisa = false;
	}

	@Override
	public String toString() {
		return this.descricao + " - " + this.campoDeInteresse;
	}

	public boolean associaAtividade(String codigoAtividade, Atividade atividade) {
		this.atividadesAssociadas.put(codigoAtividade, atividade);
		return false;
	}

	public boolean desassociaAtividade(String codigoAtividade) {
		this.atividadesAssociadas.remove(codigoAtividade);
		return false;
	}
	
	
}
