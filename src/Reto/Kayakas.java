package Reto;

public class Kayakas extends Articulo {
	
	public int getAsientos() {
		return asientos;
	}

	public void setAsientos(int asientos) {
		this.asientos = asientos;
	}

	private int asientos;

	@Override
	public String toString() {
		return "Kayakas [asientos=" + asientos + "]";
	}

	public Kayakas(String cod_art, String tipo, String nombre, int cantidad_disponible, int precio_dia, Tienda tienda,int asientoss) {
		super(cod_art, tipo, nombre, cantidad_disponible, precio_dia, tienda);
		asientos=asientoss;
	}

}
