package ar.edu.unlam.tallerweb1.modelo;

import java.util.HashMap;

public class Usuarios {

	private static Usuarios					instance		= new Usuarios();
	private HashMap<String, Sanguchetto>	usuariosActivos	= new HashMap<String, Sanguchetto>();

	private Usuarios() {
	};

	public static Usuarios getInstance() {
		return instance;
	}

	public boolean agregarUsuario(Usuario usuario, Sanguchetto sanguche) {
		if (usuariosActivos.containsKey(usuario))
			return false;
		usuariosActivos.put(usuario.getUsername(), sanguche);
		return true;
	}

	public void quitarUsuario(Usuario usuario) {
		usuariosActivos.remove(usuario);
	}

	public Sanguchetto obtenerSanguche(Usuario usuario) {
		if (!usuariosActivos.containsKey(usuario.getUsername()))
			System.out.println("[Usuarios.obtenerSanguche()] - No existe el sanguche.");
		return usuariosActivos.get(usuario.getUsername());
	}

}
