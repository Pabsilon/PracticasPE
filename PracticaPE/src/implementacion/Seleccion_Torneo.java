package implementacion;

import java.util.Random;

public class Seleccion_Torneo implements AlgoritmoSeleccion {
	
	private int _participantes;
	
	public Seleccion_Torneo(int n){
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
			
			//Si el problema es de minimizacion, coger el mas peque�o
			if(!maximizacion)
			{
				float minVal = Float.MAX_VALUE;
				int min = 0;
				for(int j = 0; j < _participantes; j++)
				{
					if(elegidos[j] < minVal)
					{
						minVal = elegidos[j];
						min = j;
					}
				}
				
				seleccionados[i] = poblacion[min];
			}
			else
			{
				float maxVal = Float.MIN_VALUE;
				int max = 0;
				for(int j = 0; j < _participantes; j++)
				{
					if(elegidos[j] > maxVal)
					{
						maxVal = elegidos[j];
						max = j;
					}
				}
				
				seleccionados[i] = poblacion[max];
			}
		}

	}

}
