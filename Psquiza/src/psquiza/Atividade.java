package psquiza;

import java.util.ArrayList;
import java.util.List;

public class Atividade {

	private String descricao;
	private String nivelRisco;
	private String descricaoRisco;
	private Validador validador;
	private List<String[]> listaItens;

	public Atividade(String descricao, String nivelRisco, String descricaoRisco) {
		this.validador = new Validador();
		validador.verificaEntradaNulaVazia(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		validador.verificaNivelRisco(nivelRisco);
		validador.verificaEntradaNulaVazia(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		this.descricao = descricao;
		this.nivelRisco = nivelRisco;
		this.descricaoRisco = descricaoRisco;
		this.listaItens = new ArrayList<>();
	}

	public void cadastraItem(String item) {
		validador.verificaEntradaNulaVazia(item, "Item nao pode ser nulo ou vazio.");
		String[] array = new String[2];
		array[0] = "PENDENTE";
		array[1] = item;
		this.listaItens.add(array);
	}

	public int getQtdItensPendentes() {
		int saida = 0;
		for (int i = 0; i < this.listaItens.size(); i++) {
			if (this.listaItens.get(i)[0].equals("PENDENTE")) {
				saida++;
			}
		}
		return saida;
	}

	public int getQtdItensRealizados() {
		int saida = 0;
		for (int i = 0; i < this.listaItens.size(); i++) {
			if (this.listaItens.get(i)[0].equals("REALIZADO")) {
				saida++;
			}
		}
		return saida;
	}

	@Override
	public String toString() {
		String saida = this.descricao + " (" + this.nivelRisco + " - " + this.descricaoRisco + ")";
		for (int i = 0; i < this.listaItens.size(); i++) {
			saida += " | " + this.listaItens.get(i)[0] + " - " + this.listaItens.get(i)[1];
		}
		return saida;
	}

}
