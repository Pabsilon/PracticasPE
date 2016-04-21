package amutacion;

import java.util.Random;

import implementation.Cromosoma;
import implementation.FAuxiliares;

/** Implementacion del algorimo propio de mutacion.
 *  Es una mezcla entre inversion e insercion
 * @author Pablo Mac-Veigh
 *
 */
public class AMutacion_Propio implements AMutacion {
	/* (non-Javadoc)
	 * 
	 * Elegimos dos puntos, invertimos los valores entre esos dos puntos y los insertamos en una poscion aleatoria de los genes.
	 * 
	 * @see amutacion.AMutacion#mutar(implementation.Cromosoma[], float, java.util.Random)
	 */
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
						aux = FAuxiliares.removeElement(aux, copia[j]);
					}
					for (int j = pInsercion; j<pInsercion + copia.length; j++){
						aux = FAuxiliares.insertElement(aux, j, copia[j-pInsercion]);
					}
					
					
					
					poblacion[i].setGenotipo(aux);
				}
			}
		}
	}
}
