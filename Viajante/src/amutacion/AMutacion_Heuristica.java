package amutacion;

import java.util.Random;

import implementation.Cromosoma;

public class AMutacion_Heuristica implements AMutacion {

	private int[] _localbest;
	private final int _n = 3;
	@Override
	public void mutar(Cromosoma[] poblacion, float mutacionProb, Random rand) {
		int[] indexSeleccionados = new int[_n];
		int[] seleccionados = new int[_n];
		
		for (int i = 0; i<poblacion.length; i++){
			if (rand.nextFloat()<mutacionProb){
				int[] alelos = poblacion[i].getGenotipo();
				//elegimos n elementos
				for (int j = 0; j<_n; j++){
					indexSeleccionados[j] = (int) Math.floor((alelos.length-1)*rand.nextFloat());
					seleccionados[j] = alelos[indexSeleccionados[j]];
				}
				//intercambiamos los n alelos en todas las permutaciones y nos quedamos con la mejor.
				
				
			}
		}
	}
}
