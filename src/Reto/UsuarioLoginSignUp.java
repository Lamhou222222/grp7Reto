package Reto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioLoginSignUp {
	 public static boolean login(String dni, String contrasena) {
	        String sql = "SELECT contrasena FROM usuario WHERE Dni_usuario = ?";
	        try (Connection conn = DBConnection.getConexion();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1, dni);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                String hashedPassword = rs.getString("contrasena");
	                return contrasena.equals(hashedPassword); // 
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    public static void register(String dni, String nombre, String sexo, String contrasena) {
	        String sql = "INSERT INTO usuario (Dni_usuario, nom_usuario,contrasena,sexo) VALUES (?, ?, ?, ?, ?)";
	        
	        try (Connection conn = DBConnection.getConexion();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1, dni);
	            stmt.setString(2, nombre);
	            stmt.setString(3, contrasena); 
	            stmt.setString(4, sexo);
	            stmt.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
