package amutacion;

import java.util.Random;

import implementation.Cromosoma;

public class AMutacion_Intercambio implements AMutacion {

	@Override
	public void mutar(Cromosoma[] poblacion, float mutacionProb, Random rand) {
		
		for (int i=0; i<poblacion.length; i++){
			int alelos[] = poblacion[i].getGenotipo();
			for (int j = 1; j<alelos.length; j++){
				if (rand.nextFloat()<mutacionProb){
					int exchange = (int) (alelos.length * rand.nextFloat());
					int aux = alelos[j];
					alelos[j]=alelos[exchange];
					alelos[exchange]=aux;
					poblacion[i].setGenotipo(alelos); //TODO utilizar otro m�todo
				}
			}
		}
	}

}
