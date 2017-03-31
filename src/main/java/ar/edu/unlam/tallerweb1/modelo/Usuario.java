package ar.edu.unlam.tallerweb1.modelo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Cliente
 */
public class Usuario {

	public enum TipoUsuario {
		ADMIN,
		CLIENTE
	}

	@NotNull(
			message = "El nombre de usuario no puede estar en blanco.")
	@Size(
			min = 4,
			max = 15,
			message = "El nombre de usuario debe contener de 4 a 10 caracteres.")
	private String		username;

	@NotNull(
			message = "La contraseña no puede estar en blanco.")
	@Size(
			min = 4,
			max = 15,
			message = "La contraseña debe contener de 4 a 10 caracteres.")
	private String		password;
	private TipoUsuario	tipo;

	public Usuario() {
	}

	/**
	 * Crea un usuario del tipo <code>CLIENTE</code>.
	 * 
	 * @param nombre
	 * @param password
	 */
	public Usuario(String nombre, String password) {
		this.username = nombre;
		this.password = password;
		this.tipo = TipoUsuario.CLIENTE;
	}

	/**
	 * Crea un usuario especificando el tipo. Se utiliza una vez iniciada sesión, donde ya no interesa la contraseña.
	 * 
	 * @param nombre
	 * @param tipo
	 */
	public Usuario(String nombre, TipoUsuario tipo) {
		this.username = nombre;
		this.tipo = tipo;
		this.password = "";
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String nombre) {
		this.username = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String apellido) {
		this.password = apellido;
	}

	public void clonar(Usuario usuario) {
		this.username = usuario.username;
		this.tipo = usuario.tipo;
		this.password = usuario.password;
	}

	@Override
	public boolean equals(Object obj) {
		Usuario other = (Usuario)obj;
		if (username.equals(other.username))
			return true;
		return false;
	}
}
