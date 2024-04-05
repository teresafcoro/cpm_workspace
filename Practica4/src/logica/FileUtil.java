package logica;

import java.io.*;
import java.util.*;

/**
 * Clase encargada de leer/escribir de/en ficheros.dat los articulos
 * Establece un nombre aleatorio a cada pedido/ticket de la compra
 * @author UO263728
 */
public abstract class FileUtil {
	
	public static void loadFile(String nombreFicheroEntrada, List<Articulo> listaCatalogo) {
	    String linea;
	    String[] datosArticulo = null;	   
	    try {
	    	BufferedReader fichero = new BufferedReader(new FileReader(nombreFicheroEntrada));
	    	while (fichero.ready()) {
	    		linea = fichero.readLine();
	    		datosArticulo = linea.split("@");
	    		listaCatalogo.add(new Articulo(datosArticulo[0],datosArticulo[1],datosArticulo[2],Float.parseFloat(datosArticulo[3]),0));
	    	}
	    	fichero.close();
	    } catch (FileNotFoundException fnfe) {
	    	System.out.println("El archivo no se ha encontrado.");
	    } catch (IOException ioe) {
	    	new RuntimeException("Error de entrada/salida.");
	    }
	}
	
	public static void saveToFile(String nombreFicheroSalida, List<Articulo> listaPedido, String tipoPedido) {
		try {
			BufferedWriter fichero = new BufferedWriter(new FileWriter("files/" + nombreFicheroSalida + ".dat"));
		    String linea = listaPedido.toString();
		    fichero.write(linea);
		    fichero.write(tipoPedido);
		    fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha podido guardar");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida");
		}
	}
	
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
