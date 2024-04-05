package logica;

import java.util.*;

/**
 * Contiene la lista de articulos que el cliente desea adquirir
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class Pedido {
	
	// Lista con los articulos del pedido
	private List<Articulo> listaPedido = null;
	
	// Atributo
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
	 * Elimina un articulo o ciertas unidades de uno ya en el pedido
	 * @param articuloPedido, Articulo
	 * @param unidades,  Integer
	 */
	public void remove(Articulo articuloPedido, int unidades) {
		int totalUnidades = udArticuloEnPedido(articuloPedido) - unidades;
		for (int i = 0; i < listaPedido.size(); i++) {
			if (listaPedido.get(i).getCodigo().equals(articuloPedido.getCodigo()))
				listaPedido.remove(listaPedido.get(i));
		}
		if (totalUnidades > 0) {
			Articulo articuloAPedido = articuloPedido;
			articuloAPedido.setUnidades(totalUnidades);
			listaPedido.add(articuloAPedido);
		}
	}
	
	/**
	 * Calcula el precio total del pedido
	 * @return total, float
	 */
	public float calcularTotal() {
		float total = 0.0f;
		for (Articulo a : listaPedido)
			total += (a.getPrecio()*a.getUnidades());
		if (total >= 50)
			McHappyDay = true;
		else
			McHappyDay = false;
		return total;
	}
	
	/**
	 * Graba el pedido en un fichero .dat
	 * Para ello llama al metodo saveToFile de FileUtil
	 * @param nombreFichero, String
	 * @param tipo, String
	 */
	public void grabarPedido(String nombreFichero, String tipo) {
		FileUtil.saveToFile(nombreFichero, listaPedido, tipo);
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

	/**
	 * Formato String del pedido
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (Articulo a : listaPedido) {
			buffer.append(a.getDenominacion());
			buffer.append(" - ");
			buffer.append(a.getPrecio());
			buffer.append("€");
			buffer.append(" (");
			buffer.append(a.getUnidades());
			buffer.append(" uds)\n");
		}
		buffer.append("Total: ");
		String precio = String.format("%.2f", calcularTotal());
		buffer.append(precio);
		return buffer.toString();
	}

}
