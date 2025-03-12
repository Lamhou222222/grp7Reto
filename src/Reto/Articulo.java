package Reto;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Articulo {
	
	
	//las atributos del clase articulo
	private static String cod_art;
	private static String tipo;
	private static String nombre;
	private static int cantidad_disponible;
	private static int precio_dia;
	private static  String tienda;
	
	
	
	//constructor del clase articulo
	 public Articulo(String cod_art, String tipo, String nombre, int cantidad_disponible, int precio_dia, String tienda) {
	        Articulo.cod_art = cod_art;
	        Articulo.tipo = tipo;
	        Articulo.nombre = nombre;
	        Articulo.cantidad_disponible = cantidad_disponible;
	        Articulo.precio_dia = precio_dia;
	        Articulo.tienda = tienda;
	    }
	 
	 
	//los gitters y setters 
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
	
	
	
	//metodo toString para mostrar los atributos del clase Articluo
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
	 
	 
	 //metodo del clase atriculo para selccion la fecha del alquiler 
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
	        
	        //insertar las dias para calcular el precio total
	        int dias = scanner.nextInt();
	        int totalPrecio = dias * precioPorDia;

	        System.out.println("Has seleccionado " + dias + " días.");
	        System.out.println("El precio total es: " + totalPrecio + "€");
	        System.out.println("La fecha de inicio del alquiler es: " + fecha);
	        System.out.println("¡Reserva realizada con éxito!");
	        
	        
	        // llamar el metodo guardarReserva del clase reserva 
	        Reserva.guardarReserva(dni, fecha, dias, cod_art, totalPrecio);
	    }
	 
	 
	 // metodo del clase atriculo para mostrarr los qrticulos que son disponible al base de datos 
	 public static void mostrarArticulos(String dni) {
		 // iniciar connexion con el base datos 'La_tienda'
	        Connection conn = DBConnection.getConexion();
	        
	        //verificar si hay conexion entre nuestra  base de datos y java
	        if (conn == null) {
	            System.out.println("Error al conectar a la base de datos.");
	            return;
	        }

	        System.out.println("Artículos disponibles:");

	        try {
	        	
	        	// la consulta para mostrar los datos de atribultos de la tabla articulo
	            String sql = "SELECT cod_art, nombre, cantidad_disponible, precio_dia FROM articulo";
	            //preparar la consulta para utilizarla atraves el metodo  prepareStatement del clase PreparedStatement 
	            PreparedStatement stmt = conn.prepareStatement(sql, 
	            	    ResultSet.TYPE_SCROLL_INSENSITIVE, 
	            	    ResultSet.CONCUR_READ_ONLY
	            	);
	            //ejecutar el la consulta 
	            
	            ResultSet rs = stmt.executeQuery();
	            //iniciar indicio para calcular los columnas 
	            int index = 1;
	            
	            //pasar por todas las comlumnas 
	            while (rs.next()) {
	                String cod_art = rs.getString("cod_art");
	                String nombre = rs.getString("nombre");
	                int cantidad = rs.getInt("cantidad_disponible");
	                int precio = rs.getInt("precio_dia");

	                System.out.println(index + ". " + nombre + " (" + cantidad + " disponibles, " + precio + "€/día)");
	                index++;
	            }
	            
	            //elegir que opcion puedo elegirla 
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
	                //para volver y elegir la opcion otra vez
	            } else if (opcion == index) {
	                System.out.println("Volver atrás.");
	                try {
						Tienda.mostrarOficinas(dni);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            } else {
	                System.out.println("Opción no válida.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public static void resArticulo(String cod_art ) {
		 try (Connection con = DBConnection.getConexion()) {
	            if (con != null) {
	            	String sqlUpdate = "UPDATE articulo SET cantidad_disponible = cantidad_disponible - 1 WHERE cod_art = ?";
	                PreparedStatement stmtUpdate = con.prepareStatement(sqlUpdate);

	                stmtUpdate.setString(1, cod_art);
	                stmtUpdate.executeUpdate();
	            }
	        } catch (SQLException e) {
	            System.err.println("Error  " + e.getMessage());
	        }
	 }


	   
}