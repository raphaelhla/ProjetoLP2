package psquiza;

public class Facade {

	private PesquisadorController pesquisadorController;
	
	public Facade() {
		this.pesquisadorController = new PesquisadorController();
	}
	
	// METODOS DA PARTE 1 (MATHEUS) ABAIXO DESSE COMENTARIO
	
	
	
	// METODOS DA PARTE 2 (RAPHAEL) ABAIXO DESSE COMENTARIO
	
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		this.pesquisadorController.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}
	
    // METODOS DA PARTE 3 (ALISSON) ABAIXO DESSE COMENTARIO
	
	
	
	// METODOS DA PARTE 4 (WESLLEY) ABAIXO DESSE COMENTARIO
	
	
	
}
