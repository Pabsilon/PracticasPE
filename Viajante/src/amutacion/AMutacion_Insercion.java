package amutacion;

import java.util.Random;

import implementation.Cromosoma;
import implementation.FAuxiliares;

/** Implementacion de la mutacion por Insercion
 * @author Pablo Mac-veigh
 *
 */
public class AMutacion_Insercion implements AMutacion {

	
	/* (non-Javadoc)
	 * 
	 * //Se elije un gen al azar y se inserta en una posicion al azar, moviendo el resto acordemente.
	 * 
	 * @see amutacion.AMutacion#mutar(implementation.Cromosoma[], float, java.util.Random)
	 */
	public void mutar(Cromosoma[] poblacion, float mutacionProb, Random rand) {
		
		for (int i=0; i<poblacion.length; i++){
			//Para toda la poblacion
			int alelos[] = poblacion[i].getGenotipo();
			for (int j = 1; j<alelos.length; j++){
				//Para cada alelo, si toca mutar
				if (rand.nextFloat()<mutacionProb){
					int exchange = alelos[j];
					//Quitamos el emento de  la lista
					alelos = FAuxiliares.removeElement(alelos, exchange).clone();
					//Lo agregamos.
					alelos = FAuxiliares.insertElement(alelos, (int)(1+(alelos.length-1) * rand.nextFloat()),exchange).clone();
				}
			}
			poblacion[i].setGenotipo(alelos);
		}
		
	}

}
