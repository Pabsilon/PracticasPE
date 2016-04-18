package amutacion;

import java.util.Random;

import implementation.Cromosoma;

public class AMutacion_Inversion implements AMutacion {

	// La mutación por inversión coje dos puntos de la cadena e invierte el orden de los elementos entre esos puntos.
	// abc|def|ghi => abc|fed|ghi
	public void mutar(Cromosoma[] poblacion, float mutacionProb, Random rand) {

		
		//TODO Preguntar sobre la posibilidad de mutación, si hay varias mutaciones.
		//TODO Preguntar si se trata de la inversión simple (invertir la cadena sobre el mismo sitio) o normal (invirtiendo la cadena
		//y metiendola en un punto aleatorio)
		
		//para toda la poblacion
		for(int i =0; i< poblacion.length; i++){
			
			int[] alelos = poblacion[i].getGenotipo();
			int inicio = 0;
			int fin;
			
			//Buscamos el primer punto de la inversión
			while (rand.nextFloat()<mutacionProb && inicio<alelos.length){
				inicio++;
			}
			
			//Buscamos el segundo punto de la inversión.
			fin = inicio;
			while (rand.nextFloat()<mutacionProb && fin <alelos.length){
				fin++;
			}
			
		}
		
	}

}
