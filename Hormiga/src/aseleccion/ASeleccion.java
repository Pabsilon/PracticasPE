package aseleccion;

import implementacion.Hormiga;

public interface ASeleccion
{
	/** Selecciona individuos de una poblacion. Crea los elementos que se almacenaran en seleccionados
	 * 
	 * Algoritmos disponibles: Ranking, Restos, Ruleta, Torneo, Truncamiento.
	 * 
	 * @param poblacion
	 * @param aptitudes
	 * @param seleccionados
	 */
	public void seleccionar(Hormiga[] poblacion, int[] aptitudes, Hormiga[] seleccionados);
}
