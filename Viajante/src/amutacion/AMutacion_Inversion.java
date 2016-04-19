package amutacion;

import java.util.Random;

import implementation.Cromosoma;

public class AMutacion_Inversion implements AMutacion {

	// La mutaci�n por inversi�n coje dos puntos de la cadena e invierte el orden de los elementos entre esos puntos.
	// abc|def|ghi => abc|fed|ghi
	public void mutar(Cromosoma[] poblacion, float mutacionProb, Random rand) {

		
		//TODO Preguntar sobre la posibilidad de mutaci�n, si hay varias mutaciones.
		//TODO Preguntar si se trata de la inversi�n simple (invertir la cadena sobre el mismo sitio) o normal (invirtiendo la cadena
		//y metiendola en un punto aleatorio)
		
		//para toda la poblacion
		for(int i =0; i< poblacion.length; i++){
			
			int[] alelos = poblacion[i].getGenotipo();
			int inicio =1 ;
			int fin;
			
			//Buscamos el primer punto de la inversi�n
			while (rand.nextFloat() > mutacionProb && inicio<alelos.length-1){
				inicio++;
			}
			//Calculamos un segundo punto para la inversi�n.
			//TODO requiere evitar copiar madrid
			fin = (int) Math.floor((alelos.length-inicio)*rand.nextFloat());
			fin+=inicio;
			System.out.println("Inicio: "+inicio);
			System.out.println("Fin: "+fin);
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
				poblacion[i].setGenList(alelos); //TODO utilizar otro m�todo
			}
		}
		
	}

}
