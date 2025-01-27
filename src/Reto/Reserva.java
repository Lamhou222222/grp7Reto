package Reto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Reserva {
	
	private static String id;
	private static String fecha_inicio;
	private static int prcio;
	private static int dias;
	private static String  dni_user;
	private static String  fecha_reservacion;
	
	
	public Reserva(String id, String fecha_inicio, int prcio, int dias, String user, String fecha_reservacion) {
        Reserva.id = id;
        Reserva.fecha_inicio = fecha_inicio;
        Reserva.prcio = prcio;
        Reserva.dias = dias;
        Reserva.dni_user = user;
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
	public static String getUser() {
		return dni_user;
	}
	public static void setUser(String user) {
		Reserva.dni_user = user;
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
	                ", user=" + dni_user +
	                ", fecha_reservacion='" + fecha_reservacion + '\'' +
	                '}';
	    }
	 public void insertarReserva() {
	        try (Connection con = DBConnection.getConexion()) {
	            if (con != null) {
	                String query = "INSERT INTO reserva (id_reserva, fecha_inicio, dias, precio_total, fecha_reservacion, Dni_usuario) VALUES (?, ?, ?, ?, ?, ?)";
	                try (PreparedStatement pst = con.prepareStatement(query)) {
	                    pst.setString(1, Reserva.id);
	                    pst.setString(2, Reserva.fecha_inicio);
	                    pst.setInt(3, Reserva.dias);
	                    pst.setInt(4, Reserva.prcio);
	                    pst.setString(5, Reserva.fecha_reservacion);
	                    pst.setString(6, Reserva.dni_user);
	                    pst.executeUpdate();
	                    System.out.println("Reserva insertada correctamente.");
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al insertar la reserva: " + e.getMessage());
	        }
	    }
	
}
