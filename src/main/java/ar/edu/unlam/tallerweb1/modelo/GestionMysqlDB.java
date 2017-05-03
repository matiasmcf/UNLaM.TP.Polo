package ar.edu.unlam.tallerweb1.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ar.edu.unlam.tallerweb1.modelo.Ingrediente.TipoIngrediente;
import ar.edu.unlam.tallerweb1.modelo.Usuario.TipoUsuario;

public class GestionMysqlDB {
	
	private static GestionMysqlDB database= new GestionMysqlDB();
	
	private GestionMysqlDB(){}
	
	public static GestionMysqlDB getInstance() {
		return database;
	}
	
	@SuppressWarnings("finally")
	private Connection getConnection()
    {
        Connection conexion=null;
          try{
        	Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://mysql149754-sanguchetto.j.layershift.co.uk/Sanguchetto";
            String usuarioDB="root";
            String passwordDB="RLKisi67079";
            conexion= DriverManager.getConnection(servidor,usuarioDB,passwordDB);
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Error1 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
        catch(SQLException ex)
        {
            System.out.println("Error2 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
        catch(Exception ex)
        {
            System.out.println("Error3 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
        finally{
            return conexion;
        }
    }

	private boolean consultar(String query) {
		boolean estado = false;
		try {
			Connection conexion=getConnection();
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
			System.out.println("consultar() - No se pudo lograr la conexion con la base de datos mysql");
			ex.printStackTrace();
		}
		return estado;
	}

	public int verificarDatos(Usuario usuario) {
		String query = "SELECT * FROM Usuario WHERE nombre='" + usuario.getUsername() + "' AND password='" + usuario.getPassword() + "'";
		int estado = 1;
		try {
			Connection conexion=getConnection();
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

	private boolean existeUsuario(String usuario) {
		String query = "SELECT * FROM Usuario WHERE nombre='" + usuario + "'";
		boolean estado = false;
		try {
			estado = consultar(query);
		}
		catch (Exception ex) {
			System.out.println("existeUsuario() - Error al comprobar existencia usuario");
		}
		return estado;
	}

	public boolean registrarUsuario(String usuario, String password) {
		if (existeUsuario(usuario) == true)
			return false;
		try {
			Connection conexion=getConnection();
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
	}

	public void cargarIngredientes(Stock stock) {
		System.out.println("Cargando ingredientes");
		String sql = "SELECT * FROM Stock";
		try {
			Connection conexion=getConnection();
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
	}
	
	public boolean insertarIngrediente(Stock stock, Ingrediente ingrediente) {
		try {
			Connection conexion=getConnection();
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
	}
	
	public boolean actualizarStockIngrediente(Stock stock, Ingrediente ingrediente) {
		try {
			Connection conexion=getConnection();
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
	}

	public boolean eliminarIngrediente(Ingrediente ingrediente) {
		try {
			Connection conexion=getConnection();
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
	}
	
	public boolean eliminarUsuario(Usuario usuario) {
		try {
			Connection conexion=getConnection();
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
	}

}
