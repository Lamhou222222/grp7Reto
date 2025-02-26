package Reto;

public interface ArticuloInterface {
	
	    void mostrarDetalles();
	    void guardarEnBD();
	    void eliminarDeBD();
	    void actualizarStock(int nuevaCantidad);
	    static void mostrarArticulos(String dni) {}
	    static void resArticulo(String cod_art) {}
}
