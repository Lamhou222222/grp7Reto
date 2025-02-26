package Reto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class Articulo implements ArticuloInterface {
    private String cod_art;
    private String tipo;
    private String nombre;
    private int cantidad_disponible;
    private int precio_dia;
    private String tienda;

    public Articulo(String cod_art, String tipo, String nombre, int cantidad_disponible, int precio_dia, String tienda) {
        this.cod_art = cod_art;
        this.tipo = tipo;
        this.nombre = nombre;
        this.cantidad_disponible = cantidad_disponible;
        this.precio_dia = precio_dia;
        this.tienda = tienda;
    }

    public String getCod_art() { return cod_art; }
    public String getTipo() { return tipo; }
    public String getNombre() { return nombre; }
    public int getCantidad_disponible() { return cantidad_disponible; }
    public int getPrecio_dia() { return precio_dia; }
    public String getTienda() { return tienda; }

    public void setCantidad_disponible(int cantidad_disponible) {
        this.cantidad_disponible = cantidad_disponible;
    }

    @Override
    public String toString() {
        return "Artículo: " + nombre + " (" + tipo + ") - Precio/día: " + precio_dia + "€ - Stock: " + cantidad_disponible;
    }

    @Override
    public void guardarEnBD() {
        Connection conn = DBConnection.getConexion();
        if (conn == null) {
            System.out.println("Error al conectar con la base de datos.");
            return;
        }

        try {
            String sql = "INSERT INTO articulo (cod_art, tipo, nombre, cantidad_disponible, precio_dia, tienda) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cod_art);
            stmt.setString(2, tipo);
            stmt.setString(3, nombre);
            stmt.setInt(4, cantidad_disponible);
            stmt.setInt(5, precio_dia);
            stmt.setString(6, tienda);

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("✅ Artículo guardado en la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarDeBD() {
        Connection conn = DBConnection.getConexion();
        if (conn == null) {
            System.out.println("Error al conectar con la base de datos.");
            return;
        }

        try {
            String sql = "DELETE FROM articulo WHERE cod_art = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cod_art);

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("🗑️ Artículo eliminado correctamente.");
            } else {
                System.out.println("⚠️ No se encontró el artículo.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarStock(int nuevaCantidad) {
        Connection conn = DBConnection.getConexion();
        if (conn == null) {
            System.out.println("Error al conectar con la base de datos.");
            return;
        }

        try {
            String sql = "UPDATE articulo SET cantidad_disponible = ? WHERE cod_art = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, nuevaCantidad);
            stmt.setString(2, cod_art);

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("✅ Stock actualizado.");
                this.cantidad_disponible = nuevaCantidad;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void resArticulo(String cod_art) {
        Connection conn = DBConnection.getConexion();
        if (conn == null) {
            System.out.println("❌ Error al conectar a la base de datos.");
            return;
        }

        String sql = "UPDATE articulo SET cantidad_disponible = cantidad_disponible - 1 WHERE cod_art = ? AND cantidad_disponible > 0";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cod_art);

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("✅ Stock actualizado correctamente para el artículo: " + cod_art);
            } else {
                System.out.println("⚠️ No se pudo actualizar el stock. Puede que no haya suficiente cantidad disponible.");
            }
        } catch (SQLException e) {
            System.err.println("⚠️ Error al actualizar el stock del artículo: " + e.getMessage());
        }
    }
}