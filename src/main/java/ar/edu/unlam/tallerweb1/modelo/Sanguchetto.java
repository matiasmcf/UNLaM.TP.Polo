package ar.edu.unlam.tallerweb1.modelo;

import java.util.LinkedList;
import java.util.List;

public class Sanguchetto {

	private static Sanguchetto instance = new Sanguchetto();
	private List<Ingrediente> ingredientes = new LinkedList<Ingrediente>();
	
	private Sanguchetto(){}
	
	public static Sanguchetto getInstance(){
		return instance;
	}
	
	/**
	 * Elimina todos los ingredientes del sanguchetto.<br>
	 */
	public void vaciar(){
		ingredientes=new LinkedList<Ingrediente>();
	}
	
	/**
	 * Agrega un ingrediente al carrito.<br>
	 * @param ingrediente
	 */
	public void agregarIngrediente(Ingrediente ingrediente){
		Stock stock= Stock.getInstance();
		if(stock.comprarIngrediente(ingrediente,1)){
			ingredientes.add(ingrediente);
		}
		
	}
	
	/**
	 * Lista todos los ingredientes que forman parte del sanguchetto.<br>
	 * @return
	 */
	public List<Ingrediente> verIngredientes(){
		List<Ingrediente> ingredientesSinCondimentos=new LinkedList<Ingrediente>();
		for (Ingrediente ingrediente : ingredientes) {
			if(ingrediente.getTipo().equals(TipoIngrediente.INGREDIENTE)){
				ingredientesSinCondimentos.add(ingrediente);
			}
		}
		return ingredientesSinCondimentos;
	}
	
	/**
     * Lista todos los condimentos que forman parte del sanguchetto.<br>
     * @return
     */
    public List<Ingrediente> verCondimentos(){
		List<Ingrediente> condimentos=new LinkedList<Ingrediente>();
		for (Ingrediente ingrediente : ingredientes) {
			if(ingrediente.getTipo().equals(TipoIngrediente.CONDIMENTO)){
				condimentos.add(ingrediente);
			}
		}
		return condimentos;
	}
	
	/**
	 * Devuelve el precio total del sanguchetto.<br>
	 * @return
	 */
	public Double getPrecio(){
		Double precio=0.;
		for (Ingrediente ingrediente : ingredientes) {
			precio+=ingrediente.getPrecio();
		}
		return precio;
	}
}
