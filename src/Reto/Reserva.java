package Reto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Reserva {
    private String id;
    private String fecha_inicio;
    private int precio;
    private int dias;
    private String dni_user;
    private String fecha_reservacion;

    public Reserva(String id, String fecha_inicio, int precio, int dias, String dni_user, String fecha_reservacion) {
        this.id = id;
        this.fecha_inicio = fecha_inicio;
        this.precio = precio;
        this.dias = dias;
        this.dni_user = dni_user;
        this.fecha_reservacion = fecha_reservacion;
    }

    public static void guardarReserva(String dni, String fecha, int dias, String cod_art, int totalPrecio) {
        try (Connection conn = DBConnection.getConexion()) {
            if (conn == null) {
                throw new DatabaseException("❌ Error al conectar a la base de datos.");
            }

            String idReserva = "R" + System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaReservacion = sdf.format(new Date());

            String sqlReserva = "INSERT INTO reserva (id_reserva, fecha_inicio, dias, precio_total, fecha_reservacion, Dni_usuario) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmtReserva = conn.prepareStatement(sqlReserva)) {
                stmtReserva.setString(1, idReserva);
                stmtReserva.setString(2, fecha);
                stmtReserva.setInt(3, dias);
                stmtReserva.setInt(4, totalPrecio);
                stmtReserva.setString(5, fechaReservacion);
                stmtReserva.setString(6, dni);
                stmtReserva.executeUpdate();
            }

            ReservaArticulo.addResArticulo(idReserva, cod_art);
            Articulo.resArticulo(cod_art);

            System.out.println("✅ Reserva guardada con éxito. Stock actualizado.");
        } catch (SQLException e) {
            throw new DatabaseException("Error al guardar la reserva en la base de datos.", e);
        }
    }
}