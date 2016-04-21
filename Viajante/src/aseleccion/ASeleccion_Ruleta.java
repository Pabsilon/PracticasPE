package aseleccion;

import java.util.Random;

import implementation.Cromosoma;

/** Implementacion de la seleccion por ruleta
 * @author Jorge Sanchez
 *
 */
public class ASeleccion_Ruleta implements ASeleccion
{
	public void seleccionar(Cromosoma[] poblacion, int[] aptitudes, Cromosoma[] seleccionados)
	{
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
		
		
		//Seleccionar
		Random rand = new Random();
		for(int i = 0; i < poblacion.length; i++)
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
