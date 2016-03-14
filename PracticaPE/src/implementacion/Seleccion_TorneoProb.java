package implementacion;

import java.util.Random;

/**Algoritmo de selección del Torneo Probabilistico.
 * @author pabs
 *
 */
public class Seleccion_TorneoProb implements AlgoritmoSeleccion{

	private int _participantes;
	private static final float _probabilidadMejor = 0.7f; //0 - 1 Probabilidad de que el mejor sea elegido (sino, el pero sera elegido)
	
	/**Setter del número de participantes.
	 * @param n el número de participantes.
	 */
	public Seleccion_TorneoProb(int n){
		_participantes = n;
	}

	public void seleccionar(float[] aptitudes, float[] puntuacionesAcumuladas, Cromosoma[] seleccionados, int tamPoblacion, Cromosoma[] poblacion, boolean maximizacion, Random rand)
	{
		//Coger tres elementos al hazar
		float elegidosValor[] = new float[_participantes];
		int elegidosIndice[] = new int[_participantes];
		
		
		
		for(int i = 0; i < tamPoblacion; i++)
		{			
			for (int j = 0; j<_participantes; j++){
				int randIndex = rand.nextInt(tamPoblacion);
				elegidosValor[j] = aptitudes[randIndex];
				elegidosIndice[j] = randIndex;
			}
			
			boolean elegirMejor = rand.nextFloat() < _probabilidadMejor; //True si se elegie al mejor de la eleccion
			//Seleccionar el mejor y el peor
			float maxVal = Integer.MIN_VALUE;
			float minVal = Float.MAX_VALUE;
			int max = 0, min = 1;
			for(int j = 0; j < _participantes; j++)
			{
				if(elegidosValor[j] > maxVal)
				{
					maxVal = elegidosValor[j];
					max = j;
				}
				else if(elegidosValor[j] < minVal)
				{
					minVal = elegidosValor[j];
					min = j;
				}
			}
			
			//Elegir el correspondiente dependiendo del resultado de la probabilidad.
			if(elegirMejor)
			{
				seleccionados[i].copiarCromosoma(poblacion[elegidosIndice[max]]);
			}
			else
			{
				seleccionados[i].copiarCromosoma(poblacion[elegidosIndice[min]]); //Elegir el peor
			}
		}

	}
	
}
