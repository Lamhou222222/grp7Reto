package Reto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {
	
	
	
	private static String Dni_usuario;
	private static String nombre ;
	private static String Sexo;
	private static String contrasenia;
	
	public Usuario( String Dni ,String nom,String s,String con) {
		Usuario.Dni_usuario=Dni;
		Usuario.nombre=nom;
		Usuario.Sexo=s;
		Usuario.contrasenia=con;

	}


	
	
	public static String getDni_usuario() {
		return Dni_usuario;
	}


	public static void setDni_usuario(String dni_usuario) {
		Dni_usuario = dni_usuario;
	}


	public static String getNombre() {
		return nombre;
	}


	public static void setNombre(String nombre) {
		Usuario.nombre = nombre;
	}


	public static String getSexo() {
		return Sexo;
	}


	public static void setSexo(String sex) {
		if(sex!="M" && sex!="m") {
			Sexo="H";
		}
		else {
			Sexo=sex;
		}
	}


	public static String getContrasenia() {
		return contrasenia;
	}


	public static void setContrasenia(String contrasenia) {
		Usuario.contrasenia = contrasenia;
	}


	





	@Override
    public String toString() {
        return "Usuario{" +
                "Dni_usuario='" + Dni_usuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", Sexo=" + Sexo +
                ", contrasenia='" + contrasenia + '\'' +
                 '\'' +
                 '}';
    }
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
	        String sql = "INSERT INTO usuario (Dni_usuario, nom_usuario,contrasena,sexo) VALUES (?, ?, ?, ?)";
	        
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
