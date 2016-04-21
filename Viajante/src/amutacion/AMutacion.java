package amutacion;

import java.util.Random;

import implementation.Cromosoma;

/**Interfaz de los algoritmos de Mutacion
 * @author Pablo Mac-Veigh
 *
 */
public interface AMutacion {

	/** Muta toda la poblacion dada.
	 * 
	 * Algoritmos disponibles: Heuristica, Insercion, Intercambio, Inversion, y Propio
	 * 
	 * @param poblacion Un array de cromosomas
	 * @param mutacionProb La probabilidad de mutacion float [0..1]
	 * @param rand El objeto random a utilizar (para usar la misma semilla)
	 */
	public void mutar(Cromosoma[] poblacion, float mutacionProb, Random rand);
	
}
