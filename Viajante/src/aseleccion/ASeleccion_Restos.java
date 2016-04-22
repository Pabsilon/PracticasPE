package aseleccion;

import java.util.Random;

import implementation.Cromosoma;

/** Implementacion de la seleccion por restos.
 * @author Pablo Mac-Veigh
 *
 */
public class ASeleccion_Restos implements ASeleccion {

	
	//Ejemplo en la página 35 del libro.
	
	//Se elije un valor K. Si Pi * K > 1 se añaden tantas copias como el resto entero del número.
	// Ejemplo: Individuo con Pi = 0.3, k = 7: Pi * K = 2.1; Se añaden 2 copias.
	// El resto se rellena con otro método, supondremos ruleta o torneo.
	@Override
	public void seleccionar(Cromosoma[] poblacion, int[] aptitudes, Cromosoma[] seleccionados) 
	{
		
		int K = poblacion.length;
		
		//Generar puntuaciones acumuladas
		float puntuaciones[] = new float[poblacion.length];
		float puntuacionesAcum[] = new float[poblacion.length];
		float sumaAptitudes = 0;
		for(int i = 0; i < poblacion.length; i++)
		{
			sumaAptitudes += aptitudes[i];
		}
		puntuaciones[0] = aptitudes[0] / sumaAptitudes;
		puntuacionesAcum[0] = puntuaciones[0];
		for(int i = 1; i < poblacion.length; i++)
		{
			puntuaciones[i] = aptitudes[i] / sumaAptitudes;
			puntuacionesAcum[i] = puntuaciones[i] + puntuacionesAcum[i - 1];
		}
		puntuacionesAcum[poblacion.length - 1] = 1; //Asegurarse que el ultimo valor de las puntuaciones es 1
		
		//Aplicar algoritmo de restos
		int pobRellena = 0;		
		for (int i = 0; i < poblacion.length; i++){
			for (int j=0; j < (int)puntuaciones[i] * K; j++){
				seleccionados[pobRellena].copiarCromosoma(poblacion[i]);
				pobRellena++;
			}
		}
		
		//Seleccionar el resto de elementos por el metodo de la ruleta
		Random rand = new Random();
		for(int i = pobRellena; i < poblacion.length; i++)
		{
			//Generar numero entre 0 - 1
			float prob = rand.nextFloat();
			//Buscar el elemento seleccionado (Nota: seleccionado nunca sera mayor que el tamano de la poblacion)
			int seleccionado = 0;
			while(prob > puntuacionesAcum[seleccionado])
			{
				seleccionado++;
			}
			
			seleccionados[i].copiarCromosoma(poblacion[seleccionado]);
		}
		
		
	}

}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             