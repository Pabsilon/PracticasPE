package implementacion;

import aseleccion.ASeleccion;
import aseleccion.ASeleccion_Ranking;
import aseleccion.ASeleccion_Restos;
import aseleccion.ASeleccion_Ruleta;
import aseleccion.ASeleccion_Torneo;
import aseleccion.Aseleccion_Truncamiento;

/**Implementacion de la fabrica de algoritmos de seleccion
 * @author Pablo Mac-Veigh
 *
 */
public class Fabrica_ASeleccion 
{
	/**Fabrica de algoritmos de seleccion
	 * @param algoritmo El nombre del algoritmo
	 * @param n El numero de participantes ( en caso de torneo, sino, es irrelevante)
	 * @return El objeto inicializado
	 */
	public static ASeleccion generarAlgoritmoSeleccion(String algoritmo, int n)
	{
		//{"Ruleta", "Torneo", "Ranking", "Restos", "Truncamiento"};
		switch(algoritmo)
		{
		case "Ruleta":
			return new ASeleccion_Ruleta();
		case "Torneo":
			return new ASeleccion_Torneo(n);
		case "Ranking":
			return new ASeleccion_Ranking();
		case "Restos":
			return new ASeleccion_Restos();
		case "Truncamiento":
			return new Aseleccion_Truncamiento();
		default:
			return null;
		}
	}
}
