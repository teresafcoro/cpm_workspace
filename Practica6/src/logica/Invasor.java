package logica;

/**
 * Elemento del juego
 * Hereda de la clase Casilla
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class Invasor extends Casilla {

	// Atributo
	boolean eliminado;

	/**
	 * Constructor
	 * Inicializa los atributos del invasor
	 * @param posicion, integer
	 */
	public Invasor(int posicion) {
		setPosicion(posicion);
		System.out.println("Posición invasor: " + getPosicion());
		setPuntos(3000);
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
