package logica;

/**
 * Clase Juego
 * Simula la ejecucion del juego Invasion Espacial
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class Juego {

	// Atributos
	private static int maxDisparos;
	private int puntos;
	private int disparos;
	private boolean invasorEncontrado;
	private boolean meteoritoEncontrado;
	private boolean escudoEncontrado;
	
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
		escudoEncontrado = false;
	}
	
	/**
	 * Obtiene el maximo numero de disparos posibles
	 * @return maxDisparos, Integer
	 */
	public static int getMaxdisparos() {
		return maxDisparos;
	}
	
	/**
	 * Asigna el maximo numero de disparos posibles
	 * @param maxdisparos, Integer
	 */
	public void setMaxdisparos(int maxdisparos) {
		maxDisparos = maxdisparos;
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
	 * Obtiene si el escudo fue encontrado 
	 * @return escudoEncontrado, boolean
	 */
	public boolean isEscudoEncontrado() {
		return escudoEncontrado;
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
		} else if (tablero.getCasillas()[i] instanceof Meteorito) {
			((Meteorito)tablero.getCasillas()[i]).setEliminado(true);
			if (!escudoEncontrado) {
				meteoritoEncontrado = true;
				puntos = 0;
			}
		} else if (tablero.getCasillas()[i] instanceof Escudo) {
			((Escudo)tablero.getCasillas()[i]).setEliminado(true);
			escudoEncontrado = true;
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
