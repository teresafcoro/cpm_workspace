package logica;

/**
 * Clase Tablero del juego Invasion Espacial
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class Tablero {
	
	// Atributos
	public static final int DIM = 8;
	private Casilla[] casillas;
	
	/**
	 * Contructor
	 * Inicializa el array de casillas y establece la posicion de los elementos del juego
	 */
	public Tablero() {
		casillas = new Casilla[DIM];
		for (int i = 0; i < DIM; i++)
			casillas[i] = new Espacio(i);
		int posicionInvasor = (int) (Math.random() * DIM);
		casillas[posicionInvasor] = new Invasor(posicionInvasor);
		int posicionMeteorito = (int) (Math.random() * DIM);
		while (posicionInvasor == posicionMeteorito)
			posicionMeteorito = (int) (Math.random() * DIM);
		casillas[posicionMeteorito] = new Meteorito(posicionMeteorito);
		int posicionEscudo = (int) (Math.random() * DIM);
		while (posicionInvasor == posicionEscudo || posicionMeteorito == posicionEscudo)
			posicionEscudo = (int) (Math.random() * DIM);
		casillas[posicionEscudo] = new Escudo(posicionEscudo);
	}

	/**
	 * Obtiene un array de casillas
	 * @return casillas, Casilla[]
	 */
	public Casilla[] getCasillas() {
		return casillas;
	}
	
	/**
	 * Asigna un array de casillas
	 * @param casillas, Casilla[]
	 */
	public void setCasillas(Casilla[] casillas) {
		this.casillas = casillas;
	}

}
