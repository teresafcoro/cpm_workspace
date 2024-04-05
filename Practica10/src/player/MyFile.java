package player;

import java.io.*;

/**
 * Clase MyFile
 * Especifica las caracteristicas de los ficheros leidos por el Reproductor MP3
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class MyFile {
	
	// Atributo fichero
	private File f;
	
	/**
	 * Constructor
	 * @param f, File
	 */
	public MyFile(File f){
		this.f = f;
	}
	
	/**
	 * Obtiene el fichero
	 * @return f, File
	 */
	public File getF() {
		return f;
	}

	/**
	 * Formato String del objeto fichero
	 * Sin la extension '.mp3'
	 */
	public String toString() {
		return f.getName().split(".mp3")[0];
	}
	
}
