package aseleccion;

import java.util.Comparator;
import java.util.PriorityQueue;

import implementation.Cromosoma;
import javafx.util.Pair;

public class Aseleccion_Truncamiento implements ASeleccion {
	
	private final int _porcentaje = 25;

	//El algoritmo de selección por truncamiento elige el top % de una población y llena 
	//la nueva población con esos individuos
	
	public void seleccionar(Cromosoma[] poblacion, float[] aptitudes, Cromosoma[] seleccionados) {
		
		int aCopiar = poblacion.length*(_porcentaje/100);
		int numCopias = poblacion.length / aCopiar;
		Comparator<javafx.util.Pair<Float, Integer>> comparador; //Para arreglar el fallo de JavaFX:
		//JRE System Libraries -> Click Derecho -> Propiedades, Cambiar a "Workspace Default" (3era opción)
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
		{
			int i = 0;
			while(!monticuloMaximos.isEmpty())
			{
				indicesOrdenador[i] = monticuloMaximos.poll().getValue();
				i++;
			}
		}
		for (int i=0; i<seleccionados.length; i++){ //Para cada seleccionado
			for (int j=0; j<aCopiar; j++){ //Elemento a copiar
				for (int k=0; k<numCopias; k++){ //Numero de copias
					seleccionados[i].copiarCromosoma(poblacion[indicesOrdenador[j]]);
				}
			}
		}

	}

}
