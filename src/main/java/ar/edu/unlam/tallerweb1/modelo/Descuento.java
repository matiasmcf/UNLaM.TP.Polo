package ar.edu.unlam.tallerweb1.modelo;

import java.util.HashMap;

public class Descuento {
	private static Descuento instance = new Descuento();
	private HashMap<String, Double> descuentos;

	private Descuento() {
		descuentos = new HashMap<String, Double>();
		descuentos.put("50off", 0.5);
		descuentos.put("40off", 0.4);
		descuentos.put("30off", 0.3);
		descuentos.put("20off", 0.2);
	}

	public static Descuento getInstance() {
		return instance;
	}

	public Double obtenerDescuento(String codigo) {
		if (descuentos.containsKey(codigo))
			return descuentos.get(codigo);
		else
			return 0.;
	}
}
