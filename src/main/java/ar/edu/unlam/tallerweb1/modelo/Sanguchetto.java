package ar.edu.unlam.tallerweb1.modelo;

import java.util.LinkedList;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Ingrediente.TipoIngrediente;

public class Sanguchetto {

	private List<Ingrediente> ingredientes;
	private String nombre;
	private String descuento;

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	public String getDescuento() {
		return descuento;
	}
	
	public String getPorcentajeDescuento(){
		String res=String.format("%d",(int) (Descuento.getInstance().obtenerDescuento(descuento)*100));
		return res+"%";
	}

	public Sanguchetto() {
		nombre = "SinNombre";
		ingredientes = new LinkedList<Ingrediente>();
	}

	public Sanguchetto(String nombre) {
		this.nombre = nombre;
		ingredientes = new LinkedList<Ingrediente>();
	}

	/**
	 * Elimina todos los ingredientes del sanguchetto.<br>
	 */
	public void vaciar() {
		for (Ingrediente ingrediente : ingredientes)
			Stock.getInstance().agregarStock(ingrediente, 1);
		this.ingredientes.removeAll(ingredientes);
	}

	/**
	 * Agrega un ingrediente al carrito.<br>
	 * 
	 * @param ingrediente
	 */
	public void agregarIngrediente(Ingrediente ingrediente) {
		Stock stock = Stock.getInstance();
		if (stock.comprarIngrediente(ingrediente, 1)) {
			ingredientes.add(ingrediente);
		}

	}

	public void quitarIngrediente(Ingrediente ingrediente) {
		Stock stock = Stock.getInstance();
		ingredientes.remove(ingrediente);
		stock.agregarStock(ingrediente, 1);
	}

	/**
	 * Lista todos los ingredientes que forman parte del sanguchetto.<br>
	 * 
	 * @return
	 */
	public List<Ingrediente> verIngredientes() {
		List<Ingrediente> ingredientesSinCondimentos = new LinkedList<Ingrediente>();
		for (Ingrediente ingrediente : ingredientes) {
			if (ingrediente.getTipo().equals(TipoIngrediente.INGREDIENTE)) {
				ingredientesSinCondimentos.add(ingrediente);
			}
		}
		return ingredientesSinCondimentos;
	}

	/**
	 * Lista todos los condimentos que forman parte del sanguchetto.<br>
	 * 
	 * @return
	 */
	public List<Ingrediente> verCondimentos() {
		List<Ingrediente> condimentos = new LinkedList<Ingrediente>();
		for (Ingrediente ingrediente : ingredientes) {
			if (ingrediente.getTipo().equals(TipoIngrediente.CONDIMENTO)) {
				condimentos.add(ingrediente);
			}
		}
		return condimentos;
	}

	/**
	 * Devuelve el precio total del sanguchetto.<br>
	 * 
	 * @return
	 */
	public Double getPrecio() {
		Double precio = 0.;
		for (Ingrediente ingrediente : ingredientes) {
			precio += ingrediente.getPrecio();
		}
		return precio-precio*Descuento.getInstance().obtenerDescuento(descuento);
	}
	
	/**
	 * Devuelve el precio total del sanguchetto sin descuento.<br>
	 * 
	 * @return
	 */
	public Double getPrecioSinDescuento() {
		Double precio = 0.;
		for (Ingrediente ingrediente : ingredientes) {
			precio += ingrediente.getPrecio();
		}
		return precio;
	}

	public void comprar() {
		this.ingredientes.removeAll(ingredientes);
	}

	@Override
	public String toString() {
		return nombre;
	}
}
