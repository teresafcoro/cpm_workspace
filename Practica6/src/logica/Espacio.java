package logica;

/**
 * Elemento del juego
 * Hereda de la clase Casilla
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class Espacio extends Casilla {
		
	/**
	 * Constructor
	 * Inicializa los atributos del espacio
	 * @param posicion, integer
	 */
	public Espacio(int posicion) {
		setPosicion (posicion);
		setPuntos(-200);
	}
	
}
