package Reto;

import java.util.Scanner;

public class Tienda {
    // Atributos de la clase Tienda
    private static String id_tienda;
    private static String nombre;
    private static String dirrecion;
    private static Articulo articulo;

    // Constructor de la clase Tienda
    public Tienda(String id_tienda, String nombre, String dirrecion, Articulo articulo) {
        Tienda.id_tienda = id_tienda;
        Tienda.nombre = nombre;
        Tienda.dirrecion = dirrecion;
        Tienda.articulo = articulo;
    }

    // Getters y Setters
    public static String getId_tienda() {
        return id_tienda;
    }

    public static void setId_tienda(String id_tienda) {
        Tienda.id_tienda = id_tienda;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        Tienda.nombre = nombre;
    }

    public static String getDirrecion() {
        return dirrecion;
    }

    public static void setDirrecion(String dirrecion) {
        Tienda.dirrecion = dirrecion;
    }

    public static Articulo getArticulo() {
        return articulo;
    }

    public static void setArticulo(Articulo articulo) {
        Tienda.articulo = articulo;
    }

    // Método toString para mostrar los atributos de la clase Tienda
    @Override
    public String toString() {
        return "Tienda{" +
               "id_tienda='" + id_tienda + '\'' +
               ", nombre='" + nombre + '\'' +
               ", dirrecion='" + dirrecion + '\'' +
               ", articulo=" + (articulo != null ? articulo.toString() : "null") +
               '}';
    }

    // Método para mostrar las oficinas disponibles
    public static void mostrarOficinas(String dni)  {
        Scanner scanner = null;
        try {
            System.out.println("\nSeleccione una oficina:");
            System.out.println("1. Oficina Donostia");
            System.out.println("2. Oficina Irún");
            System.out.println("3. Salir");

            scanner = new Scanner(System.in);
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
        } catch (Exception e) {
           System.out.println("Error al seleccionar una oficina: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
