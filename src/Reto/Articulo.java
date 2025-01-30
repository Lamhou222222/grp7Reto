package Reto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
	 
	 public static void seleccionarFechas(String dni, String cod_art, String articulo, int precioPorDia) {
	        System.out.print("\nSeleccione la fecha de inicio del alquiler (yyyy-mm-dd): ");

	        Scanner scanner = new Scanner(System.in);
	        String fecha = scanner.nextLine();

	        System.out.println("Días a alquilar:");
	        System.out.println("1 día: " + precioPorDia + "€");
	        System.out.println("2 días: " + (2 * precioPorDia) + "€");
	        System.out.println("3 días: " + (3 * precioPorDia) + "€");
	        System.out.println("4 días: " + (4 * precioPorDia) + "€");
	        System.out.print("Selecciona la cantidad de días: ");

	        int dias = scanner.nextInt();
	        int totalPrecio = dias * precioPorDia;

	        System.out.println("Has seleccionado " + dias + " días.");
	        System.out.println("El precio total es: " + totalPrecio + "€");
	        System.out.println("La fecha de inicio del alquiler es: " + fecha);
	        System.out.println("¡Reserva realizada con éxito!");

	        Reserva.guardarReserva(dni, fecha, dias, cod_art, totalPrecio);
	    }
	 public static void mostrarArticulos(String dni) {
	        Connection conn = DBConnection.getConexion();
	        if (conn == null) {
	            System.out.println("Error al conectar a la base de datos.");
	            return;
	        }

	        System.out.println("\nArtículos disponibles:");

	        try {
	            String sql = "SELECT cod_art, nombre, cantidad_disponible, precio_dia FROM articulo";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();

	            int index = 1;
	            while (rs.next()) {
	                String cod_art = rs.getString("cod_art");
	                String nombre = rs.getString("nombre");
	                int cantidad = rs.getInt("cantidad_disponible");
	                int precio = rs.getInt("precio_dia");

	                System.out.println(index + ". " + nombre + " (" + cantidad + " disponibles, " + precio + "€/día)");
	                index++;
	            }
	            System.out.println(index + ". Volver atrás");

	            Scanner scanner = new Scanner(System.in);
	            int opcion = scanner.nextInt();

	            if (opcion >= 1 && opcion < index) {
	                rs.absolute(opcion);
	                String cod_art = rs.getString("cod_art");
	                String nombre = rs.getString("nombre");
	                int precio = rs.getInt("precio_dia");
	                int cantidad = rs.getInt("cantidad_disponible");

	                if (cantidad > 0) {
	                    Articulo.seleccionarFechas(dni, cod_art, nombre, precio);
	                } else {
	                    System.out.println("❌ No hay stock disponible para " + nombre);
	                }
	            } else if (opcion == index) {
	                System.out.println("Volver atrás.");
	                Tienda.mostrarOficinas(dni);
	            } else {
	                System.out.println("Opción no válida.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


	   
}
