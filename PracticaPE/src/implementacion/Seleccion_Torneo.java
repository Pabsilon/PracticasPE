package implementacion;

import java.util.Random;

public class Seleccion_Torneo implements AlgoritmoSeleccion {

	@Override
	public void seleccionar(float[] aptitudes, float[] puntuacionesAcumuladas, Cromosoma[] seleccionados, int tamPoblacion, Cromosoma[] poblacion, boolean minimizacion, Random rand)
	{
		//Coger tres elementos al hazar
		float elegidos[] = new float[3];
		
		
		for(int i = 0; i < tamPoblacion; i++)
		{
			elegidos[0] = aptitudes[rand.nextInt(tamPoblacion)]; //Generar numero aleatorio [0, tamPoblacion) y coger ese valor
			elegidos[1] = aptitudes[rand.nextInt(tamPoblacion)];
			elegidos[2] = aptitudes[rand.nextInt(tamPoblacion)];
			
			//Si el problema es de minimizacion, coger el mas peque�o
			if(minimizacion)
			{
				float minVal = Float.MAX_VALUE;
				int min = 0;
				for(int j = 0; j < 3; j++)
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
				for(int j = 0; j < 3; j++)
				{
					if(elegidos[j] < maxVal)
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
