package logica;

/**
 * Claso Dado
 * Simula el lanzamiento de un dado
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class Dado {
	
	/**
	 * Lanza el dado y obtiene un valor entre 2 y 4
	 * @return el valor obtenido, Integer
	 */
	public static int lanzar() { 
		return ((int) (Math.random() * Juego.getMaxdisparos()) + 1);
	}

}
