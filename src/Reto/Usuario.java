package Reto;

import java.sql.Connection;
import java.sql.PreparedStatement;
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


	private static final String  ROL[]= {"Cliente","empleado"};
	
	
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


	
	
	
	
	public static boolean rolUsuario(String rol) {
		for(int k=0;k<2;k++) {
			if(ROL[k].equalsIgnoreCase(rol)) {
				return true;
			}
		}
		return false;
		
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
	
	public void insertarUsuario() {
        try (Connection con = DBConnection.getConexion()) {
            if (con != null) {
                String query = "INSERT INTO usuario (Dni_usuario, nom_usuario, contrasena, sexo) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pst = con.prepareStatement(query)) {
                    pst.setString(1, Usuario.Dni_usuario);
                    pst.setString(2, Usuario.nombre);
                    pst.setString(3, Usuario.contrasenia);
                    pst.setString(4, String.valueOf(Usuario.Sexo));
                    pst.executeUpdate();
                    System.out.println("Usuario insertado correctamente.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar el usuario: " + e.getMessage());
        }
    }
	
	
}
