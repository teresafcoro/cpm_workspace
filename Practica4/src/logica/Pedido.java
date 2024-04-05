package logica;

import java.util.*;

/**
 * Contiene la lista de articulos que el cliente desea adquirir
 * @author UO263728
 */
public class Pedido {
	
	private List<Articulo> listaPedido = null;
	private boolean McHappyDay = false;
	private String tipoPedido;
	
	public Pedido() {
		listaPedido = new ArrayList<Articulo>();
	}

	public List<Articulo> getListaPedido() {
		return this.listaPedido;
	}
	
	public void add(Articulo articuloDelCatalogo, int unidades) {
		Articulo articuloEnPedido = null;
		
		for (Articulo a : listaPedido) {
			if (a.getCodigo().equals(articuloDelCatalogo.getCodigo()))
				articuloEnPedido = a;
		}
		
		if (articuloEnPedido == null) {
			Articulo articuloAPedido = new Articulo(articuloDelCatalogo);
			articuloAPedido.setUnidades(unidades);
			listaPedido.add(articuloAPedido);
		} else
			articuloEnPedido.setUnidades(articuloEnPedido.getUnidades() + unidades);
	}
	
	public int remove(Articulo artSeleccionado, int udSeleccionadas) {
		Articulo articuloEnPedido = null;

		for (Articulo a : listaPedido) {
			if (a.getCodigo().equals(artSeleccionado.getCodigo()))
				articuloEnPedido = a;
		}
		
		int totalUd = articuloEnPedido.getUnidades()-udSeleccionadas;
		
		if (articuloEnPedido == null || totalUd < 0)
			return -1;
		else if (totalUd == 0)
			listaPedido.remove(articuloEnPedido);
		else
			articuloEnPedido.setUnidades(totalUd);
		return 0;
	}
	
	public float calcularTotal() {
		float total = 0.0f;
		for (Articulo a : listaPedido)
			total += a.getPrecio() * a.getUnidades();
		if (total >= 50)
			setMcHappyDay(true);
		else
			setMcHappyDay(false);
		return total;
	}
	
	public int udEnPedido(Articulo articuloSeleccionado) {
		for (int i = 0; i < listaPedido.size(); i++) {
			if (articuloSeleccionado.getDenominacion().equals(listaPedido.get(i).getDenominacion()))
				return listaPedido.get(i).getUnidades();
		}
		return 0;
	}
	
	public void grabarPedido(String nombreFichero) {
		FileUtil.saveToFile(nombreFichero, listaPedido, tipoPedido);
	}

	public void inicializar() {
		listaPedido.clear();
		setMcHappyDay(false);
	}

	public boolean getMcHappyDay() {
		return McHappyDay;
	}
	public void setMcHappyDay(boolean mcHappyDay) {
		McHappyDay = mcHappyDay;
	}
	
	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (Articulo a : listaPedido) {
			buffer.append(a.getDenominacion());
			buffer.append(" - ");
			buffer.append(a.getUnidades());
			buffer.append(" uds.\n");
		}
		buffer.append("Total: ");
		String precio = String.format("%.2f", calcularTotal());
		buffer.append(precio);
		buffer.append(" €");
		return buffer.toString();
	}

}
