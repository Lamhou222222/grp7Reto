package Reto;


public class Kayakas extends Articulo {
    private int asientos;

    // Constructor
    public Kayakas(String cod_art, String tipo, String nombre, int cantidad_disponible, int precio_dia, String tienda, int asientos) {
        super(cod_art, tipo, nombre, cantidad_disponible, precio_dia, tienda);
        this.asientos = asientos;
    }

    // Getter y Setter
    public int getAsientos() { return asientos; }
    public void setAsientos(int asientos) { this.asientos = asientos; }

    // Implementación del método abstracto
    @Override
    public void mostrarDetalles() {
        System.out.println(super.toString() + " - Asientos: " + asientos);
    }
}
