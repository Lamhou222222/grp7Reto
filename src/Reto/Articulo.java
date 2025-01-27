package Reto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Articulo {
	
	private static String cod_art;
	private static String tipo;
	private static String nombre;
	private static int cantidad_disponible;
	private static int precio_dia;
	private static  String tienda;
	
	
	 public Articulo(String cod_art, String tipo, String nombre, int cantidad_disponible, int precio_dia, String tienda) {
	        Articulo.cod_art = cod_art;
	        Articulo.tipo = tipo;
	        Articulo.nombre = nombre;
	        Articulo.cantidad_disponible = cantidad_disponible;
	        Articulo.precio_dia = precio_dia;
	        Articulo.tienda = tienda;
	    }
	
	public static String getCod_art() {
		return cod_art;
	}
	public static void setCod_art(String cod_art) {
		Articulo.cod_art = cod_art;
	}
	public static String getTipo() {
		return tipo;
	}
	public static void setTipo(String tipo) {
		Articulo.tipo = tipo;
	}
	public static String getNombre() {
		return nombre;
	}
	public static void setNombre(String nombre) {
		Articulo.nombre = nombre;
	}
	public static int getCantidad_disponible() {
		return cantidad_disponible;
	}
	public static void setCantidad_disponible(int cantidad_disponible) {
		Articulo.cantidad_disponible = cantidad_disponible;
	}
	public static int getPrecio_dia() {
		return precio_dia;
	}
	public static void setPrecio_dia(int precio_dia) {
		Articulo.precio_dia = precio_dia;
	}
	public static String getTienda() {
		return tienda;
	}
	public static void setTienda(String tienda) {
		Articulo.tienda = tienda;
	}
	
	
	 @Override
	    public String toString() {
	        return "Articulo{" +
	                "cod_art='" + cod_art + '\'' +
	                ", tipo='" + tipo + '\'' +
	                ", nombre='" + nombre + '\'' +
	                ", cantidad_disponible=" + cantidad_disponible +
	                ", precio_dia=" + precio_dia +
	                ", tienda='" + tienda + '\'' +
	                '}';
	    }
	 
	    public static void listarArticulos() {
	        try (Connection con = DBConnection.getConexion()) {
	            if (con != null) {
	                String query = "SELECT * FROM articulo";
	                try (Statement stmt = con.createStatement()) {
	                    ResultSet rs = stmt.executeQuery(query);
	                    while (rs.next()) {
	                        System.out.println("Código: " + rs.getString("cod_art"));
	                        System.out.println("Nombre: " + rs.getString("nombre"));
	                        System.out.println("Cantidad disponible: " + rs.getInt("cantidad_disponible"));
	                        System.out.println("Precio por día: " + rs.getInt("precio_dia"));
	                        System.out.println("Tipo: " + rs.getString("tipo"));
	                        System.out.println("----");
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al listar los artículos: " + e.getMessage());
	        }
	    }
}
