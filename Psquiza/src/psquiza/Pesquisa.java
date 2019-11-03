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
		if (atividadesAssociadas.containsKey(codigoAtividade)) {
			return false;
		}
		if (this.statusPesquisa == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		this.atividadesAssociadas.put(codigoAtividade, atividade);
		return true;
	}

	public boolean desassociaAtividade(String codigoAtividade) {
		if (!atividadesAssociadas.containsKey(codigoAtividade)) {
			return false;
		}
		if (this.statusPesquisa == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		if (!atividadesAssociadas.containsKey(codigoAtividade)) {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}
		this.atividadesAssociadas.remove(codigoAtividade);
		return true;
	}
	
	public boolean buscaDescricao(String termo) {
		if (this.descricao.contains(termo)) {
			return true;
		}
		return false;
	}

	public boolean buscaCampoInteresse(String termo) {
		if (this.campoDeInteresse.contains(termo)) {
			return true;
		}
		return false;
	}
}
