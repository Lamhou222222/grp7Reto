package Reto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class Articulo {
    // Atributos
    private String cod_art;
    private String tipo;
    private String nombre;
    private int cantidad_disponible;
    private int precio_dia;
    private String tienda;

    // Constructor
    public Articulo(String cod_art, String tipo, String nombre, int cantidad_disponible, int precio_dia, String tienda) {
        this.cod_art = cod_art;
        this.tipo = tipo;
        this.nombre = nombre;
        this.cantidad_disponible = cantidad_disponible;
        this.precio_dia = precio_dia;
        this.tienda = tienda;
    }

    // Método abstracto que las subclases deben implementar
    public abstract void mostrarDetalles();

    // Getters y Setters
    public String getCod_art() { return cod_art; }
    public String getTipo() { return tipo; }
    public String getNombre() { return nombre; }
    public int getCantidad_disponible() { return cantidad_disponible; }
    public int getPrecio_dia() { return precio_dia; }
    public String getTienda() { return tienda; }

    public void setCantidad_disponible(int cantidad_disponible) {
        this.cantidad_disponible = cantidad_disponible;
    }

    // Método para mostrar datos del artículo
    @Override
    public String toString() {
        return "Artículo: " + nombre + " (" + tipo + ") - Precio/día: " + precio_dia + "€ - Stock: " + cantidad_disponible;
    }

    // ====================== MÉTODOS JDBC ======================

    // 🔹 Método para agregar un artículo a la BD
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

    // 🔹 Método para eliminar un artículo de la BD
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

    // 🔹 Método para actualizar la cantidad de artículos
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

    // 🔹 Método estático para mostrar todos los artículos de la BD
    public static void mostrarArticulos(String dni) {
        Connection conn = DBConnection.getConexion();
        if (conn == null) {
            System.out.println("Error al conectar con la base de datos.");
            return;
        }

        try {
            String sql = "SELECT * FROM articulo";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("📜 Lista de Artículos:");
            while (rs.next()) {
                System.out.println("Código: " + rs.getString("cod_art") +
                        " | Nombre: " + rs.getString("nombre") +
                        " | Precio: " + rs.getInt("precio_dia") +
                        " | Stock: " + rs.getInt("cantidad_disponible"));
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
	    try {
	        String sql = "UPDATE articulo SET cantidad_disponible = cantidad_disponible - 1 WHERE cod_art = ?";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setString(1, cod_art);
	        int rowsUpdated = stmt.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("✅ Stock actualizado para el artículo: " + cod_art);
	        } else {
	            System.out.println("⚠️ No se encontró el artículo con código: " + cod_art);
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al actualizar el stock: " + e.getMessage());
	    }
	}

}