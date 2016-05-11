package aseleccion;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

import implementacion.Hormiga;
import javafx.util.Pair;

public class ASeleccion_Ranking implements ASeleccion {

	private final float _beta = 1.5f;
	
	@Override
	public void seleccionar(Hormiga[] poblacion, int[] aptitudes, Hormiga[] seleccionados) 
	{
		//Ordenar los elementos de mejor a peor aptitud (Heap de minimos)
		Comparator<javafx.util.Pair<Integer, Integer>> comparador; //Para arreglar el fallo de JavaFX:
		//JRE System Libraries -> Click Derecho -> Propiedades, Cambiar a "Workspace Default" (3era opción)
		comparador = new Comparator<javafx.util.Pair<Integer, Integer>>()
		{

			@Override
			public int compare(Pair<Integer, Integer> o1,
					Pair<Integer, Integer> o2) {
				if(o1.getKey() > o2.getKey()) return -1;
				if(o1.getKey() == o2.getKey()) return 0;
				if(o1.getKey() < o2.getKey()) return 1;
				return 0;
			}
		};
		
		PriorityQueue<javafx.util.Pair<Integer, Integer>> monticuloMaximos = new PriorityQueue<javafx.util.Pair<Integer, Integer>>(comparador);
		for(int i = 0; i < aptitudes.length; i++)
		{
			monticuloMaximos.add(new javafx.util.Pair<Integer, Integer>(aptitudes[i], i));
		}
		
		//Obtener indices ordenados
		int[] indicesOrdenador = new int[poblacion.length];
		{
			int i = 0;
			while(!monticuloMaximos.isEmpty())
			{
				indicesOrdenador[i] = monticuloMaximos.poll().getValue();
				i++;
			}
		}
		
		//Generar probabilidades de seleccion
		float[] probabilidades = new float[poblacion.length];
		for(int i = 0; i < probabilidades.length; i++)
		{
			probabilidades[i] = (1.0f / probabilidades.length) * (_beta - 2 * (_beta - 1) * ((i - 1.0f)/probabilidades.length - 1));
		}
		
		Random rand = new Random();
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
			
			seleccionados[i] = poblacion[indicesOrdenador[j]].crearCopia();
		}

	}

}
