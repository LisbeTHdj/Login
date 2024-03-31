package Conexionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexionbasedato {
	  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	    private static final String URL = "jdbc:mysql://localhost:3306/Registro";
	    private static final String USER = "root";
	    private static final String PASSWORD = "12345@Abc";

	    private Connection con;

	    public Conexionbasedato() {
	        try {
	            Class.forName(DRIVER);
	            System.out.println("Conexión con MySQL establecida");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            System.out.println("Error en el driver");
	        }
	    }

	    public Connection getConnection() throws SQLException {
	        if (con == null || con.isClosed()) {
	            con = DriverManager.getConnection(URL, USER, PASSWORD);
	            System.out.println("Conectado a MySQL");
	        }
	        return con;
	    }

	    public void close() {
	        if (con != null) {
	            try {
	                con.close();
	                System.out.println("Se cerró la conexión exitosamente");
	            } catch (SQLException e) {
	                e.printStackTrace();
	                System.out.println("Error al cerrar la conexión");
	            }
	        }
	    }

	    public static void main(String[] args) {
	        Conexionbasedato db = new Conexionbasedato();
	        try {
	            Connection con = db.getConnection();
	            db.close(); // Conexion cerrada
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Error de conexión");
	        }
	    }
	

}
