package aseleccion;

import java.util.Random;

import implementacion.Hormiga;

/** Implementacion de la seleccion de torneo
 * @author Jorge Sanchez
 *
 */
public class ASeleccion_Torneo implements ASeleccion {
	
	private int _participantes; //Numero de participantes en el torneo
	
	public ASeleccion_Torneo(int participantes) {
		_participantes  = participantes;
	}

	@Override
	public void seleccionar(Hormiga[] poblacion, int[] aptitudes, Hormiga[] seleccionados)
	{
		//Coger _participantes elementos al azar
		float elegidosValor[] = new float[_participantes];
		int elegidosIndice[] = new int[_participantes];
		
		Random rand = new Random();
		for(int i = 0; i < poblacion.length; i++)
		{			
			for (int j = 0; j < _participantes; j++){
				int randIndex = rand.nextInt(poblacion.length);
				elegidosValor[j] = aptitudes[randIndex];
				elegidosIndice[j] = randIndex;
			}
			
			float maxVal = Integer.MIN_VALUE;
			int max = 0;
			for(int j = 0; j < _participantes; j++)
			{
				if(elegidosValor[j] > maxVal)
				{
					maxVal = elegidosValor[j];
					max = j;
				}
			}
			
			seleccionados[i] = poblacion[elegidosIndice[max]].crearCopia();
		}


	}

}
