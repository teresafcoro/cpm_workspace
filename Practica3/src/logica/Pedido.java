package logica;

import java.util.*;

/**
 * Contiene la lista de articulos que el cliente desea adquirir
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class Pedido {
	
	// Lista con los articulos del pedido
	private List<Articulo> listaPedido = null;
	
	// Atributos
	private boolean McHappyDay = false;
	
	/**
	 * Constructor
	 * Inicializa la lista de articulos del pedido
	 */
	public Pedido() {
		listaPedido = new ArrayList<Articulo>();
	}

	/**
	 * Obtiene la lista de articulos del pedido
	 * @return List
	 */
	public List<Articulo> getListaPedido() {
		return this.listaPedido;
	}
	
	/**
	 * Inicializa la lista de articulos del pedido
	 */
	public void inicializar() {
		listaPedido.clear();
		McHappyDay = false;
	}
	
	/**
	 * Añade un nuevo elemento a la lista de articulos del pedido
	 * o bien, incrementa las unidades de un articulo ya existente
	 * en la lista del pedido
	 * @param articuloDelCatalogo, articulo a añadir, Articulo
	 * @param unidades, unidades a añadir/incrementar de un articulo, Integer
	 */
	public void add(Articulo articuloDelCatalogo, int unidades) {
		Articulo articuloEnPedido = null;
		for (Articulo a : listaPedido) {
			if (a.getCodigo().equals(articuloDelCatalogo.getCodigo()))
				articuloEnPedido = a;
		}
		if (articuloEnPedido == null) {
			Articulo articuloAPedido = articuloDelCatalogo;
			articuloAPedido.setUnidades(unidades);
			listaPedido.add(articuloAPedido);
		} else
			articuloEnPedido.setUnidades(articuloEnPedido.getUnidades() + unidades);
	}
	
	/**
	 * Calcula el precio total del pedido
	 * @return total, float
	 */
	public float calcularTotal() {
		float total = 0.0f;
		for (Articulo a : listaPedido)
			total += a.getPrecio()*a.getUnidades();
		if (total >= 50)
			McHappyDay = true;
		return total;
	}
	
	/**
	 * Graba el pedido en un fichero .dat
	 * Para ello llama al metodo saveToFile de FileUtil
	 * @param nombreFichero, String
	 */
	public void grabarPedido(String nombreFichero) {
		FileUtil.saveToFile(nombreFichero, listaPedido);
	}
	
	/**
	 * Obtiene si se aplica el descuento
	 * @return true, si asi es
	 * false, en otro caso
	 */
	public boolean getMcHappyDay() {
		return McHappyDay;
	}
	
	/**
	 * Comprueba las unidades en el pedido de un cierto articulo
	 * @param articuloSeleccionado, Articulo
	 * @return 0, si no se encuentra en el pedido dicho articulo
	 * o el valor de unidades de este en el pedido, Integer
	 */
	public int udArticuloEnPedido(Articulo articuloSeleccionado) {
		for (Articulo a : listaPedido) {
			if (a.getCodigo().equals(articuloSeleccionado.getCodigo()))
				return a.getUnidades();
		}
		return 0;
	}

}
