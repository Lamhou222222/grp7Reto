package Reto;
import java.sql.*;
public class DBConnection {
	
	public static Connection getConexion() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/La_Tienda","root","Lamhour2000@");
			
		}catch(SQLException e) {
			System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
		}
	}
}
