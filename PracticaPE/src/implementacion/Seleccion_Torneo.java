package implementacion;

import java.util.Random;

/**Algoritmo de selección del Torneo.
 * @author pabs
 *
 */
public class Seleccion_Torneo implements AlgoritmoSeleccion {
	
	private int _participantes;
	
	/**Setter del número de participantes.
	 * @param n El número de participantes.
	 */
	public Seleccion_Torneo(int n){
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
			
			float maxVal = Float.MIN_VALUE;
			int max = 0;
			for(int j = 0; j < _participantes; j++)
			{
				if(elegidosValor[j] > maxVal)
				{
					maxVal = elegidosValor[j];
					max = j;
				}
			}
			
			seleccionados[i].copiarCromosoma(poblacion[elegidosIndice[max]]);
		}

	}

}
