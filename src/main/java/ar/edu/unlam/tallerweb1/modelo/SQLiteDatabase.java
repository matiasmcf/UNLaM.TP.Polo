package ar.edu.unlam.tallerweb1.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ar.edu.unlam.tallerweb1.modelo.Ingrediente.TipoIngrediente;
import ar.edu.unlam.tallerweb1.modelo.Usuario.TipoUsuario;

/**
 * Objeto que permite conectarse a una base de datos y realizar consultas sobre la misma
 */
public class SQLiteDatabase {

	private static SQLiteDatabase database;

	public static void setDatabase(SQLiteDatabase database) {
		SQLiteDatabase.database = database;
	}

	private String	databaseDriver	= "jdbc:sqlite:";
	private String	databaseURL;

	public void setDatabaseURL(String databaseURL) {
		this.databaseURL = databaseURL;
	}

	private SQLiteDatabase() {

	}

	public static SQLiteDatabase getInstance() {
		return database;
	}

	/**
	 * Ejecuta una consulta en la base de datos
	 * 
	 * @param query
	 *            (consulta)
	 * @return si la cunsulta fue exitosa o no.
	 */
	public boolean consultar(String query) {
		boolean estado = false;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexion = DriverManager.getConnection(databaseDriver + databaseURL);
			PreparedStatement pst = conexion.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			if (!rs.next())
				estado = false;
			else
				estado = true;
			pst.close();
			rs.close();
			conexion.close();
		}
		catch (SQLException ex) {
			System.out.println("consultar() - No se pudo lograr la conexion con la base de datos");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return estado;
	}

	/**
	 * Busca un usuario en la base de datos. En caso de que la información de inicio de sesión sea correcta, carga el tipo de usuario y devuelve <b>true</b>, en caso contrario devuelve <b>false</b>.
	 */
	public boolean verificarDatos(Usuario usuario) {
		String query = "SELECT * FROM USUARIO WHERE nombre='" + usuario.getUsername() + "' AND password='" + usuario.getPassword() + "'";
		boolean estado = false;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexion = DriverManager.getConnection(databaseDriver + databaseURL);
			PreparedStatement pst = conexion.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			if (!rs.next())
				estado = false;
			else {
				estado = true;
				usuario.setTipo(rs.getString("tipo").equals("admin") ? TipoUsuario.ADMIN : TipoUsuario.CLIENTE);
				Usuarios.getInstance().agregarUsuario(usuario, new Sanguchetto("Sanguche-" + usuario.getUsername()));
			}
			pst.close();
			rs.close();
			conexion.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("verificarDatos() - Error usuario y contraseña");
		}
		return estado;
	}

	/**
	 * @param usuario
	 * @return si el usuario existe o no en la base de datos.
	 */
	private boolean existeUsuario(String usuario) {
		String query = "SELECT * FROM USUARIO WHERE nombre='" + usuario + "'";
		boolean estado = false;
		try {
			estado = consultar(query);
		}
		catch (Exception ex) {
			System.out.println("existeUsuario() - Error al comprobar existencia usuario");
		}
		return estado;
	}

	/**
	 * Registra un usuario en la base de datos
	 * 
	 * @param usuario
	 * @param password
	 */
	public boolean registrarUsuario(String usuario, String password) {
		if (existeUsuario(usuario) == true)
			return false;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexion = DriverManager.getConnection(databaseDriver + databaseURL);
			Statement st = conexion.createStatement();
			String query = "Insert into Usuario values('" + usuario + "','" + password +"','"+"cliente"+ "')";
			st.executeUpdate(query);
			st.close();
			conexion.close();
			return true;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("registrarUsuario() - No se pudo lograr la coneccion con la base de datos");
			return false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void cargarIngredientes(Stock stock) {
		System.out.println("Cargando ingredientes");
		String sql = "SELECT * FROM Stock";
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexion = DriverManager.getConnection(databaseDriver + databaseURL);
			PreparedStatement pst = conexion.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				stock.agregarIngrediente(
						new Ingrediente(rs.getString("ingrediente"), (double)rs.getFloat("precio"), rs.getInt("tipo") == 0 ? TipoIngrediente.INGREDIENTE : TipoIngrediente.CONDIMENTO));
				stock.agregarStock(new Ingrediente(rs.getString("ingrediente")), rs.getInt("stock"));
				System.out.println("Cargado: " + rs.getString("ingrediente"));
			}
			pst.close();
			rs.close();
			conexion.close();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("cargarIngredientes() - No se pudo lograr la conexion con la base de datos");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean insertarIngrediente(Stock stock, Ingrediente ingrediente) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexion = DriverManager.getConnection(databaseDriver + databaseURL);
			Statement st = conexion.createStatement();
			String query = "Insert into Stock values('" + ingrediente.getNombre() + "','" + ingrediente.getPrecio() + "','" + stock.obtenerStockDisponible(ingrediente) + "','"
					+ (ingrediente.getTipo().equals(TipoIngrediente.INGREDIENTE) == true ? "0" : "1") + "')";
			st.executeUpdate(query);
			st.close();
			conexion.close();
			return true;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("registrarUsuario() - No se pudo lograr la coneccion con la base de datos");
			return false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean actualizarStockIngrediente(Stock stock, Ingrediente ingrediente) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexion = DriverManager.getConnection(databaseDriver + databaseURL);
			Statement st = conexion.createStatement();
			String query = "Update Stock set stock='" + stock.obtenerStockDisponible(ingrediente) + "' where ingrediente='" + ingrediente.getNombre() + "'";
			st.executeUpdate(query);
			st.close();
			conexion.close();
			return true;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("registrarUsuario() - No se pudo lograr la coneccion con la base de datos");
			return false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean eliminarIngrediente(Ingrediente ingrediente) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexion = DriverManager.getConnection(databaseDriver + databaseURL);
			Statement st = conexion.createStatement();
			String query = "Delete from Stock where ingrediente='" + ingrediente.getNombre() + "'";
			st.executeUpdate(query);
			st.close();
			conexion.close();
			return true;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("registrarUsuario() - No se pudo lograr la coneccion con la base de datos");
			return false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
}
