package ar.edu.unlam.tallerweb1.modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestDatabase {

	private SQLiteDatabase	db	= SQLiteDatabase.getInstace();
	private Usuario			usuario;

	@Test
	public void testInicioDeSesionExitoso() {
		usuario = new Usuario("admin", "admin");
		assertEquals(true, db.verificarDatos(usuario));
		usuario = new Usuario("yo", "yo");
		assertEquals(true, db.verificarDatos(usuario));
	}

	@Test
	public void testInicioDeSesionFallido() {
		usuario = new Usuario("admin1", "admin");
		assertEquals(false, db.verificarDatos(usuario));
		usuario = new Usuario("yo", "yo1");
		assertEquals(false, db.verificarDatos(usuario));
	}

}
