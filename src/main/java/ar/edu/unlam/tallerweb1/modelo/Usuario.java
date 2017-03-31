package ar.edu.unlam.tallerweb1.modelo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Usuario {

	public enum TipoUsuario {
		ADMIN,
		CLIENTE
	}

	@NotNull(
			message = "asd")
	@Size(
			min = 4,
			max = 15,
			message = "El nombre de usuario debe contener de 4 a 10 caracteres")
	private String		username;

	@NotNull
	@Size(
			min = 4,
			max = 15,
			message = "La contraseña debe contener de 4 a 10 caracteres")
	private String		password;
	private TipoUsuario	tipo;
	private boolean		accion;		// true=inicio sesion - false=registro

	public boolean isAccion() {
		return accion;
	}

	public void setAccion(boolean accion) {
		this.accion = accion;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public Usuario(String nombre, String password) {
		this.username = nombre;
		this.password = password;
		this.tipo = TipoUsuario.CLIENTE;
	}

	public Usuario(String nombre, TipoUsuario tipo) {
		this.username = nombre;
		this.tipo = tipo;
		this.password = "";
	}

	public Usuario() {
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
		return true;
	}
}
