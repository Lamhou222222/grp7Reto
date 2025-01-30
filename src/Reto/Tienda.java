package Reto;

import java.util.Scanner;

public class Tienda {
	
	
	//los atributos del clase Tienda 
	private static String id_tienda;
	private static String nombre;
	private static String dirrecion;
	private static Articulo articulo;
	
	
	
	//constructor del clase tienda 
	public Tienda(String id_tienda, String nombre, String dirrecion, Articulo articulo) {
        Tienda.id_tienda = id_tienda;
        Tienda.nombre = nombre;
        Tienda.dirrecion = dirrecion;
        Tienda.articulo = articulo;
    }
	
	//getters y setters
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
	
	// metodo toString o sobrescribir  para mostrar los atributos del clase Tienda 
	 @Override
	    public String toString() {
	        return "Tienda{" +
	               "id_tienda='" + id_tienda + '\'' +
	               ", nombre='" + nombre + '\'' +
	               ", dirrecion='" + dirrecion + '\'' +
	               ", articulo=" + (articulo != null ? articulo.toString() : "null") +
	               '}';
	    }
	 
	 
	 
	 // metodo del clase tienda para mostrar las oficinas que son disponible 
	 public static void mostrarOficinas(String dni) {
		 
		 //menu para mostrar el local de cada tienda
	        System.out.println("\nSeleccione una oficina:");
	        System.out.println("1. Oficina Donostia");
	        System.out.println("2. Oficina Irún");
	        System.out.println("3. Salir");
	        
	        
	        //hay que eligir alguna opcion 
	        Scanner scanner = new Scanner(System.in);
	        int opcion = scanner.nextInt();
	        scanner.nextLine();
	        
	        
	        //utlizar switch para elegir que opcion
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
