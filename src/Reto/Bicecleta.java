package Reto;

public class Bicecleta extends Articulo {
	
	public double getVelocidadMax() {
		return velocidadMax;
	}

	public void setVelocidadMax(double velocidadMax) {
		this.velocidadMax = velocidadMax;
	}

	@Override
	public String toString() {
		return "Bicecleta [velocidadMax=" + velocidadMax + "]";
	}

	private double velocidadMax;

	public Bicecleta(String cod_art, String tipo, String nombre, int cantidad_disponible, int precio_dia,String tienda,double velo) {
		super(cod_art, tipo, nombre, cantidad_disponible, precio_dia, tienda);
		velocidadMax=velo;
		// TODO Auto-generated constructor stub
	}
	
	
	
}
