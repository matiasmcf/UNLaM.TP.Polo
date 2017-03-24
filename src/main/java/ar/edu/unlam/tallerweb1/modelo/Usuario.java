package ar.edu.unlam.tallerweb1.modelo;

public class Usuario {

	public enum TipoUsuario {
		ADMIN,
		CLIENTE
	}

	private String		username;
	private String		password;
	private TipoUsuario	tipo;

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public Usuario(String nombre, String apellido) {
		this.username = nombre;
		this.password = apellido;
		this.tipo = TipoUsuario.CLIENTE;
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
}
