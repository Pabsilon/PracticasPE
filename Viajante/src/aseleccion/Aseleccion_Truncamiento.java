package aseleccion;

import java.util.Comparator;
import java.util.PriorityQueue;

import implementation.Cromosoma;
import javafx.util.Pair;

public class Aseleccion_Truncamiento implements ASeleccion {
	
	private final float _porcentaje = 0.25f;

	//El algoritmo de selección por truncamiento elige el top % de una población y llena 
	//la nueva población con esos individuos
	
	public void seleccionar(Cromosoma[] poblacion, int[] aptitudes, Cromosoma[] seleccionados) {
		
		int aCopiar = (int)(poblacion.length * _porcentaje);
		int numCopias = poblacion.length / aCopiar;
		Comparator<javafx.util.Pair<Integer, Integer>> comparador;
		//Para arreglar el fallo de JavaFX:
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
		
		//Seleccionar elementos
		int k = 0;
		for (int i=0; i < aCopiar; i++){ //Para cada seleccionado
			for (int j=0; j < numCopias; j++){ //Elemento a copiar
					seleccionados[k].copiarCromosoma(poblacion[indicesOrdenador[i]]);
					k++;
			}
		}

	}

}
