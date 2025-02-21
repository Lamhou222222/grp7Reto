package Reto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {
    // Atributos de la reserva
    private String id;
    private String fecha_inicio;
    private int precio;
    private int dias;
    private String dni_user;
    private String fecha_reservacion;

    // Constructor
    public Reserva(String id, String fecha_inicio, int precio, int dias, String dni_user, String fecha_reservacion) {
        this.id = id;
        this.fecha_inicio = fecha_inicio;
        this.precio = precio;
        this.dias = dias;
        this.dni_user = dni_user;
        this.fecha_reservacion = fecha_reservacion;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFecha_inicio() { return fecha_inicio; }
    public void setFecha_inicio(String fecha_inicio) { this.fecha_inicio = fecha_inicio; }

    public int getPrecio() { return precio; }
    public void setPrecio(int precio) { this.precio = precio; }

    public int getDias() { return dias; }
    public void setDias(int dias) { this.dias = dias; }

    public String getDni_user() { return dni_user; }
    public void setDni_user(String dni_user) { this.dni_user = dni_user; }

    public String getFecha_reservacion() { return fecha_reservacion; }
    public void setFecha_reservacion(String fecha_reservacion) { this.fecha_reservacion = fecha_reservacion; }

    // Método toString para mostrar la reserva
    @Override
    public String toString() {
        return "Reserva{" +
                "ID='" + id + '\'' +
                ", Fecha Inicio='" + fecha_inicio + '\'' +
                ", Precio=" + precio +
                ", Días=" + dias +
                ", Usuario=" + dni_user +
                ", Fecha Reservación='" + fecha_reservacion + '\'' +
                '}';
    }

    // Método para guardar una reserva en la base de datos
    public static void guardarReserva(String dni, String fecha, int dias, String cod_art, int totalPrecio) {
        Connection conn = DBConnection.getConexion();
        if (conn == null) {
            System.out.println("❌ Error al conectar a la base de datos.");
            return;
        }

        try {
            // Generar un ID de reserva único basado en el tiempo
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
            ReseraArticulo.addResArticulo(idReserva, cod_art);

            // Restar 1 a la cantidad disponible en la tabla articulo
            Articulo.resArticulo(cod_art);

            System.out.println("✅ Reserva guardada con éxito. Stock actualizado.");
        } catch (SQLException e) {
            System.err.println("⚠️ Error al guardar la reserva: " + e.getMessage());
        }
    }
}
