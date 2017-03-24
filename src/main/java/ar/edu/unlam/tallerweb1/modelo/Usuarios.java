package ar.edu.unlam.tallerweb1.modelo;

import java.util.HashMap;

public class Usuarios {

	private static Usuarios					instance		= new Usuarios();
	private HashMap<Usuario, Sanguchetto>	usuariosActivos	= new HashMap<Usuario, Sanguchetto>();

	private Usuarios() {
	};

	public static Usuarios getInstance() {
		return instance;
	}

	public boolean agregarUsuario(Usuario usuario, Sanguchetto sanguche) {
		if (!usuariosActivos.containsKey(usuario))
			return false;
		usuariosActivos.put(usuario, sanguche);
		return true;
	}

	public void quitarUsuario(Usuario usuario) {
		usuariosActivos.remove(usuario);
	}

	public Sanguchetto obtenerSanguche(Usuario usuario) {
		return usuariosActivos.get(usuario);
	}

}
