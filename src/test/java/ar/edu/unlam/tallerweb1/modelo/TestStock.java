package ar.edu.unlam.tallerweb1.modelo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Ingrediente.TipoIngrediente;

public class TestStock {
	
	private Stock stock = Stock.getInstance();
	private Ingrediente condimento = new Ingrediente("Mayonesa",1.5,TipoIngrediente.CONDIMENTO);
	private Ingrediente ingrediente = new Ingrediente("Jamon",3.,TipoIngrediente.INGREDIENTE);
	
	@After
	public void eliminarResiduosStock(){
		stock.eliminarIngrediente(ingrediente);
		stock.eliminarIngrediente(condimento);
	}
	
	@Test
	public void testAgregarIngrediente() {
		stock.agregarIngrediente(ingrediente);
		assertTrue(stock.listarIngredientes().contains(ingrediente));
	}
	
	@Test
	public void testEliminarIngrediente() {
		stock.agregarIngrediente(ingrediente);
		stock.eliminarIngrediente(ingrediente);
		assertTrue(!stock.listarIngredientes().contains(ingrediente));
	}
	@Test
	public void testAgregarStockIngrediente() {
		stock.agregarIngrediente(ingrediente);
		stock.agregarStock(ingrediente,10);
		assertEquals((Integer)10,stock.obtenerStock().get(ingrediente));
	}
	@Test
	public void testVaciarStockIngrediente() {
		stock.agregarIngrediente(ingrediente);
		stock.agregarStock(ingrediente,100);
		stock.vaciarStock(ingrediente);
		assertEquals((Integer)0,stock.obtenerStock().get(ingrediente));
	}
	@Test
	public void testComprarIngrediente() {
		stock.agregarIngrediente(ingrediente);
		stock.agregarStock(ingrediente,100);
		stock.comprarIngrediente(ingrediente, 3);
		assertEquals((Integer)97,stock.obtenerStock().get(ingrediente));
	}
	@Test
	public void testComprarIngredienteSinStock() {
		stock.agregarIngrediente(ingrediente);
		stock.agregarStock(ingrediente,5);
		assertTrue(!stock.comprarIngrediente(ingrediente, 6));
	}
	@Test
	public void testListarIngredientesDisponibles() {
		stock.agregarIngrediente(ingrediente);
		stock.agregarStock(ingrediente,5);
		assertTrue(stock.listarIngredientesDisponibles().contains(ingrediente));
	}
	
	@Test
	public void testObtenerStockDisponible() {
		stock.agregarIngrediente(ingrediente);
		stock.agregarStock(ingrediente,10);
		assertEquals((Integer)10,stock.obtenerStockDisponible(ingrediente));
	}
}
