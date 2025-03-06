package Reto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatosArticulos {

    public void articulosIrun() {
        String sql = "SELECT * FROM articulo WHERE id_tienda = 'T002'";
        try (Connection conn = DBConnection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (conn == null) {
                throw new SQLException("Error al conectar a la base de datos.");
            }

            while (rs.next()) {
                System.out.println("Artículos en Irun:");
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Precio: " + rs.getInt("precio_dia"));
                System.out.println("Cantidad Disponible: " + rs.getInt("cantidad_disponible"));
                System.out.println("-----");
            }

        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta para Irun: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("No se pudo recuperar los artículos de Irun.", e);
        }
    }

    public void articulosDonostia() {
        String sql = "SELECT * FROM articulo WHERE id_tienda = 'T001'";
        try (Connection conn = DBConnection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (conn == null) {
                throw new SQLException("Error al conectar a la base de datos.");
            }

            while (rs.next()) {
                System.out.println("Artículos en Donostia:");
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Precio: " + rs.getInt("precio_dia"));
                System.out.println("Cantidad Disponible: " + rs.getInt("cantidad_disponible"));
                System.out.println("-----");
            }

        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta para Donostia: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("No se pudo recuperar los artículos de Donostia.", e);
        }
    }
}
