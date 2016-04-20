package implementation;

import java.util.Random;

import acruce.ACruce;
import amutacion.AMutacion;
import aseleccion.ASeleccion;

//Distancia optima dada por el profesor: 5298
public class Traveler 
{
	Cromosoma _poblacion[];
	double[] _mejorAbsoluto;
	double[] _mejorGeneracion;
	double[] _mediaGeneracion;
	
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

	
	public void ejecutarAlgoritmo(int numGeneraciones, ASeleccion asl, ACruce crx, AMutacion mut, float mutacionProb, Random rand)
	{
		float[] aptitudes = new float[_poblacion.length];
		Cromosoma[] seleccionados = new Cromosoma[_poblacion.length];
		for (int i = 0; i<_poblacion.length; i++){
			seleccionados[i] = new Cromosoma(rand);
		}
		for(int i = 0; i < numGeneraciones; i++)
		{		
			for(int j = 0; j < _poblacion.length; j++)
			{
				aptitudes[j]=_poblacion[j].getAptitud();
				System.out.println(_poblacion[j].getFenotipo());
				System.out.println(_poblacion[j].getAptitud());
			}
			//Seleccionar
			asl.seleccionar(_poblacion, aptitudes, seleccionados);
			//Cruzar
			for (int k = 0; k<seleccionados.length/2; k++){
				Cromosoma h1 = new Cromosoma(rand);
				Cromosoma h2 = new Cromosoma(rand);
				crx.cruzar(seleccionados[k], seleccionados[k+1], h1, h2);
				seleccionados[k].copiarCromosoma(h1);
				seleccionados[k+1].copiarCromosoma(h2);
			}
			_poblacion = seleccionados;
			//Mutar
			mut.mutar(_poblacion, mutacionProb, rand);
		}
	}
	
}
