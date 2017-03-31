package ar.edu.unlam.tallerweb1.modelo;

/**
 * Clase que relaciona un determinado ingrediente a una cantidad.<br>
 * Utilizada para la compra de stock.
 */
public class IngredienteCantidad {

	private int		cantidad;
	private String	nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
