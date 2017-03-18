package ar.edu.unlam.tallerweb1.modelo;

public class User {

	private String	username;
	private String	password;

	public User(String nombre, String apellido) {
		this.username = nombre;
		this.password = apellido;
	}

	public User() {
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
