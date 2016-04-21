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
				inicio = (int) Math.floor((alelos.length-1)*rand.nextFloat());
				
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
					//TODO
					
					
					
					System.arraycopy(copia, 0, alelos, inicio, fin-inicio+1);
					
					
					
					poblacion[i].setGenotipo(alelos); //TODO utilizar otro m�todo
				}
			}
		}
	}

}
