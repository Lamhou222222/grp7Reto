package Reto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/La_Tienda";
    private static final String USER = "root";
    private static final String PASSWORD = "Lamhour2000";

    static {
        try {
            // Se carga el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargada");//nadi
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver JDBC: " + e.getMessage());
        }
    }

    public static Connection getConexion() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }
}
