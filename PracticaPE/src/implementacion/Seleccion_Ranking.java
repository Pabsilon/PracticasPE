package implementacion;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

import javafx.util.Pair;

//Se basa en el ranking segun la ordenacion de los individuos por fitness decreciente.
/**Algoritmo de selección de Ranking.
 * @author pabs
 *
 */
public class Seleccion_Ranking implements AlgoritmoSeleccion {

	private final float _beta = 1.5f;
		
	@Override
	public void seleccionar(float[] aptitudes, float[] puntuacionesAcumuladas,
			Cromosoma[] seleccionados, int tamPoblacion, Cromosoma[] poblacion,
			boolean minimizacion, Random rand) {
		
		
		//Ordenar los elementos de mejor a peor
		Comparator<javafx.util.Pair<Float, Integer>> comparador;
		comparador = new Comparator<javafx.util.Pair<Float, Integer>>()
		{

			@Override
			public int compare(Pair<Float, Integer> o1,
					Pair<Float, Integer> o2) {
				if(o1.getKey() > o2.getKey()) return -1;
				if(o1.getKey() == o2.getKey()) return 0;
				if(o1.getKey() < o2.getKey()) return 1;
				return 0;
			}
		};
		
		PriorityQueue<javafx.util.Pair<Float, Integer>> monticuloMaximos = new PriorityQueue<javafx.util.Pair<Float, Integer>>(comparador);
		for(int i = 0; i < aptitudes.length; i++)
		{
			monticuloMaximos.add(new javafx.util.Pair<Float, Integer>(aptitudes[i], i));
		}
		
		//Obtener indices ordenados
		int[] indicesOrdenador = new int[poblacion.length];
		{//Bloque para no tener conflictos con i :D
			int i = 0;
			while(!monticuloMaximos.isEmpty())
			{
				indicesOrdenador[i] = monticuloMaximos.poll().getValue();
				i++;
			}
		}
		
		//Seleccionarlos
		float[] probabilidades = new float[poblacion.length];
		for(int i = 0; i < probabilidades.length; i++)
		{
			probabilidades[i] = (1.0f / probabilidades.length) * (_beta - 2 * (_beta - 1) * ((i - 1.0f)/probabilidades.length - 1));
		}
		
		//Seleccionar elementos
		for(int i = 0; i < seleccionados.length; i++)
		{
			float randomNum = rand.nextFloat();
			
			//Escoger el elemento seleccionado
			int j = 1;
			float sumProb = probabilidades[0];
			while((j < probabilidades.length - 1) && (randomNum > sumProb))
			{
				sumProb += probabilidades[j];
				j++;
			}
			
			seleccionados[i].copiarCromosoma(poblacion[indicesOrdenador[j]]);
		}
		
	}

}
