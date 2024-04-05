package logica;

/**
 * Nuevo elemento del juego
 * Hereda de la clase Casilla
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class Escudo extends Casilla {

	// Atributo
	private boolean eliminado;
	
	/**
	 * Contructor
	 * Inicializa los atributos del escudo
	 * @param posicion, integer
	 */
	public Escudo(int posicion) {
		setPosicion(posicion);
		System.out.println("Posición escudo: " + getPosicion());
		setPuntos(500);
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
