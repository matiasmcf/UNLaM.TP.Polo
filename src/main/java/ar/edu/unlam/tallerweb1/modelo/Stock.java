package ar.edu.unlam.tallerweb1.modelo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Maneja un stock de ingredientes, el mismo puede ser asociado a una cantidad.<br>
 * No persiste, es decir, luego de la ejecucion del programa el Stock se inicialza vacio.<br>
 * 
 * @author sismael
 *
 */
public class Stock {

	private static Stock				instance		= new Stock();
	private Map<Ingrediente, Integer>	stock			= new HashMap<Ingrediente, Integer>();
	private static boolean				inicializado	= false;

	private Stock() {
	}

	public static Stock getInstance() {
		if (inicializado == false) {
			SQLiteDatabase.getInstance().cargarIngredientes(instance);
			inicializado = true;
		}
		return instance;
	}

	/**
	 * Devuelve un listado de los ingredientes del stock, tengan o no stock, es decir, los ingredientes con cantidad 0 son incluidos.<br>
	 * 
	 * @param producto
	 * @param cantidad
	 * @return
	 */
	public Set<Ingrediente> listarIngredientesDisponibles() {
		HashSet<Ingrediente> ingredientesDisponibles = new HashSet<Ingrediente>();
		for (Ingrediente ingrediente: stock.keySet())
			if (!stock.get(ingrediente).equals(0))
				ingredientesDisponibles.add(ingrediente);
		return ingredientesDisponibles;
	}

	public Set<Ingrediente> listarIngredientes() {
		return stock.keySet();
	}

	/**
	 * Devuelve un mapa con los ingredientes y su stock correspondiente, tengan o no stock, es decir, los ingredientes con cantidad 0 son incluidos.<br>
	 * 
	 * @param producto
	 * @param cantidad
	 * @return
	 */
	public Map<Ingrediente, Integer> obtenerStock() {
		return stock;
	}

	/**
	 * Permite agregar el ingrediente indicado al stock, con cantidad 0.<br>
	 * 
	 * @param ingrediente
	 * @param cantidad
	 * @return true en caso de exito, false si el ingrediente ya existe en el stock.<br>
	 */
	public Boolean agregarIngrediente(Ingrediente ingrediente) {
		if (this.stock.containsKey(ingrediente)) {
			return false;
		}
		this.stock.put(ingrediente, 0);
		return true;
	}

	/**
	 * Permite agregar stock al existente para un ingrediente dado. Si el ingrediente tiene un stock de N, ahora tendra N + cantidad.<br>
	 * 
	 * @param ingrediente
	 * @param cantidad
	 * @return true en caso de exito, false si el ingrediente no existe en el stock.<br>
	 */
	public Boolean agregarStock(Ingrediente ingrediente, Integer cantidad) {
		if (!this.stock.containsKey(ingrediente)) {
			return false;
		}
		Integer nuevaCantidad = this.stock.get(ingrediente) + cantidad;
		this.stock.put(ingrediente, nuevaCantidad);
		return true;
	}

	/**
	 * Devuelve el stock disponible para el ingrediente pedido. NULL si el ingrediente no existe en el stock<br>
	 * 
	 * @param ingrediente
	 * @return
	 */
	public Integer obtenerStockDisponible(Ingrediente ingrediente) {
		if (!this.stock.containsKey(ingrediente)) {
			return null;
		}
		return this.stock.get(ingrediente);
	}

	/**
	 * Indica si el ingrediente indicado fue incluido en el stock.<br>
	 * 
	 * @param ingrediente
	 * @return
	 */
	public Boolean existeIngrediente(Ingrediente ingrediente) {
		return this.stock.containsKey(ingrediente);
	}

	/**
	 * Permite comprar N unidades del ingrediente indicado.<br>
	 * 
	 * @param ingrediente
	 * @param unidades
	 * @return true en caso de exito, false si el ingrediente no existe en el stock.<br>
	 */
	public Boolean comprarIngrediente(Ingrediente ingrediente, Integer unidades) {
		// System.out.println(ingrediente.getNombre());
		if (!this.stock.containsKey(ingrediente) || stock.get(ingrediente).equals(0)) {
			return false;
		}
		Integer nuevaCantidad = this.stock.get(ingrediente) - unidades;
		this.stock.put(ingrediente, nuevaCantidad);
		return true;
	}

	/**
	 * Elimina el ingrediente indicado del stock, no importa cual sea la cantidad disponible del mismo.<br>
	 * 
	 * @param ingrediente
	 * @return true en caso de exito, false si el ingrediente no existe en el stock.<br>
	 */
	public Boolean eliminarIngrediente(Ingrediente ingrediente) {
		if (!this.stock.containsKey(ingrediente)) {
			return false;
		}
		this.stock.remove(ingrediente);
		return true;
	}
}
