package psquiza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pesquisa implements Comparable<Pesquisa> {

	private String descricao;
	private String campoDeInteresse;
	private String codigo;
	private boolean statusPesquisa;
	private Validador validador;
	private Map<String, Atividade> atividadesAssociadas;
	private Map<String, Problema> problemaAssociado;
	private Map<String, Objetivo> objetivosAssociados;

	public Pesquisa(String descricao, String campoDeInteresse, String codigo) {
		validador = new Validador();
		validador.verificaEntradaNulaVazia(descricao, "Descricao nao pode ser nula ou vazia.");
		this.statusPesquisa = true;
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.codigo = codigo;
		this.atividadesAssociadas = new HashMap<String, Atividade>();
		this.problemaAssociado = new HashMap<String, Problema>();
		this.objetivosAssociados = new HashMap<String, Objetivo>();
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

	// US5 WESLLEY

	public boolean associaProblema(String codigoProblema, Problema problema) {
		if (problemaAssociado.containsKey(codigoProblema)) {
			return false;
		} else if (problemaAssociado.size() > 0) {
			throw new IllegalArgumentException("Pesquisa ja associada a um problema.");
		} else if (this.statusPesquisa == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else {
			this.problemaAssociado.put(codigoProblema, problema);
			return true;
		}
	}

	public boolean desassociaProblema(String codigoProblema) {
		if (this.statusPesquisa == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else if (!problemaAssociado.containsKey(codigoProblema)) {
			return false;
		} else {
			this.problemaAssociado.remove(codigoProblema);
			return true;
		}
	}

	public boolean getAssociacaoObjetivo(String codigoObjetivo) {
		return this.objetivosAssociados.containsKey(codigoObjetivo);
	}

	public boolean associaObjetivo(String codigoObjetivo, Objetivo objetivo) {
		if (this.statusPesquisa == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else if (objetivosAssociados.containsKey(codigoObjetivo)) {
			return false;
		} else {
			this.objetivosAssociados.put(codigoObjetivo, objetivo);
			return true;
		}
	}

	public boolean desassociaObjetivo(String codigoObjetivo) {
		if (this.statusPesquisa == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else if (!objetivosAssociados.containsKey(codigoObjetivo)) {
			return false;
		} else {
			this.objetivosAssociados.remove(codigoObjetivo);
			return true;
		}
	}

	public String getCodigo() {
		return this.codigo;
	}

	public int getQtdObjetivo() {
		return this.objetivosAssociados.size();
	}

	@Override
	public int compareTo(Pesquisa p) {
		return this.codigo.compareTo(p.getCodigo());
	}

	public boolean getTemProblemaAssociado() {
		if (this.problemaAssociado.size() > 0) {
			return true;
		}
		return false;
	}

	public String getCodigoProblema() {
		List<String> lista = new ArrayList<>();
		if (this.problemaAssociado.size() > 0) {
			for (String chave : this.problemaAssociado.keySet()) {
				lista.add(chave);
			}
			return lista.get(0);
		}
		return "Sem problemas associados.";
	}
}
