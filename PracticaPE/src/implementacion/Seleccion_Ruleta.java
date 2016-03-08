package implementacion;

import java.util.Random;

//Algoritmo de seleccion: Ruleta
public class Seleccion_Ruleta implements AlgoritmoSeleccion {

	@Override
	public void seleccionar(float[] aptitudes, float[] puntuacionesAcumuladas, Cromosoma[] seleccionados, int tamPoblacion, Cromosoma[] poblacion, boolean minimizacion, Random rand)
	{
		for(int i = 0; i < tamPoblacion; i++)
		{
			//Generar numero entre 0 - 1
			float prob = rand.nextFloat();
			//Buscar el elemento seleccionado (Nota: seleccionado nunca sera mayor que el tama�o de la poblacion)
			int seleccionado = 0;
			while(prob > puntuacionesAcumuladas[seleccionado])
			{
				seleccionado++;
			}
			
			seleccionados[i].copiarCromosoma(poblacion[seleccionado]);
		}
	}

}
