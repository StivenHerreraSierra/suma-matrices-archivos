/**
 * Clase encargada de los procesos relacionados con archivos y directorios.
 */
package co.uniquindio.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Stiven Herrera Sierra.
 */
public class Persistencia {
	/**
	 * Captura el contenido de un archivo de texto y lo almacena en una lista.
	 * @param ruta Ruta del archivo.
	 * @param texto Lista con el contenido.
	 * @throws IOException Excepción de lectura.
	 */
	public static ArrayList<String> leerArchivo (String ruta) 
			throws IOException {
		FileReader caracter = new FileReader (ruta);
		BufferedReader lineas = new BufferedReader (caracter);
		ArrayList<String> texto = new ArrayList<String>();
		String linea;
		while ((linea = lineas.readLine()) != null) {
			texto.add(linea);
		}
		lineas.close();
		caracter.close();
		return texto;
	}
	
	/**
	 * Escribe texto a un archivo indicado por el usuario a través de una
	 * dirección.
	 * @param ruta Ruta del archivo.
	 * @param texto Texto a escribir.
	 * @throws IOException Excepción de escritura.
	 */
	public static void escribirArchivo (String ruta, ArrayList<String> texto) 
			throws IOException {
		FileWriter caracter = new FileWriter (ruta);
		BufferedWriter lineas = new BufferedWriter (caracter);
		for (String linea : texto) {
			lineas.write(linea + "\n");
		}
		lineas.close();
		caracter.close();
	}
	
	/**
	 * Lista las direcciones de los archivos con extensión .txt contenido en un
	 * directorio de la ruta indicada por el usuario.
	 * @param rutaPrincipal Ruta del directorio.
	 * @param direcciones Lista de direcciones.
	 */
	public static void listarDireccionesArchivos (String rutaPrincipal,
			ArrayList<String> direcciones){
		File archivo = new File(rutaPrincipal);
		File[] contenido = archivo.listFiles();
		String ruta = "";
		for (File indice : contenido) {
			ruta = indice.getPath();
			if (indice.isFile() &&
				ruta.substring(ruta.length() - 4).equals(".txt")) {
				direcciones.add(ruta);
			}
			if (indice.isDirectory()) {
				listarDireccionesArchivos(ruta, direcciones);
			}
		}
	}
}
