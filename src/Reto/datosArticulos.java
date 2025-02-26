package Reto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class datosArticulos {
	 public void articulosIrun() {
	        String sql = "SELECT * FROM articulo where id_tienda ='T002' ";
	        try (Connection conn = DBConnection.getConexion();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	            	System.out.println("Articulos en irun");
	                System.out.println("nombre " + rs.getString("nombre"));
	                System.out.println("Precio: " + rs.getInt("precio_dia"));
	                System.out.println("Cantidad Disponible: " + rs.getInt("fcantidad_disponible"));


	                System.out.println("-----");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 public void articulosDonostia() {
	        String sql = "SELECT * FROM articulo where id_tienda ='T001' ";
	        try (Connection conn = DBConnection.getConexion();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	            	System.out.println("Articulos en donostia");
	                System.out.println("nombre " + rs.getString("nombre"));
	                System.out.println("Precio: " + rs.getInt("precio_dia"));
	                System.out.println("Cantidad Disponible: " + rs.getInt("cantidad_disponible"));



	                System.out.println("-----");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
