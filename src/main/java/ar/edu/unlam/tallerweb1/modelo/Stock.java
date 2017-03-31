package ar.edu.unlam.tallerweb1.modelo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Maneja un stock de ingredientes asociando cada <code>Ingrediente</code> a una <code>cantidad</code>.<br>
 * Al iniciar, carga los datos desde una base de datos. <br>
 * Ver: {@link SQLiteDatabase}.<br>
 * Es <i>Singleton</i>, por lo que debe accederse mediante <code>Stock.getInstance()</code>.<br>
 */
public class Stock {

	private static Stock				instance		= new Stock();
	private Map<Ingrediente, Integer>	stock			= new HashMap<Ingrediente, Integer>();
	private static boolean				inicializado	= false;

	private Stock() {
	}

	/**
	 * Obtiene la instancia.
	 */
	public static Stock getInstance() {
		if (inicializado == false) {
			SQLiteDatabase.getInstance().cargarIngredientes(instance);
			inicializado = true;
		}
		return instance;
	}

	/**
	 * Devuelve un listado de los ingredientes que tengan stock mayor a 0 (cero).
	 */
	public Set<Ingrediente> listarIngredientesDisponibles() {
		HashSet<Ingrediente> ingredientesDisponibles = new HashSet<Ingrediente>();
		for (Ingrediente ingrediente: stock.keySet())
			if (!stock.get(ingrediente).equals(0))
				ingredientesDisponibles.add(ingrediente);
		return ingredientesDisponibles;
	}

	/**
	 * Devuelve un listado con todos los ingredientes, tengan o no stock disponible.
	 */
	public Set<Ingrediente> listarIngredientes() {
		return stock.keySet();
	}

	/**
	 * Devuelve un mapa con los ingredientes y su stock correspondiente, tengan o no stock disponible.<br>
	 * 
	 * @param producto
	 * @param cantidad
	 * @return
	 */
	public Map<Ingrediente, Integer> obtenerStock() {
		return stock;
	}

	/**
	 * Agrega el ingrediente indicado al stock, con cantidad 0 (cero).<br>
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
	 * @return true en caso de exito, false si el ingrediente no exista en el stock.<br>
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
	 * Retorna el stock disponible para el ingrediente pedido. Retorna <b><code>null</code></b> si el ingrediente no existe en el stock<br>
	 * 
	 * @param ingrediente
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
	 */
	public Boolean existeIngrediente(Ingrediente ingrediente) {
		return this.stock.containsKey(ingrediente);
	}

	/**
	 * Permite comprar N unidades del ingrediente indicado. Disminuye el stock del mismo.<br>
	 * 
	 * @param ingrediente
	 * @param unidades
	 * @return true en caso de exito, false si el ingrediente no existe en el stock.<br>
	 */
	public Boolean comprarIngrediente(Ingrediente ingrediente, Integer unidades) {
		if (!this.stock.containsKey(ingrediente) || stock.get(ingrediente).compareTo(unidades) < 0) {
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

	/**
	 * Pone en cero el stock del ingrediente indicado.
	 * 
	 * @param ingrediente
	 * @return true en caso de exito, false si el ingrediente no existe en el stock.<br>
	 */
	public boolean vaciarStock(Ingrediente ingrediente) {
		if (!this.stock.containsKey(ingrediente)) {
			return false;
		}
		this.stock.put(ingrediente, 0);
		return true;
	}
}
