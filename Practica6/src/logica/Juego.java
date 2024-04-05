package logica;

/**
 * Clase Juego
 * Simula la ejecucion del juego Invasion Espacial
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class Juego {
	
	// Atributos
	public static final int maxDisparos = 4;
	private int puntos;
	private int disparos; 
	private boolean invasorEncontrado;
	private boolean meteoritoEncontrado;
	
	// Clase Tablero
	private Tablero tablero;
	
	/**
	 * Constructor
	 * Inicializa el juego
	 */
	public Juego() {
		inicializarJuego();
	}
	
	/**
	 * Inicializa el juego
	 */
	public void inicializarJuego() {
		tablero = new Tablero();
		puntos = 800;
		disparos = 0;
		invasorEncontrado = false;
		meteoritoEncontrado = false;
	}
	
	/**
	 * Obtiene el maximo numero de disparos posibles
	 * @return maxDisparos, Integer
	 */
	public static int getMaxdisparos() {
		return maxDisparos;
	}
	
	/**
	 * Obtiene el tablero del juego
	 * @return tablero, Tablero
	 */
	public Tablero getTablero() {
		return this.tablero;
	}

	/**
	 * Obtiene los puntos obtenidos por el usuario
	 * @return puntos, Integer
	 */
	public int getPuntos() {
		return this.puntos;
	}
	
	/**
	 * Obtiene el numero de disparos que puede usar el usuario
	 * @return disparos, Integer
	 */
	public int getDisparos() {
		return this.disparos;
	}
	
	/**
	 * Asigna el numero de disparos que puede usar el usuario
	 * @param disparos, Integer
	 */
	private void setDisparos(int disparos) {
		this.disparos = disparos;
	}
	
	/**
	 * Obtiene si el invasor fue encontrado 
	 * @return invasorEncontrado, boolean
	 */
	public boolean isInvasorEncontrado() {
		return this.invasorEncontrado;
	}

	/**
	 * Obtiene si el meteorito fue encontrado 
	 * @return meteoritoEncontrado, boolean
	 */
	public boolean isMeteoritoEncontrado() {
		return this.meteoritoEncontrado;
	}

	/**
	 * Simula la ejecucion de los disparos por parte del usuario
	 * @param i, Integer
	 */
	public void dispara(int i) {
		disparos--;
		puntos += tablero.getCasillas()[i].getPuntos();
		if (tablero.getCasillas()[i] instanceof Invasor) {
			((Invasor)tablero.getCasillas()[i]).setEliminado(true);
			invasorEncontrado = true;
		}
		else if (tablero.getCasillas()[i] instanceof Meteorito) {
			((Meteorito)tablero.getCasillas()[i]).setEliminado(true);
			meteoritoEncontrado = true;
			puntos = 0;
		}
	}
	
	/**
	 * Comprueba si la partida ha finalizado
	 * @return boolean
	 */
	public boolean isPartidaFinalizada() {
		return (invasorEncontrado || meteoritoEncontrado || disparos == 0);
	}

	/**
	 * Simula el lanzamiento del dado
	 * Para ello hace una llamada a la clase Dado
	 */
	public void lanzar() {
		int n = Dado.lanzar();
		setDisparos(n);	
		System.out.println("Han salido: " + n + " disparos");
	}

}
