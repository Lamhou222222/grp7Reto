package Reto;

public class Reserva {
	
	private static String id;
	private static String fecha_inicio;
	private static int prcio;
	private static int dias;
	private static Usuario user;
	private static String  fecha_reservacion;
	
	
	public Reserva(String id, String fecha_inicio, int prcio, int dias, Usuario user, String fecha_reservacion) {
        Reserva.id = id;
        Reserva.fecha_inicio = fecha_inicio;
        Reserva.prcio = prcio;
        Reserva.dias = dias;
        Reserva.user = user;
        Reserva.fecha_reservacion = fecha_reservacion;
    }
	
	
	public static String getId() {
		return id;
	}
	public static void setId(String id) {
		Reserva.id = id;
	}
	public static String getFecha_inicio() {
		return fecha_inicio;
	}
	public static void setFecha_inicio(String fecha_inicio) {
		Reserva.fecha_inicio = fecha_inicio;
	}
	public static int getPrcio() {
		return prcio;
	}
	public static void setPrcio(int prcio) {
		Reserva.prcio = prcio;
	}
	public static int getDias() {
		return dias;
	}
	public static void setDias(int dias) {
		Reserva.dias = dias;
	}
	public static Usuario getUser() {
		return user;
	}
	public static void setUser(Usuario user) {
		Reserva.user = user;
	}
	public static String getFecha_reservacion() {
		return fecha_reservacion;
	}
	public static void setFecha_reservacion(String fecha_reservacion) {
		Reserva.fecha_reservacion = fecha_reservacion;
	}
	
	
	 @Override
	    public String toString() {
	        return "Reserva{" +
	                "id de reserva='" + id + '\'' +
	                ", fecha_inicio='" + fecha_inicio + '\'' +
	                ", precio=" + prcio +
	                ", dias=" + dias +
	                ", user=" + (user != null ? user.toString() : "null") +
	                ", fecha_reservacion='" + fecha_reservacion + '\'' +
	                '}';
	    }
	
}
