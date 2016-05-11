package aseleccion;

import implementacion.Hormiga;

//http://www.dca.fee.unicamp.br/~gomide/courses/EA072/artigos/Genetic_Algorithm_TSPR_eview_Larranaga_1999.pdf
//Trabajo que contiene bastante de lo que hacemos.

/** Interfaz que implementa los algoritmos de seleccion
 * @author Pablo Mac-Veigh
 *
 */
public interface ASeleccion
{
	/** Selecciona individuos de una poblacion.
	 * 
	 * Algoritmos disponibles: Ranking, Restos, Ruleta, Torneo, Truncamiento.
	 * 
	 * @param poblacion
	 * @param aptitudes
	 * @param seleccionados
	 */
	public void seleccionar(Hormiga[] poblacion, int[] aptitudes, Hormiga[] seleccionados);
}
