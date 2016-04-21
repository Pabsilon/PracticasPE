package implementation;

import amutacion.AMutacion;
import amutacion.AMutacion_Heuristica;
import amutacion.AMutacion_Insercion;
import amutacion.AMutacion_Intercambio;
import amutacion.AMutacion_Inversion;
import amutacion.AMutacion_Propio;

/** Implementacion de la fabrica de Mutacion
 * @author Pablo Mac-Veigh
 *
 */
public class Fabrica_AMutacion {
	
	/**Fabrica de algoritmos de mutacion
	 * @param algoritmo El string del nombre del algoritmo
	 * @return el algoritmo inicializado.
	 */
	public static AMutacion generarAlgoritmoMutacion(String algoritmo){
		// {"Insercion", "Intercambio", "Inversion", "Heuristica", "Propio"};
		switch (algoritmo){
		case "Insercion":
			return new AMutacion_Insercion();
		case "Intercambio":
			return new AMutacion_Intercambio();
		case "Inversion":
			return new AMutacion_Inversion();
		case "Heuristica":
			return new AMutacion_Heuristica();
		case "Propio":
			return new AMutacion_Propio();
		default: return null;
		}
	}

}
