package ar.edu.unlam.tallerweb1.modelo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Ingrediente.TipoIngrediente;

public class TestSanguchetto {

	private Sanguchetto sanguche;
	private Ingrediente condimento = new Ingrediente("Prueba1",1.5,TipoIngrediente.CONDIMENTO);
	private Ingrediente ingrediente = new Ingrediente("Prueba2",3.,TipoIngrediente.INGREDIENTE);
	
	@Before
	public void inicializar(){
		Stock.getInstance().agregarIngrediente(ingrediente);
		Stock.getInstance().agregarStock(ingrediente, 10);
		Stock.getInstance().agregarIngrediente(condimento);
		Stock.getInstance().agregarStock(condimento, 10);
		sanguche=new Sanguchetto();
	}
	@After
	public void eliminarResiduosDB(){
		Stock.getInstance().eliminarIngrediente(ingrediente);
		Stock.getInstance().eliminarIngrediente(condimento);
	}
	
	
	@Test
	public void testAgregarIngrediente() {
		int sizeAnt= sanguche.verCondimentos().size();
		sanguche.agregarIngrediente(condimento);
		assertTrue(sanguche.verCondimentos().size()==sizeAnt+1);
	}
	@Test
	public void testAgregarIngredienteSinStock() {
		Stock.getInstance().vaciarStock(condimento);
		int sizeAnt= sanguche.verCondimentos().size();
		sanguche.agregarIngrediente(condimento);
		assertTrue(sanguche.verCondimentos().size()==sizeAnt);
	}
	@Test
	public void testQuitarIngrediente() {
		int sizeAnt= sanguche.verCondimentos().size();
		sanguche.agregarIngrediente(condimento);
		sanguche.quitarIngrediente(condimento);
		assertTrue(sanguche.verCondimentos().size()==sizeAnt);
	}
	
	
	@Test
	public void testVerCondimento() {
		sanguche.agregarIngrediente(condimento);
		assertTrue(sanguche.verCondimentos().get(0).equals(condimento));
	}
	@Test
	public void testVerIngrediente() {
		sanguche.agregarIngrediente(ingrediente);
		assertTrue(sanguche.verIngredientes().get(0).equals(ingrediente));
	}
	@Test
	public void testVaciarSanguche() {
		sanguche.agregarIngrediente(ingrediente);
		sanguche.vaciar();
		assertTrue(sanguche.verCondimentos().size()==0&&sanguche.verIngredientes().size()==0&&Stock.getInstance().obtenerStock().get(ingrediente).equals(10));
	}
	@Test
	public void testComprarSanguche() {
		sanguche.agregarIngrediente(ingrediente);
		sanguche.comprar();
		assertTrue(sanguche.verCondimentos().size()==0&&sanguche.verIngredientes().size()==0&&Stock.getInstance().obtenerStock().get(ingrediente).equals(9));
	}

}
