package ar.edu.unlam.tallerweb1.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSQLiteDatabase {

	private GestionMysqlDB database;

	@Before
	public void inicializar() {
		database = GestionMysqlDB.getInstance();
	}

	@After
	public void finalizar() {
	}

	@Test
	public void testAltaBajaUsuarios() {
		assertTrue(database.registrarUsuario("abc1", "abc1"));
		assertTrue(database.eliminarUsuario(new Usuario("abc1", "abc1")));
	}

	@Test
	public void testInicioSesion() {
		Usuario usuario = new Usuario("test", "test");
		assertTrue(database.registrarUsuario("test", "test"));
		assertEquals(database.verificarDatos(usuario), 0);
		assertTrue(database.eliminarUsuario(usuario));
	}

	@Test
	public void testCargaDeIngredientes() {
		database.cargarIngredientes(Stock.getInstance());
		assertTrue(Stock.getInstance().listarIngredientes().size() > 0);
	}

	@Test
	public void testAltaBajaIngrediente() {
		Ingrediente ingrediente = new Ingrediente("ingredienteTest", 1.5, Ingrediente.TipoIngrediente.INGREDIENTE);
		assertTrue(database.insertarIngrediente(Stock.getInstance(), ingrediente));
		assertTrue(database.eliminarIngrediente(ingrediente));
	}
}
