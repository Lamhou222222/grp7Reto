package Reto;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReseraArticulo {
	
	private static String id_reserva;
	private static String cod_art;
	
	
	public ReseraArticulo(String reserva, String articulo) {
		ReseraArticulo.id_reserva = reserva;
        ReseraArticulo.cod_art = articulo;
    }
	
	
	public static String getRerserva() {
		return id_reserva;
	}
	public static void setRerserva(String rerserva) {
		ReseraArticulo.id_reserva = rerserva;
	}
	public static String getArticulo() {
		return cod_art;
	}
	public static void setArticulo(String articulo) {
		ReseraArticulo.cod_art = articulo;
	}
	
	
	
	 @Override
	 public String toString() {
		    return "Detalle {" +
		           "\n  reserva: " + (id_reserva != null ? id_reserva : "null") +
		           ",\n  articulo: " + (cod_art != null ? cod_art : "null") +
		           "\n}";
		}
	 
	 public static void listarReservarArticulos() {
	        try (Connection con = DBConnection.getConexion()) {
	            if (con != null) {
	                String query = "SELECT * FROM reservaarticulo ";
	                try (Statement stmt = con.createStatement()) {
	                    ResultSet rs = stmt.executeQuery(query);
	                    while (rs.next()) {
	                        System.out.println("Código: " + rs.getString("cod_art"));
	                        System.out.println("id_reserva: " + rs.getString("id_reserva"));
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Error  " + e.getMessage());
	        }
	    }
	 public static void addResArticulo(String idReserva,String cod_art) {
		 try (Connection con = DBConnection.getConexion()) {
	            if (con != null) {
	            	String sqlReservaArticulo = "INSERT INTO ReservaArticulo (id_reserva, cod_art) VALUES (?, ?)";
	                PreparedStatement stmtReservaArticulo = con.prepareStatement(sqlReservaArticulo);

	            	 stmtReservaArticulo.setString(1,idReserva );
	                 stmtReservaArticulo.setString(2,cod_art);
	                 stmtReservaArticulo.executeUpdate();
	            }
	        } catch (SQLException e) {
	            System.err.println("Error  " + e.getMessage());
	        }
	 }
}
