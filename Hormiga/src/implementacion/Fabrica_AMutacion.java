package implementacion;

import amutacion.AMutacion;
import amutacion.AMutacion_Arbol;
import amutacion.AMutacion_Funcion;
import amutacion.AMutacion_Terminal;

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
		case "Terminal":
			return new AMutacion_Terminal();
		case "Funcion":
			return new AMutacion_Funcion();
		case "Arbol":
			return new AMutacion_Arbol();
		default: return null;
		}
	}

}
