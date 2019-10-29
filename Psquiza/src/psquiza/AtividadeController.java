package psquiza;

import java.util.HashMap;
import java.util.Map;

public class AtividadeController {

	private Validador validador;
	private Map<String, Atividade> mapaAtividades;
	private int indice;

	public AtividadeController() {
		this.validador = new Validador();
		this.mapaAtividades = new HashMap<String, Atividade>();
		this.indice = 1;
	}

	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		validador.verificaEntradaNulaVazia(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		validador.verificaNivelRisco(nivelRisco);
		validador.verificaEntradaNulaVazia(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		Atividade atividade = new Atividade(descricao, nivelRisco, descricaoRisco);
		String codigo = "A" + this.indice++;
		this.mapaAtividades.put(codigo, atividade);
		return codigo;
	}

	private void verificaSeExisteAtividade(String codigo) {
		if (!mapaAtividades.containsKey(codigo)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}

	public void apagaAtividade(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaSeExisteAtividade(codigo);
		this.mapaAtividades.remove(codigo);
	}

	public void cadastraItem(String codigo, String item) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(item, "Item nao pode ser nulo ou vazio.");
		verificaSeExisteAtividade(codigo);
		this.mapaAtividades.get(codigo).cadastraItem(item);
	}

	public String exibeAtividade(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaSeExisteAtividade(codigo);
		return this.mapaAtividades.get(codigo).toString();
	}

	public int contaItensPedentes(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaSeExisteAtividade(codigo);
		return this.mapaAtividades.get(codigo).getQtdItensPendentes();
	}

	public int contaItensRealizados(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaSeExisteAtividade(codigo);
		return this.mapaAtividades.get(codigo).getQtdItensRealizados();
	}

}
