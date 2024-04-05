package logica;

/**
 * Establece las caracteristicas o propiedades de cada objeto del pedido o carta
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class Articulo {
	
	// Atributos
	private String codigo;
	private String tipo;
	private String denominacion;
	private float precio;
	private int unidades;
	
	/**
	 * Constructor
	 * Da valor a las propiedades de este objeto articulo
	 * @param codigo, String
	 * @param tipo, String
	 * @param denominacion, String
	 * @param precio, float
	 * @param unidades, Integer
	 */
	public Articulo(String codigo, String tipo, String denominacion, float precio, int unidades) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.denominacion = denominacion;
		this.precio = precio;
		this.unidades = unidades;
	}
	
	/**
	 * Obtiene su codigo
	 * @return codigo, String
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Obtiene su precio
	 * @return precio, float
	 */
	public float getPrecio() {
		return precio;
	}
	
	/**
	 * Obtiene su numero de unidades
	 * @return unidades, Integer
	 */
	public int getUnidades() {
		return unidades;
	}

	/**
	 * Establece su numero de unidades
	 * @param unidades, Integer
	 */
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	/**
	 * Formato String del objeto
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.tipo);
		buffer.append(" - ");
		buffer.append(this.denominacion);
		buffer.append(" - ");
		buffer.append(this.precio);
		buffer.append(" €");
		if (this.unidades!=0) {
			buffer.append(" (");
			buffer.append(this.unidades);
			buffer.append(" uds)");
		}
		return buffer.toString();
	}
	
}
