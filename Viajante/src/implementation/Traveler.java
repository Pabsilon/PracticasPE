package implementation;

import java.util.Random;

import acruce.ACruce;
import acruce.ACruce_CiclosCX;
import amutacion.AMutacion_Inversion;

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
	}
	
	public void ejecutarAlgoritmo(int numGeneraciones)
	{
		//Coger el metodo de seleccion, cruce y mutacion a traves de las factorias
		for(int i = 0; i < numGeneraciones; i++)
		{
			AMutacion_Inversion mut = new AMutacion_Inversion();
			//mut.mutar(_poblacion, 1f, new Random());
			
			for(int j = 0; j < _poblacion.length; j++)
			{
				System.out.println(_poblacion[j].getFenotipo());
				System.out.println(_poblacion[j].getAptitud());
			}
			//Seleccionar
			//Cruzar
			ACruce ox = new ACruce_CiclosCX();
			
			Random rand = new Random();
			Cromosoma h1 = new Cromosoma(rand);
			Cromosoma h2 = new Cromosoma(rand);
			ox.cruzar(_poblacion[0], _poblacion[1], h1, h2);
			System.out.println(h1.getFenotipo());
			System.out.println(h2.getFenotipo());
			//Mutar
		}
	}
}
