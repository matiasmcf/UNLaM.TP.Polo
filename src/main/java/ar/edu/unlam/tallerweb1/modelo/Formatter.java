package ar.edu.unlam.tallerweb1.modelo;

/**
 * Clase que permite corregir la cantidad de digitos al momento de informar un <code>Double</code> en una vista.<br>
 * Es <i>Singleton</i>, por lo que para utilizarse debe agregarse una instancia de la misma al modelo.
 */
public class Formatter {

	private static Formatter instance = new Formatter();

	private Formatter() {
	};

	/**
	 * Obtiene la instancia.
	 */
	public static Formatter getInstance() {
		return instance;
	}

	/**
	 * Retorna una cadena representando el número con el formato aplicado.
	 * 
	 * @param dato
	 *            -Double a ser formateado.
	 */
	public String aplicar(Double dato) {
		String s = String.format("%.2f", dato);
		return s;
	}
}
