package psquiza;

import java.util.HashMap;
import psquiza.Pesquisa;
import java.util.Map;
import psquiza.Validador;

public class PesquisaController {

	private Map<String, Pesquisa> mapPesquisas;
	private Map<String, Integer> codigos;
	private Validador validador;

	public PesquisaController() {
		this.mapPesquisas = new HashMap<String, Pesquisa>();
		this.codigos = new HashMap<String, Integer>();
		this.validador = new Validador();
	}

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		validador.verificaEntradaNulaVazia(descricao, "Descricao nao pode ser nula ou vazia.");
		validador.verificaCampoDeInteresse(campoDeInteresse);
		String codigo = campoDeInteresse.substring(0, 3).toUpperCase();
		if (codigos.containsKey(codigo)) {
			int v = codigos.get(codigo);
			v += 1;
			codigos.remove(codigo);
			codigos.put(codigo, v);
		} else {
			codigos.put(codigo, 1);
		}
		String chave = codigo + codigos.get(codigo);
		mapPesquisas.put(chave, new Pesquisa(descricao, campoDeInteresse));
		return chave;
	}

	private void verificaSeExistePesquisa(String codigo) {
		if (!mapPesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		verificaSeExistePesquisa(codigo);
		if (mapPesquisas.get(codigo).getStatusPesquisa() == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else {
			if (conteudoASerAlterado.equals("DESCRICAO")) {
				validador.verificaEntradaNulaVazia(novoConteudo, "Descricao nao pode ser nula ou vazia.");
				mapPesquisas.get(codigo).setDescricao(novoConteudo);
			} else if (conteudoASerAlterado.equals("CAMPO")) {
				validador.verificaEntradaNulaVazia(novoConteudo, "Formato do campo de interesse invalido.");
				mapPesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
			} else {
				throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
			}
		}
	}

	public void encerraPesquisa(String codigo, String motivo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(motivo, "Motivo nao pode ser nulo ou vazio.");
		verificaSeExistePesquisa(codigo);
		if (mapPesquisas.get(codigo).getStatusPesquisa() == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		mapPesquisas.get(codigo).desativaPesquisa();
	}

	public void ativaPesquisa(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		verificaSeExistePesquisa(codigo);
		if (mapPesquisas.get(codigo).getStatusPesquisa() == true) {
			throw new IllegalArgumentException("Pesquisa ja ativada.");
		}
		mapPesquisas.get(codigo).ativaPesquisa();
	}

	public String exibePesquisa(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		verificaSeExistePesquisa(codigo);
		return codigo + " - " + mapPesquisas.get(codigo).toString();
	}

	public boolean ehAtiva(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		verificaSeExistePesquisa(codigo);
		return mapPesquisas.get(codigo).getStatusPesquisa();
	}

	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade, Atividade atividade) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		return this.mapPesquisas.get(codigoPesquisa).associaAtividade(codigoAtividade, atividade);
	}

	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		return this.mapPesquisas.get(codigoPesquisa).desassociaAtividade(codigoAtividade);
	}
}
