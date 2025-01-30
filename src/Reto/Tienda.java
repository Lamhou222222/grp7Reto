package Reto;

import java.util.Scanner;

public class Tienda {
	
	private static String id_tienda;
	private static String nombre;
	private static String dirrecion;
	private static Articulo articulo;
	
	
	public Tienda(String id_tienda, String nombre, String dirrecion, Articulo articulo) {
        Tienda.id_tienda = id_tienda;
        Tienda.nombre = nombre;
        Tienda.dirrecion = dirrecion;
        Tienda.articulo = articulo;
    }
	
	
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
	
	
	 @Override
	    public String toString() {
	        return "Tienda{" +
	               "id_tienda='" + id_tienda + '\'' +
	               ", nombre='" + nombre + '\'' +
	               ", dirrecion='" + dirrecion + '\'' +
	               ", articulo=" + (articulo != null ? articulo.toString() : "null") +
	               '}';
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
