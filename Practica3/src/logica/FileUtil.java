package logica;

import java.io.*;
import java.util.*;

/**
 * Clase encargada de leer/escribir de/en ficheros.dat los articulos
 * Establece un nombre aleatorio a cada pedido/ticket de la compra
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public abstract class FileUtil {
	
	/**
	 * Carga el contenido de un fichro .dat con los articulos de la carta 
	 * @param nombreFicheroEntrada, String
	 * @param listaCatalogo, List
	 */
	public static void loadFile(String nombreFicheroEntrada, List<Articulo> listaCatalogo) {
	    String linea;
	    String[] datosArticulo = null;	   
	    try {
	    	BufferedReader fichero = new BufferedReader(new FileReader(nombreFicheroEntrada));
	    	while (fichero.ready()) {
	    		linea = fichero.readLine();
	    		datosArticulo = linea.split("@");
	    		listaCatalogo.add(new Articulo(datosArticulo[0],datosArticulo[1],datosArticulo[2],
	    				Float.parseFloat(datosArticulo[3]),0));
	    	}
	    	fichero.close();
	    } catch (FileNotFoundException fnfe) {
	    	System.out.println("El archivo no se ha encontrado.");
	    } catch (IOException ioe) {
	    	new RuntimeException("Error de entrada/salida.");
	    }
	}
	
	/**
	 * Guarda el pedido realizado por un cliente en un fichero .dat
	 * @param nombreFicheroSalida, String
	 * @param listaPedido, List
	 */
	public static void saveToFile(String nombreFicheroSalida, List<Articulo> listaPedido) {
		try {
			BufferedWriter fichero = new BufferedWriter(new FileWriter("files/" + nombreFicheroSalida + ".dat"));
		    String linea = listaPedido.toString();
		    fichero.write(linea);
		    fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha podido guardar");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida");
		}
	}
	
	/**
	 * Crea un nombre de fichero aleatorio dada una base de caracteres
	 * Dicho nombre se asignara al fichero .dat con el pedido de un cliente
	 * @return codigo, String
	 */
	public static String setFileName() {
		String codigo = "";
		String base = "0123456789abcdefghijklmnopqrstuvwxyz";
		int longitudCodigo = 8;
		for (int i=0; i<longitudCodigo; i++) { 
			int numero = (int)(Math.random()*(base.length())); 
			codigo += base.charAt(numero);
		}
		return codigo;
	}
	
}
