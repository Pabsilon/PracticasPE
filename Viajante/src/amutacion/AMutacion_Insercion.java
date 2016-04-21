package amutacion;

import java.util.Random;

import implementation.Cromosoma;

public class AMutacion_Insercion implements AMutacion {

	//Se elije un gen al azar y se inserta en una posición al azar, moviendo el resto acordemente.
	
	public void mutar(Cromosoma[] poblacion, float mutacionProb, Random rand) {
		
		for (int i=0; i<poblacion.length; i++){
			int alelos[] = poblacion[i].getGenotipo();
			for (int j = 1; j<alelos.length; j++){
				if (rand.nextFloat()<mutacionProb){
					int exchange = alelos[j];
					alelos = removeElement(alelos, exchange).clone();
					alelos = insertElement(alelos, (int)(1+(alelos.length-1) * rand.nextFloat()),exchange).clone();
				}
			}
			poblacion[i].setGenotipo(alelos);
		}
		
	}
	

	private int[] insertElement(int[] array, int pos, int k) {
		final int length = array.length;
		int result[] = new int[length+1];
		if (array != null) {
			System.arraycopy(array, 0, result, 0, pos);
			result[pos]=k;
			System.arraycopy(array, pos, result, pos +1, length-pos);
		}
		return result;
	}


	public int[] removeElement(int[] array, int element) {
        if (array != null) {
            final int length = array.length;
            for (int i = 0; i < length; i++) {
                if (array[i] == element) {
                    if (length == 1) {
                        return null;
                    }
                    int[] result = new int[length-1];
                    System.arraycopy(array, 0, result, 0, i);
                    System.arraycopy(array, i + 1, result, i, length - i - 1);
                    return result;
                }
            }
        }
        return array;
    }

}
