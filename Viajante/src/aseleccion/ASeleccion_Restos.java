package aseleccion;

import java.util.Random;

import implementation.Cromosoma;

public class ASeleccion_Restos implements ASeleccion {

	
	//Ejemplo en la p�gina 35 del libro.
	
	//Se elije un valor K. Si Pi * K > 1 se a�aden tantas copias como el resto entero del n�mero.
	// Ejemplo: Individuo con Pi = 0.3, k = 7: Pi * K = 2.1; Se a�aden 2 copias.
	// El resto se rellena con otro m�todo, supondremos ruleta o torneo.
	@Override
	public void seleccionar(Cromosoma[] poblacion, float[] aptitudes, Cromosoma[] seleccionados) {
		
		//Hay que preguntar sobre K, si un valor arbitrrario, si se pide, etc.
		int K = 10;
		
		//Generar puntuaciones acumuladas
		float puntuaciones[] = new float[poblacion.length];
		float puntuacionesAcum[] = new float[poblacion.length];
		int sumaAptitudes = 0;
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
		
		int pobRellena = 0;
		
		for (int i = 0; i<=puntuacionesAcum.length; i++){
			for (int j=0; j<Math.floor(puntuacionesAcum[i] * K); j++){
				seleccionados[pobRellena].copiarCromosoma(poblacion[i]);
				pobRellena++;
			}
		}
		
		//Seleccionar el resto por ruleta
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
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             