package ar.edu.unlam.tallerweb1.modelo;

import java.util.HashMap;

/**
 * Clase que permite el manejo de múltiples usuarios simuláeneamente.<br>
 * Asocia un <code>Sanguchetto</code> a cada usuario que inicie sesión.<br>
 * Es <i>Singleton</i>, por lo que debe accederse mediante <code>Usuarios.getInstance()</code>.<br>
 */
public class Usuarios {

	private static Usuarios					instance		= new Usuarios();
	private HashMap<String, Sanguchetto>	usuariosActivos	= new HashMap<String, Sanguchetto>();

	private Usuarios() {
	};

	/**
	 * Obtiene la instancia.
	 */
	public static Usuarios getInstance() {
		return instance;
	}

	/**
	 * Agrega un cliente a la lista de usuarios activos, asociándolo a un <code>Sanguchetto</code><br>
	 * Retorna <code>false</code> en caso de que el usuario ya haya iniciado sesión.
	 * 
	 * @param usuario
	 *            -Usuario a ser agregado.<br>
	 *            <br>
	 * 
	 */
	public boolean agregarUsuario(Usuario usuario) {
		if (usuariosActivos.containsKey(usuario.getUsername()))
			return false;
		usuariosActivos.put(usuario.getUsername(), new Sanguchetto());
		return true;
	}

	/**
	 * Elimina el usuario de la lista de usuarios activos.
	 * 
	 * @param usuario
	 *            -Usuario a eliminar.
	 */
	public void quitarUsuario(Usuario usuario) {
		usuariosActivos.remove(usuario.getUsername());
	}

	/**
	 * Retorna el <code>Sanguchetto</code> asociado al usuario.
	 */
	public Sanguchetto obtenerSanguche(Usuario usuario) {
		if (!usuariosActivos.containsKey(usuario.getUsername()))
			System.out.println("[Usuarios.obtenerSanguche()] - No existe el sanguche.");
		return usuariosActivos.get(usuario.getUsername());
	}

}
