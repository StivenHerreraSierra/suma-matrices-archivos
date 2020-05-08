/**
 * Clase encargada de los procesos generales en común con varias clases.
 */
package co.uniquindio.procesos;

import java.util.regex.Pattern;

/**
 * @author Stiven Herrera Sierra.
 */
public class Procesos {
	/**
	 * Descompone una linea de texto a partir de un patrón indicado.
	 * @param patron Patrón de partición.
	 * @param linea Línea a particionar.
	 * @return Arreglo con el contenido particionado.
	 */
	public static String[] particionarSplit (String patron, String linea) {
		Pattern pattern = Pattern.compile(patron);
		String[] particion = pattern.split(linea);
		return particion;
	}
}
