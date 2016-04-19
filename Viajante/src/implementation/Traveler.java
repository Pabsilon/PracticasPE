package implementation;

import java.util.Random;

import acruce.ACruce;
import amutacion.AMutacion;
import aseleccion.ASeleccion;

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

	
	public void ejecutarAlgoritmo(int numGeneraciones, ASeleccion asl, ACruce crx, AMutacion mut, float mutacionProb, Random rand)
	{
		float[] aptitudes = new float[_poblacion.length];
		Cromosoma[] seleccionados = new Cromosoma[_poblacion.length];
		for(int i = 0; i < numGeneraciones; i++)
		{			
			for(int j = 0; j < _poblacion.length; j++)
			{
				System.out.println(_poblacion[j].getFenotipo());
				System.out.println(_poblacion[j].getAptitud());
			}
			//Seleccionar
			asl.seleccionar(_poblacion, aptitudes, seleccionados);
			//Cruzar
			//crx.cruzar(p1, p2, h1, h2);
			//Mutar
			mut.mutar(_poblacion, mutacionProb, rand);
		}
	}
}
