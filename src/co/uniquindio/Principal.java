/**
 * Sumar las matrices almacenadas en archivos de texto dentro de un directorio
 * indicado por el usuario.
 * Recuerde que es necesario que las matrices tengan la misma dimensión.
 * 
 * 1. Cargar directorio.
 * 2. Listar las direcciones de los archivos de texto.
 * 3. Ciclo:
 *   3.1: Cargar la primer dirección en una matriz y su dimensión en cadena.
 *   3.2: Ciclo:
 *   	3.2.1: Cargar el archivo siguiente en una matriz.
 *   	3.2.2: Condición: comparar la primer fila con la dimensión 1.
 *   		3.2.2.1: Si son iguales -> sumar su contenido y guardar en la matriz
 *   								   de la primer dirección y remover su semejante.
 *   3.3: Fin del ciclo.
 * 	 3.4: Escribir el archivo correspondiente al resultado.
 * 4. Fin del ciclo.
 * 
 * --Suma--
 * 1. Recibe una matriz y una lista con el contenido del segundo archivo. 
 * 2. Particionar la segunda lista.
 * 3. Sumar cada elemento desde la fila #1 (Segunda fila).
 * 4. Almacenar en una matriz temporal.
 * 5. Retornar el resultado en la matriz temporal.
 */
package co.uniquindio;

import java.io.IOException;
import java.util.ArrayList;

import co.uniquindio.persistencia.Persistencia;
import co.uniquindio.procesos.Procesos;

/**
 * @author Stiven Herrera Sierra.
 */
public class Principal {
	private String ruta = "src/";
	/**
	 * Método principal del programa.
	 * @param args Parámetros opcionales de ejecución. 
	 */
	public static void main(String[] args) {
		try {
			new Principal ();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Constructor sin parámetros de la clase.
	 */
	public Principal () {
		ArrayList<String> direcciones = new ArrayList<String>();
		ArrayList<String> texto = null;
		int[][] matrizActual = null;
		String dimension = "";
		String direccion = "";
		Persistencia.listarDireccionesArchivos(ruta, direcciones);
		while (direcciones.size() > 0) {
			try {
				direccion = direcciones.remove(0);
				texto = Persistencia.leerArchivo(direccion);
				matrizActual = ArrayList_Int(texto);
				dimension = texto.remove(0);
				matrizActual = compararMatrices (dimension, matrizActual,
						 direcciones);
				texto = int_Arraylist (matrizActual);
				Persistencia.escribirArchivo(ruta + "SumaDimension" +
					matrizActual[0][0] + "_" + matrizActual[0][1] + ".txt", texto);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Transfiere el contenido de una lista String a una matriz de enteros.
	 * @param texto Lista con el texto.
	 * @param dimension Tamaño de la matriz.
	 * @return Matriz con los elementos enteros.
	 */
	public int[][] ArrayList_Int (ArrayList<String> texto){
		String[] particion = Procesos.particionarSplit("[,]+", texto.get(0));
		int fila = Integer.parseInt(particion[0]);
		int columna = Integer.parseInt(particion[1]);
		int[][] matriz = new int[fila + 1][columna];
		for (int i = 0; i < texto.size(); i++) {
			particion = Procesos.particionarSplit("[,]+", texto.get(i));
			for (int j = 0; j < particion.length; j++) {
				matriz[i][j] = Integer.parseInt(particion[j]);
			}
		}
		return matriz;
	}
	
	/**
	 * Tranfiere el contenido de una matriz de enteros a una lista de String.
	 * @param matriz Matriz con números.
	 * @param texto Lista que almacena los valores.
	 */
	public ArrayList<String> int_Arraylist (int[][] matriz) {
		String temporal = matriz[0][0] + "," + matriz[0][1];
		ArrayList<String> texto =new ArrayList<String>();
		texto.add(temporal);
		for (int i = 1; i < matriz.length; i++) {
			temporal = "";
			for (int j = 0; j < matriz[i].length; j++) {
				temporal = temporal + matriz[i][j];
				if (j < (matriz[i].length - 1)) {
					temporal = temporal + ",";
				}
			}
			texto.add(temporal);
		}
		return texto;
	}
	
	/**
	 * Suma las matrices de igual dimensión a la matriz indicada.
	 * @param dimension Dimensiones de la matriz principal.
	 * @param matriz Matriz principal.
	 * @param direcciones Direcciones de los archivos txt del directorio.
	 * @return Matriz de enteros con los resultados.
	 * @throws IOException Excepción de lectura.
	 */
	public int[][] compararMatrices(String dimension, int[][] matriz,
			ArrayList<String> direcciones) throws IOException {
		ArrayList<String> archivo = new ArrayList<String>();
		String ruta;
		for (int i = 0; i < direcciones.size(); i++) {
			ruta = direcciones.get(i);
			archivo = Persistencia.leerArchivo(ruta);
			if (archivo.get(0).equals(dimension)) {
				matriz = sumarMatrices (matriz, ArrayList_Int(archivo));
				direcciones.remove(ruta);
			}
		}
		return matriz;
	}
	
	/**
	 * Suma los elementos de las matrices indicadas.
	 * @param matrizA Matriz con los primeros sumandos.
	 * @param matrizB Matriz con los segundos sumandos.
	 * @return Matriz con el total.
	 */
	public int[][] sumarMatrices (int[][] matrizA, int[][] matrizB){
		int[][] resultado = new int[matrizA.length][matrizA[1].length];
		resultado[0][0] = matrizA[0][0];
		resultado[0][1] = matrizA[0][1];
		for (int i = 1; i < resultado.length; i++) {
			for (int j = 0; j < matrizA[i].length; j++) {
				resultado[i][j] = matrizA[i][j] + matrizB[i][j];
			}
		}
		return resultado;
	}
	
	/**
	 * Imprime los elementos contenidos en una matriz.
	 * @param mat 
	 */
	public void imprimirMatrizInt (int[][]matriz){
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {		
				System.out.print(matriz[i][j] + " | ");
			}
			System.out.println();
		}
	}
}
