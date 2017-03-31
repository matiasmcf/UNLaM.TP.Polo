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
 * Clase que permite conectarse a una base de datos y realizar consultas sobre la misma.<br>
 * Es <i>Singleton</i>, por lo que debe accederse mediante <code>SQLiteDatabase.getInstance()</code>.<br>
 */
public class SQLiteDatabase {

	private static SQLiteDatabase	database		= new SQLiteDatabase();
	private String					databaseDriver	= "jdbc:sqlite:";
	private String					databaseURL		= "src/main/resources/database/database.sqlite";

	/**
	 * Método utilizado para cargar la configuración de la base de datos desde el archivo <b><i>spring-servel.xml</i></b>.
	 * 
	 * @param database
	 */
	public static void setDatabase(SQLiteDatabase database) {
		SQLiteDatabase.database = database;
	}

	/**
	 * Método utilizado para cargar la configuración de la base de datos desde el archivo <b><i>spring-servel.xml</i></b>.
	 * 
	 * @param databaseURL
	 */
	public void setDatabaseURL(String databaseURL) {
		this.databaseURL = databaseURL;
	}

	private SQLiteDatabase() {
	}

	/**
	 * Obtiene la instacia.
	 */
	public static SQLiteDatabase getInstance() {
		return database;
	}

	/**
	 * Ejecuta una consulta sobre la base de datos.
	 * 
	 * @param query
	 *            -Consulta a ejecutar.
	 * @return <code>true</code> si la cunsulta fue exitosa, <code>false</code> en caso contrario.
	 */
	private boolean consultar(String query) {
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
	 * Busca un usuario en la base de datos. <br>
	 * Agrega el usuario a la lista de usuarios activos. <br>
	 * Retorna:
	 * <ol>
	 * • 0: éxito.<br>
	 * • 1: datos de inicio de sesión incorrectos.<br>
	 * • 2: el usuario ya está activo.
	 * </ol>
	 */
	public int verificarDatos(Usuario usuario) {
		String query = "SELECT * FROM USUARIO WHERE nombre='" + usuario.getUsername() + "' AND password='" + usuario.getPassword() + "'";
		int estado = 1;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexion = DriverManager.getConnection(databaseDriver + databaseURL);
			PreparedStatement pst = conexion.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			if (!rs.next())
				estado = 1;
			else {
				estado = 0;
				usuario.setTipo(rs.getString("tipo").equals("admin") ? TipoUsuario.ADMIN : TipoUsuario.CLIENTE);
				if (Usuarios.getInstance().agregarUsuario(usuario))
					estado = 0;
				else
					estado = 2;
			}
			pst.close();
			rs.close();
			conexion.close();
		}
		catch (Exception ex) {
			System.out.println("verificarDatos() - Error usuario y contraseña");
		}
		return estado;
	}

	/**
	 * Informa si el usuario existe en la base de datos.
	 * 
	 * @param usuario
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
			String query = "Insert into Usuario values('" + usuario + "','" + password + "','" + "cliente" + "')";
			st.executeUpdate(query);
			st.close();
			conexion.close();
			return true;
		}
		catch (SQLException sqle) {
			System.out.println("registrarUsuario() - No se pudo lograr la coneccion con la base de datos");
			return false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Carga los ingredientes al {@link Stock}.
	 * 
	 * @param stock
	 */
	public void cargarIngredientes(Stock stock) {
		System.out.println("Cargando ingredientes");
		String sql = "SELECT * FROM Stock";
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexion = DriverManager.getConnection(databaseDriver + databaseURL);
			PreparedStatement pst = conexion.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				stock.agregarIngrediente(new Ingrediente(rs.getString("ingrediente"), rs.getDouble("precio"), rs.getInt("tipo") == 0 ? TipoIngrediente.INGREDIENTE : TipoIngrediente.CONDIMENTO));
				stock.agregarStock(new Ingrediente(rs.getString("ingrediente")), rs.getInt("stock"));
				System.out.println("Cargado: " + rs.getString("ingrediente"));
			}
			pst.close();
			rs.close();
			conexion.close();
		}
		catch (SQLException ex) {
			System.out.println("cargarIngredientes() - No se pudo lograr la conexion con la base de datos");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Agrega un ingrediente a la base de datos.<br>
	 * Retorna <b>true</b> en caso de éxito, <b>false</b> en caso contrario.
	 * 
	 * @param stock
	 *            -Necesario para obtener la cantidad disponible.
	 * @param ingrediente
	 */
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
			System.out.println("insertarIngrediente() - No se pudo lograr la coneccion con la base de datos");
			return false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Actualiza el stock del ingrediente en la base de datos.<br>
	 * Retorna <b>true</b> en caso de éxito, <b>false</b> en caso contrario.
	 * 
	 * @param stock
	 *            -Necesario para obtener la cantidad disponible.
	 * @param ingrediente
	 */
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
			System.out.println("actualizarStockIngrediente() - No se pudo lograr la coneccion con la base de datos");
			return false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Actualiza el precio del ingrediente en la base de datos.<br>
	 * Retorna <b>true</b> en caso de éxito, <b>false</b> en caso contrario.
	 * 
	 * @param precio
	 * @param ingrediente
	 */
	public boolean actualizarPrecioIngrediente(Double precio, Ingrediente ingrediente) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexion = DriverManager.getConnection(databaseDriver + databaseURL);
			Statement st = conexion.createStatement();
			String query = "Update Stock set precio='" + precio + "' where ingrediente='" + ingrediente.getNombre() + "'";
			st.executeUpdate(query);
			st.close();
			conexion.close();
			return true;
		}
		catch (SQLException sqle) {
			System.out.println("actualizarPrecioIngrediente() - No se pudo lograr la coneccion con la base de datos");
			return false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Elimina un ingrediente de la base de datos.<br>
	 * Retorna <b>true</b> en caso de éxito, <b>false</b> en caso contrario.
	 * 
	 * @param ingrediente
	 */
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
			System.out.println("eliminarIngrediente() - No se pudo lograr la coneccion con la base de datos");
			return false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Elimina un usuario de la base de datos.<br>
	 * Retorna <b>true</b> en caso de éxito, <b>false</b> en caso contrario.
	 * 
	 * @param usuario
	 */
	public boolean eliminarUsuario(Usuario usuario) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conexion = DriverManager.getConnection(databaseDriver + databaseURL);
			Statement st = conexion.createStatement();
			String query = "Delete from Usuario where nombre='" + usuario.getUsername() + "' and password='" + usuario.getPassword() + "'";
			st.executeUpdate(query);
			st.close();
			conexion.close();
			return true;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("eliminarUsuario() - No se pudo lograr la coneccion con la base de datos");
			return false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
}
