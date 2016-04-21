package amutacion;

import java.util.Random;

import implementation.Cromosoma;

public class AMutacion_Heuristica implements AMutacion {

	private final int _n = 3;

	public void mutar(Cromosoma[] poblacion, float mutacionProb, Random rand) {
		int[] indexSeleccionados = new int[_n];
		
		for (int i = 0; i<poblacion.length; i++){
			if (rand.nextFloat()<mutacionProb){
				int valMejor = poblacion[i].getAptitud();
				Cromosoma prueba = new Cromosoma();
				int[] alelos = poblacion[i].getGenotipo();
				int[] mejor = alelos.clone();
				//elegimos n elementos menos madrid
				for (int j = 0; j<_n; j++){
					indexSeleccionados[j] = (int) Math.floor((alelos.length-1)*rand.nextFloat()+1);
				}
				//intercambiamos los n alelos en todas las permutaciones y nos quedamos con la mejor.
				//De momento está implementado para 3.
				for (int j = 0; j<2; j++){
					alelos = swapElements(alelos,indexSeleccionados[1],indexSeleccionados[2]);
					prueba.setGenotipo(alelos);
					if (prueba.getAptitud()<valMejor){
						valMejor = prueba.getAptitud();
						mejor = alelos.clone();
					}
					alelos = swapElements(alelos,indexSeleccionados[0],indexSeleccionados[1]);
					prueba.setGenotipo(alelos);
					if (prueba.getAptitud()<valMejor){
						valMejor = prueba.getAptitud();
						mejor = alelos.clone();
					}
				}
				alelos = swapElements(alelos,indexSeleccionados[1],indexSeleccionados[2]);
				prueba.setGenotipo(alelos);
				if (prueba.getAptitud()<valMejor){
					valMejor = prueba.getAptitud();
					mejor = alelos.clone();
				}
				
				poblacion[i].setGenotipo(mejor);
				
			}
		}
	}
	
	private int[] swapElements(int[] array, int posA, int posB){
		int aux = array[posA];
		array[posA]=array[posB];
		array[posB]=aux;
		return array;
	}
}
