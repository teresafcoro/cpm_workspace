package logica;

import java.util.*;

/**
 * Carta de articulos que puede comprar un cliente
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class Carta {
	
	// Fichero donde se encuentran los articulos de la carta inicialmente
	private static final String FICHERO_ARTICULOS = "files/articulos.dat";

	// Lista de Articulos que compondra la carta
	private List<Articulo> listaArticulos = null;

	/**
	 * Constructor Inicializa la lista de articulos de la carta y carga en esta los
	 * articulos que la componen
	 */
	public Carta() {
		listaArticulos = new ArrayList<Articulo>();
		cargarArticulos();
	}

	/**
	 * Obtiene de un fichero todos los articulos que compondran la carta
	 * Para ello llama al metodo loadFile de FileUtil
	 */
	private void cargarArticulos() {
		FileUtil.loadFile(FICHERO_ARTICULOS, listaArticulos);
	}

	/**
	 * Obtiene la lista de articulos de la carta
	 * Pero convertida en array
	 * @return articulos, Array de articulos
	 */
	public Articulo[] getArticulos() {
		return listaArticulos.toArray(new Articulo[listaArticulos.size()]);	
	}
	
	/**
	 * Filtra los articulos por tipo especificado
	 * @param tipo, String
	 * @return articulosFiltroFinal, Articulo[]
	 */
	public Articulo[] filtrar(String tipo) {
		List<Articulo> articulosFiltro = new ArrayList<Articulo>();
		for (int i = 0; i < listaArticulos.size(); i++) {
			if (tipo.equals(listaArticulos.get(i).getTipo()))
				articulosFiltro.add(listaArticulos.get(i));
		}
		return articulosFiltro.toArray(new Articulo[articulosFiltro.size()]);
	}
	
}
