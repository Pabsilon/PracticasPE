package amutacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import implementation.Cromosoma;

public class AMutacion_Insercion implements AMutacion {

	//Se elije un gen al azar y se inserta en una posición al azar, moviendo el resto acordemente.
	
	public void mutar(Cromosoma[] poblacion, float mutacionProb, Random rand) {
		
		for (int i=0; i<poblacion.length; i++){
			@SuppressWarnings("unchecked")
			int alelos[] = poblacion[i].getGenotipo();
			for (int j = 1; j<alelos.length; j++){
				if (rand.nextFloat()<mutacionProb){
					
				}
			}
			poblacion[i].setGenList(alelos);
		}
		
	}

}
