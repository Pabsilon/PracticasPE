package implementation;

import java.util.Random;

import amutacion.AMutacion_Insercion;
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
		
		for(int i = 0; i < tamanoPoblacion; i++)
		{
			System.out.println(_poblacion[i].getFenotipo());
			System.out.println(_poblacion[i].getAptitud());
		}
	}
	
	public void ejecutarAlgoritmo(int numGeneraciones)
	{
		//Coger el metodo de seleccion, cruce y mutacion a traves de las factorias
		for(int i = 0; i < numGeneraciones; i++)
		{
			AMutacion_Insercion mut = new AMutacion_Insercion();
			mut.mutar(_poblacion, 0.05f, new Random());
			
			for(int j = 0; j < _poblacion.length; j++)
			{
				System.out.println(_poblacion[j].getFenotipo());
				System.out.println(_poblacion[j].getAptitud());
			}
			//Seleccionar
			//Cruzar
			//Mutar
		}
	}
}
