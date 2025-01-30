package Reto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	 public static void guardarReserva(String dni, String fecha, int dias, String cod_art, int totalPrecio) {
	        Connection conn = DBConnection.getConexion();
	        if (conn == null) {
	            System.out.println("Error al conectar a la base de datos.");
	            return;
	        }

	        try {
	            // Generar ID de reserva único basado en el tiempo
	            String idReserva = "R" + System.currentTimeMillis();

	            // Obtener la fecha actual como fecha de reservación
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            String fechaReservacion = sdf.format(new Date());

	            // Insertar en la tabla reserva
	            String sqlReserva = "INSERT INTO reserva (id_reserva, fecha_inicio, dias, precio_total, fecha_reservacion, Dni_usuario) VALUES (?, ?, ?, ?, ?, ?)";
	            PreparedStatement stmtReserva = conn.prepareStatement(sqlReserva);
	            stmtReserva.setString(1, idReserva);
	            stmtReserva.setString(2, fecha);
	            stmtReserva.setInt(3, dias);
	            stmtReserva.setInt(4, totalPrecio);
	            stmtReserva.setString(5, fechaReservacion);
	            stmtReserva.setString(6, dni);
	            stmtReserva.executeUpdate();

	            // Insertar en la tabla reservaarticulo
	            String sqlReservaArticulo = "INSERT INTO ReservaArticulo (id_reserva, cod_art) VALUES (?, ?)";
	            PreparedStatement stmtReservaArticulo = conn.prepareStatement(sqlReservaArticulo);
	            stmtReservaArticulo.setString(1, idReserva);
	            stmtReservaArticulo.setString(2, cod_art);
	            stmtReservaArticulo.executeUpdate();

	            // Restar 1 a la cantidad disponible en la tabla articulo
	            String sqlUpdate = "UPDATE articulo SET cantidad_disponible = cantidad_disponible - 1 WHERE cod_art = ?";
	            PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
	            stmtUpdate.setString(1, cod_art);
	            stmtUpdate.executeUpdate();

	            System.out.println("✅ Reserva guardada y stock actualizado.");

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
}
