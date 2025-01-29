package Reto;
import java.sql.*;
public class DBConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/La_Tienda"; 
    private static final String USER = "root"; 
    private static final String PASS = "Lamhour2000@"; 

    public static Connection getConexion() {
        try {

            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println("Error de conexión a la base de datos: " + e.getMessage());
            
        return null;
    }
        }
}
