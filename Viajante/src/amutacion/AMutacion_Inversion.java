package amutacion;

import java.util.Random;

import implementation.Cromosoma;

/** Implementacion de la mutacion por inversión.
 * @author Pablo Mac-Veigh
 *
 */
public class AMutacion_Inversion implements AMutacion {

	
	/* (non-Javadoc)
	 * 
	 * Se eligen dos puntos de la cadena de genes, y se invierten estos.
	 * 
	 * @see amutacion.AMutacion#mutar(implementation.Cromosoma[], float, java.util.Random)
	 */
	public void mutar(Cromosoma[] poblacion, float mutacionProb, Random rand) {
		//para toda la poblacion
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
					
					//La devolvemos a su sitio.
					System.arraycopy(copia, 0, alelos, inicio, fin-inicio+1);
					poblacion[i].setGenotipo(alelos);
				}
			}
		}
		
	}

}
