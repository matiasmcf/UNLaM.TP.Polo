package ar.edu.unlam.tallerweb1.modelo;

public class Formatter {

	private static Formatter instance = new Formatter();

	private Formatter() {
	};

	public static Formatter getInstance() {
		return instance;
	}

	public String aplicar(Double d) {
		String s = String.format("%.2f", d);
		return s;
	}
}
