package implementacion;

import java.util.Random;

public class Seleccion_TorneoProb implements AlgoritmoSeleccion{

	private int _participantes;
	private static final float _probabilidadMejor = 0.7f; //0 - 1 Probabilidad de que el mejor sea elegido (sino, el pero sera elegido)
	
	public Seleccion_TorneoProb(int n){
		_participantes = n;
	}

	@Override
	public void seleccionar(float[] aptitudes, float[] puntuacionesAcumuladas, Cromosoma[] seleccionados, int tamPoblacion, Cromosoma[] poblacion, boolean maximizacion, Random rand)
	{
		//Coger tres elementos al hazar
		float elegidos[] = new float[_participantes];
		
		
		for(int i = 0; i < tamPoblacion; i++)
		{			
			for (int j = 0; j<_participantes; j++){
				elegidos[j] = aptitudes[rand.nextInt(tamPoblacion)];
			}
			
			boolean elegirMejor = rand.nextFloat() < _probabilidadMejor; //True si se elegie al mejor de la eleccion
			if(!maximizacion) //Minimizacion
			{
				//Seleccionar el mejor y el peor
				float minVal = Float.MAX_VALUE;
				float maxVal = Float.MIN_VALUE;
				int min = 0, max = 1;
				for(int j = 0; j < _participantes; j++)
				{
					if(elegidos[j] < minVal)
					{
						minVal = elegidos[j];
						min = j;
					}
					else if(elegidos[j] > maxVal)
					{
						maxVal = elegidos[j];
						max = j;
					}
				}
				
				//Elegir el correspondiente dependiendo del resultado de la probabilidad.
				if(elegirMejor)
				{
					seleccionados[i].copiarCromosoma(poblacion[min]);
				}
				else
				{
					seleccionados[i].copiarCromosoma(poblacion[max]); //Elegir el peor
				}
			}
			else
			{
				//Seleccionar el mejor y el peor
				float maxVal = Float.MIN_VALUE;
				float minVal = Float.MAX_VALUE;
				int max = 0, min = 1;
				for(int j = 0; j < _participantes; j++)
				{
					if(elegidos[j] > maxVal)
					{
						maxVal = elegidos[j];
						max = j;
					}
					else if(elegidos[j] < minVal)
					{
						minVal = elegidos[j];
						min = j;
					}
				}
				
				//Elegir el correspondiente dependiendo del resultado de la probabilidad.
				if(elegirMejor)
				{
					seleccionados[i].copiarCromosoma(poblacion[max]);
				}
				else
				{
					seleccionados[i].copiarCromosoma(poblacion[min]); //Elegir el peor
				}
			}
		}

	}
	
}
