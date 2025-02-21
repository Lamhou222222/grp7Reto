package Reto;


public class Bicicleta extends Articulo {
    private double velocidadMax;

    public Bicicleta(String cod_art, String tipo, String nombre, int cantidad_disponible, int precio_dia, String tienda, double velocidadMax) {
        super(cod_art, tipo, nombre, cantidad_disponible, precio_dia, tienda);
        this.velocidadMax = velocidadMax;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println(super.toString() + " - Velocidad Máx: " + velocidadMax + " km/h");
    }
}
