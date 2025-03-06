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
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConexion();
            if (conn == null) {
                throw new DatabaseException("Error al conectar con la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la conexión: " + e.getMessage());
            e.printStackTrace();
            throw new DatabaseException("Error al obtener la conexión para guardar el artículo.");
        }

        try {
            String sql = "INSERT INTO articulo (cod_art, tipo, nombre, cantidad_disponible, precio_dia, tienda) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cod_art);
            stmt.setString(2, tipo);
            stmt.setString(3, nombre);
            stmt.setInt(4, cantidad_disponible);
            stmt.setInt(5, precio_dia);
            stmt.setString(6, tienda);

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("✅ Artículo guardado en la base de datos.");
            } else {
                throw new SQLException("No se insertaron filas en la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
            throw new DatabaseException("Error al ejecutar la consulta para guardar el artículo.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
                e.printStackTrace();
                throw new DatabaseException("Error al cerrar los recursos después de guardar.");
            }
        }
    }

    @Override
    public void eliminarDeBD() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConexion();
            if (conn == null) {
                throw new DatabaseException("Error al conectar con la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la conexión: " + e.getMessage());
            e.printStackTrace();
            throw new DatabaseException("Error al obtener la conexión para eliminar el artículo.");
        }

        try {
            String sql = "DELETE FROM articulo WHERE cod_art = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cod_art);

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("🗑️ Artículo eliminado correctamente.");
            } else {
                System.out.println("⚠️ No se encontró el artículo.");
                throw new DatabaseException("El artículo no fue encontrado para eliminar.");
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
            throw new DatabaseException("Error al ejecutar la consulta para eliminar el artículo.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
                e.printStackTrace();
                throw new DatabaseException("Error al cerrar los recursos después de eliminar.");
            }
        }
    }

    @Override
    public void actualizarStock(int nuevaCantidad) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConexion();
            if (conn == null) {
                throw new DatabaseException("Error al conectar con la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la conexión: " + e.getMessage());
            e.printStackTrace();
            throw new DatabaseException("Error al obtener la conexión para actualizar el stock.");
        }

        try {
            String sql = "UPDATE articulo SET cantidad_disponible = ? WHERE cod_art = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, nuevaCantidad);
            stmt.setString(2, cod_art);

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("✅ Stock actualizado.");
                this.cantidad_disponible = nuevaCantidad;
            } else {
                System.out.println("⚠️ No se encontró el artículo.");
                throw new DatabaseException("El artículo no fue encontrado para actualizar el stock.");
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
            throw new DatabaseException("Error al ejecutar la consulta para actualizar el stock.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
                e.printStackTrace();
                throw new DatabaseException("Error al cerrar los recursos después de actualizar el stock.");
            }
        }
    }

    public static void resArticulo(String cod_art) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConexion();
            if (conn == null) {
                throw new DatabaseException("❌ Error al conectar a la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la conexión: " + e.getMessage());
            e.printStackTrace();
            throw new DatabaseException("Error al obtener la conexión para reducir el stock.");
        }

        String sql = "UPDATE articulo SET cantidad_disponible = cantidad_disponible - 1 WHERE cod_art = ? AND cantidad_disponible > 0";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cod_art);

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("✅ Stock actualizado correctamente para el artículo: " + cod_art);
            } else {
                System.out.println("⚠️ No se pudo actualizar el stock. Puede que no haya suficiente cantidad disponible.");
                throw new DatabaseException("No se pudo actualizar el stock, la cantidad es insuficiente.");
            }
        } catch (SQLException e) {
            System.err.println("⚠️ Error al actualizar el stock del artículo: " + e.getMessage());
            throw new DatabaseException("Error al intentar reducir el stock del artículo.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
                e.printStackTrace();
                throw new DatabaseException("Error al cerrar los recursos después de actualizar el stock.");
            }
        }
    }
}
