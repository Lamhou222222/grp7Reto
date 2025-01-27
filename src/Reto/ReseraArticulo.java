package Reto;

public class ReseraArticulo {
	
	private static Reserva rerserva;
	private static Articulo articulo;
	
	
	public ReseraArticulo(Reserva reserva, Articulo articulo) {
		ReseraArticulo.rerserva = reserva;
        ReseraArticulo.articulo = articulo;
    }
	
	
	public static Reserva getRerserva() {
		return rerserva;
	}
	public static void setRerserva(Reserva rerserva) {
		ReseraArticulo.rerserva = rerserva;
	}
	public static Articulo getArticulo() {
		return articulo;
	}
	public static void setArticulo(Articulo articulo) {
		ReseraArticulo.articulo = articulo;
	}
	
	
	
	 @Override
	 public String toString() {
		    return "Detalle {" +
		           "\n  reserva: " + (rerserva != null ? rerserva : "null") +
		           ",\n  articulo: " + (articulo != null ? articulo : "null") +
		           "\n}";
		}
}
