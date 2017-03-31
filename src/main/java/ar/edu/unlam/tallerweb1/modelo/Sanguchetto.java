package ar.edu.unlam.tallerweb1.modelo;

import java.util.LinkedList;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Ingrediente.TipoIngrediente;

/**
 * Clase que permite armar un sanguche con los ingredientes y condimentos seleccionados.
 */
public class Sanguchetto {

	private List<Ingrediente>	ingredientes;
	private String				descuento;

	/**
	 * Crea un Sanguchetto.
	 */
	public Sanguchetto() {
		ingredientes = new LinkedList<Ingrediente>();
	}

	/**
	 * Almacena el código de descuento ingresado.
	 * 
	 * @param descuento
	 */
	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	/**
	 * Obtiene el código de descuento ingresado por el usuario.
	 */
	public String getDescuento() {
		return descuento;
	}

	/**
	 * Retorna un <code>String</code> representando el porcentaje de descuento obtenido.
	 */
	public String getPorcentajeDescuento() {
		String res = String.format("%d", (int)(Descuento.getInstance().obtenerDescuento(descuento) * 100));
		return res + "%";
	}

	/**
	 * Elimina todos los ingredientes del Sanguchetto y restaura el {@link Stock} de los mismos.<br>
	 */
	public void vaciar() {
		for (Ingrediente ingrediente: ingredientes)
			Stock.getInstance().agregarStock(ingrediente, 1);
		this.ingredientes.removeAll(ingredientes);
	}

	/**
	 * Agrega un ingrediente al sanguche, disminuyendo el stock del mismo.<br>
	 * 
	 * @param ingrediente
	 */
	public void agregarIngrediente(Ingrediente ingrediente) {
		Stock stock = Stock.getInstance();
		if (stock.comprarIngrediente(ingrediente, 1)) {
			ingredientes.add(ingrediente);
		}

	}

	/**
	 * Elimina un ingrediente del sanguche, restaurando el stock del mismo.
	 * 
	 * @param ingrediente
	 */
	public void quitarIngrediente(Ingrediente ingrediente) {
		Stock stock = Stock.getInstance();
		ingredientes.remove(ingrediente);
		stock.agregarStock(ingrediente, 1);
	}

	/**
	 * Lista todos los ingredientes que forman parte del sanguche.<br>
	 */
	public List<Ingrediente> verIngredientes() {
		List<Ingrediente> ingredientesSinCondimentos = new LinkedList<Ingrediente>();
		for (Ingrediente ingrediente: ingredientes) {
			if (ingrediente.getTipo().equals(TipoIngrediente.INGREDIENTE)) {
				ingredientesSinCondimentos.add(ingrediente);
			}
		}
		return ingredientesSinCondimentos;
	}

	/**
	 * Lista todos los condimentos que forman parte del sanguche.<br>
	 */
	public List<Ingrediente> verCondimentos() {
		List<Ingrediente> condimentos = new LinkedList<Ingrediente>();
		for (Ingrediente ingrediente: ingredientes) {
			if (ingrediente.getTipo().equals(TipoIngrediente.CONDIMENTO)) {
				condimentos.add(ingrediente);
			}
		}
		return condimentos;
	}

	/**
	 * Retorna el precio total del sanguchetto, aplicando los descuentos disponibles.<br>
	 */
	public Double getPrecio() {
		Double precio = 0.;
		for (Ingrediente ingrediente: ingredientes) {
			precio += ingrediente.getPrecio();
		}
		return precio - precio * Descuento.getInstance().obtenerDescuento(descuento);
	}

	/**
	 * Retorna el precio total del sanguchetto sin descuento.<br>
	 */
	public Double getPrecioSinDescuento() {
		Double precio = 0.;
		for (Ingrediente ingrediente: ingredientes) {
			precio += ingrediente.getPrecio();
		}
		return precio;
	}

	/**
	 * Vacía el sanguche, haciendo permanentes los cambios al stock.
	 */
	public void comprar() {
		this.ingredientes.removeAll(ingredientes);
	}
}
