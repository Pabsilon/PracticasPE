package amutacion;

import java.util.Random;

import implementation.Cromosoma;

public class AMutacion_Propio implements AMutacion {
	
	
	//Se trata de una mutacion que mezcla inversión e inserción.
	@Override
	public void mutar(Cromosoma[] poblacion, float mutacionProb, Random rand) {
		for(int i =0; i< poblacion.length; i++){
			
			if (rand.nextFloat()<mutacionProb){
				int[] alelos = poblacion[i].getGenotipo();
				int inicio =1 ;
				int fin;
				
				//Buscamos el primer punto de la inversion				
				inicio = (int) Math.floor((alelos.length-1)*rand.nextFloat()+1);
				
				//Calculamos un segundo punto para la inversion.
				fin = (int) Math.floor((alelos.length-inicio)*rand.nextFloat());
				fin+=inicio;
				
				if (fin!=0){
					int[] copia = new int[fin-inicio+1];
					System.arraycopy(alelos, inicio, copia, 0, fin-inicio+1);
					//Invertimos el array
					for(int j = 0; j < copia.length / 2; j++)
					{
					    int temp = copia[j];
					    copia[j] = copia[copia.length - j - 1];
					    copia[copia.length - j - 1] = temp;
					}
					
					//La devolvemos a un sitio aleatorio
					//Punto Aleatorio:
					int pInsercion = (int) Math.floor((alelos.length-copia.length-1)*rand.nextFloat()+1);
					int[] aux = alelos.clone();
					for (int j = 0; j<copia.length; j++){
						aux = removeElement(aux, copia[j]);
					}
					for (int j = pInsercion; j<pInsercion + copia.length; j++){
						aux = insertElement(aux, j, copia[j-pInsercion]);
					}
					
					
					
					poblacion[i].setGenotipo(aux);
				}
			}
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
	
	private int[] removeElement(int[] array, int element) {
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
