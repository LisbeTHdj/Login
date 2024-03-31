package Conexionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Registro {
	  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	    private static final String URL = "jdbc:mysql://localhost:3306/Registro";
	    private static final String USER = "root";
	    private static final String PASSWORD = "12345@Abc";

	    private Connection con;

	    public Registro() {
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
	    	 Registro db = new Registro();
	    	    Connection con = null;
	    	    try {
	    	        con = db.getConnection();
	    	       
	    	        PreparedStatement statement = con.prepareStatement("SELECT * FROM tabla");
	    	        ResultSet result = statement.executeQuery();
	    	       
	    	    } catch (SQLException e) {
	    	        e.printStackTrace();
	    	        System.out.println("Error de conexión");
	    	    } finally {
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
	

}}
