package implementacion;

import java.util.Random;

/**Interfaz de los algoritmos de la fase de selección.
 * @author pabs
 *
 */
public interface AlgoritmoSeleccion 
{
	void seleccionar(float[] aptitudes, float[] puntuacionesAcumuladas, Cromosoma[] seleccionados, int tamPoblacion, Cromosoma[] poblacion, boolean minimizacion, Random rand);
}
