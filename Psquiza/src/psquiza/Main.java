package psquiza;

public class Main {

	public static void main(String[] args) {
		Facade facade = new Facade();
		facade.cadastraProblema("Pedro amorin", 3);
		facade.cadastraProblema("Eu amo programar", 5);
		facade.cadastraAtividade("Iriamo da silva", "BAIXO", "olhamos para cima");
		System.out.println(facade.busca("amo"));
	}
}
