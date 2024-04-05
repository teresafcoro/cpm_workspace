package logica;

/**
 * Nuevo elemento del juego
 * Hereda de la clase Casilla
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class Meteorito extends Casilla {

	// Atributo
	boolean eliminado;
	
	/**
	 * Constructor
	 * Inicializa los atributos del meteorito
	 * @param posicion, integer
	 */
	public Meteorito(int posicion) {
		setPosicion(posicion);
		System.out.println("Posición meteorito: " + getPosicion());
		setPuntos(0);
		setEliminado(false);
	}
	
	/**
	 * Comprueba si fue eliminado
	 * @return eliminado, boolean
	 */
	public boolean isEliminado() {
		return eliminado;
	}
	
	/**
	 * Establece si fue eliminado
	 * @param eliminado, boolean
	 */
	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	
}
