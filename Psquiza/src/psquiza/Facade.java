package psquiza;

import easyaccept.EasyAccept;

public class Facade {

	public static void main(String[] args) {
		args = new String[] { "psquiza.Facade", "testes_easyaccept/use_case_1.txt",
				"testes_easyaccept/use_case_2.txt",
				/**"testes_easyaccept/use_case_3.txt", "testes_easyaccept/use_case_4.txt" */};
		EasyAccept.main(args);
	}

	private PesquisadorController pesquisadorController;

	public Facade() {
		this.pesquisadorController = new PesquisadorController();
	}

	// METODOS DA PARTE 1 (MATHEUS) ABAIXO DESSE COMENTARIO

	// METODOS DA PARTE 2 (RAPHAEL) ABAIXO DESSE COMENTARIO

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		this.pesquisadorController.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		this.pesquisadorController.alteraPesquisador(email, atributo, novoValor);
	}

	public void ativaPesquisador(String email) {
		this.pesquisadorController.ativaPesquisador(email);
	}

	public void desativaPesquisador(String email) {
		this.pesquisadorController.desativaPesquisador(email);
	}

	public String exibePesquisador(String email) {
		return this.pesquisadorController.exibePesquisador(email);
	}

	// METODOS DA PARTE 3 (ALISSON) ABAIXO DESSE COMENTARIO

	// METODOS DA PARTE 4 (WESLLEY) ABAIXO DESSE COMENTARIO

}
