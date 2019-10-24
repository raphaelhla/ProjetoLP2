package psquiza;

public class Main {

	/**
	 * CRIEI ESSE MAIN APENAS PARA FAZER ALGUNS TESTES
	 * @param args
	 */
	public static void main(String[] args) {
		Validador x = new Validador();
		String URL = "sem o necessario";
//		x.verificafotoURL(URL);
		PesquisadorController y = new PesquisadorController();
		y.cadastraPesquisador("teste", "estudante", "teste", "teste@teste", "https://teste");
		y.alteraPesquisador("teste@teste", "email", "matrix@1999");
		System.out.println(y.exibePesquisador("matrix@1999"));
//		Pesquisador pesquisardor = new Pesquisador("teste", "estudante", "teste.", "teste@teste", "sem o necessario");
	}
}
