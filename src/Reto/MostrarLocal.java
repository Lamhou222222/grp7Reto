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

	public static void mostrarOficinas(String dni) {
	        System.out.println("\nSeleccione una oficina:");
	        System.out.println("1. Oficina Donostia");
	        System.out.println("2. Oficina Irún");
	        System.out.println("3. Salir");
	
	        Scanner scanner = new Scanner(System.in);
	        int opcion = scanner.nextInt();
	        scanner.nextLine();
	
	        switch (opcion) {
	            case 1:
	                System.out.println("Has seleccionado la Oficina Donostia");
	                Articulo.mostrarArticulos(dni);
	                break;
	            case 2:
	                System.out.println("Has seleccionado la Oficina Irún");
	                Articulo.mostrarArticulos(dni);
	                break;
	            case 3:
	                System.out.println("Volver al menú principal.");
	                break;
	            default:
	                System.out.println("Opción no válida. Volviendo al menú principal.");
	        }
	    }
	

}
