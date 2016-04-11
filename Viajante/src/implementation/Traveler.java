package implementation;

import java.util.Random;

//Distancia optima dada por el profesor: 5298
public class Traveler 
{
	Cromosoma _poblacion[];
	public Traveler(int tamanoPoblacion)
	{
		Random rand = new Random();
		//Generar poblacion
		_poblacion = new Cromosoma[tamanoPoblacion];
		for(int i = 0; i < tamanoPoblacion; i++)
		{
			_poblacion[i] = new Cromosoma(rand);
		}
		
		for(int i = 0; i < tamanoPoblacion; i++)
		{
			System.out.println(_poblacion[i].getFenotipo());
		}
	}
	
	public void ejecutarAlgoritmo(int numGeneraciones)
	{
		for(int i = 0; i < numGeneraciones; i++)
		{
			//Seleccionar
			//Cruzar
			//Mutar
		}
	}
}
