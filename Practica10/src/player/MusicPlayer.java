package player;

import java.io.File;
import javazoom.jlgui.basicplayer.*;

/**
 * Clase MusicPlayer
 * Especifica las caracteristicas del EII MusicPlayer
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class MusicPlayer {

	// Atributos
	private BasicPlayer basicPlayer = null;
	private File cancionSonando;
	private boolean isAleatorio;

	/**
	 * Constructor
	 */
	public MusicPlayer() {
		basicPlayer = new BasicPlayer();
		cancionSonando = null;
		isAleatorio = false;
	}

	/**
	 * Permite reproducir una cancion
	 * @param file, File
	 */
	public void play(File file) {
		try {
			basicPlayer.open(file);
			basicPlayer.play();
		} catch (Exception e) {
		}
	}

	/**
	 * Permite parar la cancion reproducida
	 */
	public void stop() {
		try {
			basicPlayer.stop();
		} catch (BasicPlayerException e) {
		}
	}

	/**
	 * Permite establecer un volumen determinado
	 * @param vol, double
	 * @param volMax, double
	 */
	public void setVolume(double vol, double volMax) {
		try {
			basicPlayer.setGain(vol/volMax);
		} catch (BasicPlayerException e) {
		}
	}

	/**
	 * Obtiene la cancion que se esta reproduciendo
	 * @return cancionSonando, File
	 */
	public File getCancionSonando() {
		return cancionSonando;
	}

	/**
	 * Asigna la cancion que ha reproducir
	 * @param cancionSonando, File
	 */
	public void setCancionSonando(File cancionSonando) {
		this.cancionSonando = cancionSonando;
	}

	/**
	 * Comprueba si se esta reproduciendo canciones de forma aleatoria
	 * @return isAleatorio, boolean
	 */
	public boolean isAleatorio() {
		return isAleatorio;
	}

	/**
	 * Establece si reproducir canciones de forma aleatoria
	 * @param isAleatorio, boolean
	 */
	public void setAleatorio(boolean isAleatorio) {
		this.isAleatorio = isAleatorio;
	}

}
