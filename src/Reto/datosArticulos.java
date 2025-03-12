package Reto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class datosArticulos {
    public void articulosIrun() {
        String sql = "SELECT * FROM articulo where id_tienda ='T002' ";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConexion();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Articulos en irun");
                System.out.println("nombre " + rs.getString("nombre"));
                System.out.println("Precio: " + rs.getInt("precio_dia"));
                System.out.println("Cantidad Disponible: " + rs.getInt("fcantidad_disponible"));
                System.out.println("-----");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void articulosDonostia() {
        String sql = "SELECT * FROM articulo where id_tienda ='T001' ";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConexion();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Articulos en donostia");
                System.out.println("nombre " + rs.getString("nombre"));
                System.out.println("Precio: " + rs.getInt("precio_dia"));
                System.out.println("Cantidad Disponible: " + rs.getInt("fcantidad_disponible"));
                System.out.println("-----");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
