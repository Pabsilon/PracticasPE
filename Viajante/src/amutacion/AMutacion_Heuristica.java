package amutacion;

import java.util.Random;

import implementation.Cromosoma;
import implementation.FAuxiliares;

/** Implementacion de la Mutación Heuristica
 * @author Pablo Mac-Veigh
 *
 */
public class AMutacion_Heuristica implements AMutacion {

	private final int _n = 3;

	/* (non-Javadoc)
	 * 
	 * Se muta la población entera. Se eligen 3 elementos aleatorios, se hacen todas las permutaciones posibles de esos
	 * tres elementos. Se evaluan los individuos generados y nos quedamos con el mejor.
	 * 
	 * @see amutacion.AMutacion#mutar(implementation.Cromosoma[], float, java.util.Random)
	 */
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
						alelos = FAuxiliares.swapElements(alelos,indexSeleccionados[1],indexSeleccionados[2]);
						prueba.setGenotipo(alelos);
						if (prueba.getAptitud()<valMejor){
							valMejor = prueba.getAptitud();
							mejor = alelos.clone();
						}
						alelos = FAuxiliares.swapElements(alelos,indexSeleccionados[0],indexSeleccionados[1]);
						prueba.setGenotipo(alelos);
						if (prueba.getAptitud()<valMejor){
							valMejor = prueba.getAptitud();
							mejor = alelos.clone();
						}
					}
					alelos = FAuxiliares.swapElements(alelos,indexSeleccionados[1],indexSeleccionados[2]);
					prueba.setGenotipo(alelos);
					if (prueba.getAptitud()<valMejor){
						valMejor = prueba.getAptitud();
						mejor = alelos.clone();
					}
				
				poblacion[i].setGenotipo(mejor);
				
			}
		}
	}
}
