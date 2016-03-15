package implementacion;

/**Fábrica de algoritmos de selección.
 * @author pabs
 *
 */
public class AlgoritmoSeleccionFabrica 
{
	/**Devuelve el algoritmo de selección pedido.
	 * @param algoritmo El nombre del algoritmo (Ruleta, Torneo, Torneo_Probabilistico).
	 * @param participantes El número de participantes, en caso de torneo.
	 * @return El algoritmo de selección inicializado.
	 */
	static AlgoritmoSeleccion getAlgoritmoDeSeleccion(String algoritmo, int participantes)
	{
		//Devuelve el algoritmo solicitado
		switch(algoritmo)
		{
		case "Ruleta":
			return new Seleccion_Ruleta();
		case "Torneo":
			return new Seleccion_Torneo(participantes);
		case "Torneo_Probabilistico":
			return new Seleccion_TorneoProb(participantes);
		case "Ranking":
			return new Seleccion_Ranking();
		default:
			return new Seleccion_Ruleta();
		}
	}
}
