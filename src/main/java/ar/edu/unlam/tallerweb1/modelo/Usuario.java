package ar.edu.unlam.tallerweb1.modelo;

public class Usuario {

	public enum TipoUsuario {
		ADMIN, CLIENTE
	}

	private String username;
	private String password;
	private TipoUsuario tipo;
	private boolean accion; //true=inicio sesion - false=registro

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
		Usuario other = (Usuario) obj;
		if (username.equals(other.username))
			return true;
		return true;
	}
}
