package Reto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MostrarLocal {
	 public static void mostrarLasTiendas() {
	        String sql = "SELECT * FROM tienda";
	        try (Connection conn = DBConnection.getConexion();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                System.out.println("La oficina: " + rs.getString("nombre"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

}
