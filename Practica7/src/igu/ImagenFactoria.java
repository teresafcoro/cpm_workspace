package igu;

import javax.swing.ImageIcon;
import logica.*;

/**
 * Clase ImagenFactoria
 * Se encarga de buscar y establecer imagenes a unos
 * elementos determinados de la interfaz de juego Inavsion Espacial
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class ImagenFactoria {

	// Atributos con la ruta de la imagen especificada
	private static final String IMAGEN_INVASOR = "/img/invader.jpg";
	private static final String IMAGEN_SPACE = "/img/space.jpg";
	private static final String IMAGEN_SHOOT = "/img/shoot.png";
	private static final String IMAGEN_METEORITO = "/img/meteorite.jpg";
	private static final String IMAGEN_ESCUDO = "/img/escudo.jpg";

	/**
	 * Obtiene la imagen de una determinada casilla
	 * @param casilla, Casilla
	 * @return ImageIcon
	 */
	public static ImageIcon getImagen(Casilla casilla) {
		if (casilla instanceof Invasor)
			return cargaImagen(IMAGEN_INVASOR);
		else if (casilla instanceof Espacio)
			return cargaImagen(IMAGEN_SPACE);
		else if (casilla instanceof Meteorito)
			return cargaImagen(IMAGEN_METEORITO);
		else if (casilla instanceof Escudo)
			return cargaImagen(IMAGEN_ESCUDO);
		return null;
	}

	/**
	 * Obtiene la imagen del disparo
	 * @return ImageIcon
	 */
	public static ImageIcon getShootImage() {
		return cargaImagen(IMAGEN_SHOOT);
	}
	
	/**
	 * Carga la imagen pedida
	 * @param fichero, String
	 * @return ImageIcon
	 */
	private static ImageIcon cargaImagen(String fichero) {
		return new ImageIcon(ImagenFactoria.class.getResource(fichero));
	}
	
}
