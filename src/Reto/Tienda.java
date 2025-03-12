package Reto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Tienda {
    // Atributos de la clase Tienda
    private String id_tienda;
    private String nombre;
    private String direccion;

    // Constructor de la clase Tienda
    public Tienda(String id_tienda, String nombre, String direccion) {
        this.id_tienda = id_tienda;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    // Getters y setters
    public String getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(String id_tienda) {
        this.id_tienda = id_tienda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Método para mostrar artículos desde la base de datos
    public static void mostrarArticulos() {
        String query = "SELECT cod_art, nombre, cantidad_disponible FROM articulo";

        try (Connection conn = DBConnection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\nArtículos disponibles en la tienda:");
            while (rs.next()) {
                System.out.println("Código: " + rs.getString("cod_art") +
                        ", Nombre: " + rs.getString("nombre") +
                        ", Stock: " + rs.getInt("cantidad_disponible"));
            }
        } catch (SQLException e) {
            System.err.println("⚠️ Error al obtener los artículos de la base de datos: " + e.getMessage());
        }
    }

    // Método para mostrar las oficinas disponibles
    public static void mostrarOficinas(String dni) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        try {
            do {
                System.out.println("\nSeleccione una oficina:");
                System.out.println("1. Oficina Donostia");
                System.out.println("2. Oficina Irún");
                System.out.println("3. Salir");
                System.out.print("Opción: ");
                
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la Oficina Donostia");
                        mostrarArticulos();
                        break;
                    case 2:
                        System.out.println("Has seleccionado la Oficina Irún");
                        mostrarArticulos();
                        break;
                    case 3:
                        System.out.println("Volviendo al menú principal.");
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            } while (opcion != 3);
        } catch (Exception e) {
            System.err.println("⚠️ Error en la selección de oficina: " + e.getMessage());
            scanner.nextLine(); // Limpiar buffer
        } finally {
            scanner.close();
        }
    }
}
