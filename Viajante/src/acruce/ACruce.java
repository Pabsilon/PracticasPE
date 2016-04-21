package acruce;

import implementation.Cromosoma;

/** Interfaz de los algoritmos de cruce.
 * @author Pabs
 *
 */
public interface ACruce
{
	
	/** Cruza dos padres; p1 y p2; y genera dos hijos: h1, h2.
	 * Cruces disponibles: CiclosCX, CodOrdinal, OX, PMX, Propio, RecRutasERX, VariantesOX
	 * @param p1 El primer padre
	 * @param p2 El segundo padre
	 * @param h1 El primer hijo generado
	 * @param h2 El segundo hijo generado
	 */
	public void cruzar(Cromosoma p1, Cromosoma p2, Cromosoma h1, Cromosoma h2);
}
