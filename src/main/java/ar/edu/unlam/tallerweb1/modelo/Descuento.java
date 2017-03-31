package ar.edu.unlam.tallerweb1.modelo;

import java.util.HashMap;

/**
 * Clase que contiene códigos de descuentos.<br>
 * Es una clase <i>Singleton</i>, por lo que debe accederse mediante el método <code>Descuento.getInstance()</code><br>
 */
public class Descuento {

	private static Descuento		instance	= new Descuento();
	private HashMap<String, Double>	descuentos;

	private Descuento() {
		descuentos = new HashMap<String, Double>();
		descuentos.put("50off", 0.5);
		descuentos.put("40off", 0.4);
		descuentos.put("30off", 0.3);
		descuentos.put("20off", 0.2);
	}

	/**
	 * Obtiene la instancia.
	 */
	public static Descuento getInstance() {
		return instance;
	}

	/**
	 * Devuelve el porcentaje de descuento asociado al código que se recibe por parámetro.<br>
	 * En caso de no existir el código, retorna 0 (ningún descuento).<br>
	 */
	public Double obtenerDescuento(String codigo) {
		if (descuentos.containsKey(codigo))
			return descuentos.get(codigo);
		else
			return 0.;
	}
}
