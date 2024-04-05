package logica;

/**
 * Superclase Casilla
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public abstract class Casilla {
	
	// Atributos comunes de las subclases que hereden de esta
	private int posicion;
	private int puntos;
	
	/**
	 * Obtiene la posicion de la casilla
	 * @return posicion, Integer
	 */
	public int getPosicion() {
		return posicion;
	}
	
	/**
	 * Asigna la posicion de la casilla
	 * @param posicion, Integer
	 */
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
	/**
	 * Obtiene los puntos de la casilla
	 * @return puntos, Integer
	 */
	public int getPuntos() {
		return puntos;
	}
	
	/**
	 * Asigna los puntos de la casilla
	 * @param puntos, Integer
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
}
