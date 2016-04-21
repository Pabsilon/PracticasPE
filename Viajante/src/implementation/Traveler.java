package implementation;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

import acruce.ACruce;
import amutacion.AMutacion;
import aseleccion.ASeleccion;
import javafx.util.Pair;

//Distancia optima dada por el profesor: 5298
public class Traveler 
{
	private Cromosoma _poblacion[];
	private final int _generacionTotales;
	private final ACruce _agc;
	private final ASeleccion _ags;
	private final AMutacion _agm;
	private final float _probabilidadCruce, _probabilidadMutacion;
	private final boolean _elitismo;
	private final int _numeroElites;
	private long _semilla;
	
	private Cromosoma _mejorCromosoma;
	
	
	public Traveler(int tamanoPoblacion, int generaciones, long semilla, ACruce agc, AMutacion agm, ASeleccion ags, float cruceProbabilidad, float mutacionProbabilidad, boolean elitismo)
	{
		_generacionTotales = generaciones;
		_agc = agc;
		_ags = ags;
		_agm = agm;
		_probabilidadCruce = cruceProbabilidad;
		_probabilidadMutacion = mutacionProbabilidad/100;
		_elitismo = elitismo;
		_numeroElites = (int)(0.02f * tamanoPoblacion);
		
		
		_semilla = semilla;
		//Generar poblacion usando la semilla de parametro
		if(_semilla == 0)
		{
			_semilla = System.currentTimeMillis();
		}
		Random rand = new Random(_semilla);
		_poblacion = new Cromosoma[tamanoPoblacion];
		for(int i = 0; i < tamanoPoblacion; i++)
		{
			_poblacion[i] = new Cromosoma(rand);
		}
		
		_mejorCromosoma = new Cromosoma(rand);
	}

	//Ejecuta las generaciones y devuelve el mejor elemento
	public String ejecutarAlgoritmo(double[] mejorAbsoluto, double[] mejorGeneracion, double[] mediaGeneracion)
	{
		//Variables auxiliares
		int aptitudes[] = new int[_poblacion.length];
		Cromosoma elites[] = new Cromosoma[_numeroElites];
		for(int i = 0; i < elites.length; i++)
		{
			elites[i] = new Cromosoma();
		}
		Cromosoma seleccionados[] = new Cromosoma[_poblacion.length];
		Random rand = new Random();
		
		for(int i = 0; i < _poblacion.length; i++)
		{
			seleccionados[i] = new Cromosoma();
		}
		
		
		for(int i = 0; i < _generacionTotales; i++)
		{
			//Evaluar
			evaluar(aptitudes,elites, mejorAbsoluto, mejorGeneracion, mediaGeneracion, i);
			
			//Seleccionar
			_ags.seleccionar(_poblacion, aptitudes, seleccionados);
			for(int j = 0; j < _poblacion.length; j++)
			{
				_poblacion[j].copiarCromosoma(seleccionados[j]);
			}
			
			//Cruzar
			for(int j = 0; j < _poblacion.length; j++)
			{
				if(rand.nextFloat() < _probabilidadCruce)
				{
					int indP1 = j;
					int indP2 = rand.nextInt(_poblacion.length); //Elegir pareja al azar
					Cromosoma p1 = _poblacion[indP1];
					Cromosoma p2 = _poblacion[indP2];
					Cromosoma h1 = new Cromosoma();
					Cromosoma h2 = new Cromosoma();
					
					_agc.cruzar(p1, p2, h1, h2);
					
					_poblacion[indP1] = h1;
					_poblacion[indP2] = h2;
				}
			}
			
			//Mutar
			_agm.mutar(_poblacion, _probabilidadMutacion, rand);
			
			//Recupear elites
			if(_elitismo)
			{
				recuperarElites(aptitudes, elites);
			}
			
		}
		
		return _mejorCromosoma.getFenotipo();
	}
	
	//Evalua la poblacion. Calcula diferentes datos y devuelve aptitudes, elites y actualiza las arrays de datos para mostrar
	private void evaluar(int[] aptitudes, Cromosoma[] elites, double[] mejorAbsoluto, double[] mejorGeneracion, double[] mediaGeneracion, int generacion)
	{
		//Generamos aptitudes, su media, mejor elemento (minima aptitud)
		PriorityQueue<Cromosoma> colaMinimos = FAuxiliares.getColaPrioridadMinimos();
		float mediaAptitudes = 0;
		int aptitudMayor = Integer.MIN_VALUE; //Guardamos la mayor aptitud para despalzar aptitudes
		for(int i = 0; i < _poblacion.length; i++)
		{
			aptitudes[i] = _poblacion[i].getAptitud();
			mediaAptitudes += aptitudes[i];
			
			colaMinimos.add(_poblacion[i]);
			
			if(aptitudes[i] > aptitudMayor)
			{
				aptitudMayor = aptitudes[i];
			}
		}
		mediaAptitudes = mediaAptitudes / _poblacion.length;
		mejorGeneracion[generacion] = colaMinimos.peek().getAptitud();
		
		//Actualizamos el mejor elemento
		if(colaMinimos.peek().getAptitud() < _mejorCromosoma.getAptitud())
		{
			_mejorCromosoma.copiarCromosoma(colaMinimos.peek());
		}
		//Si hay elitismo los guardamos
		if(_elitismo)
		{
			for(int i = 0; i < _numeroElites; i++)
			{
				elites[i].copiarCromosoma(colaMinimos.remove());
			}
		}
		
		//Actualizamos arrays de datos a mostrar (Mejor generacion guardado antes)
		mejorAbsoluto[generacion] = _mejorCromosoma.getAptitud();
		mediaGeneracion[generacion] = mediaAptitudes;
		
		//Desplazamos aptitudes para crear un problema de maximizacion
		for(int i = 0; i < aptitudes.length; i++)
		{
			aptitudes[i] = aptitudMayor - aptitudes[i];
		}
		
	}
	
	private void recuperarElites(int aptitudes[], Cromosoma elites[])
	{
		Comparator<javafx.util.Pair<Integer, Integer>> comparador;
		comparador = new Comparator<javafx.util.Pair<Integer, Integer>>()
		{

			@Override
			public int compare(Pair<Integer, Integer> o1,
					Pair<Integer, Integer> o2) {
				if(o1.getKey() < o2.getKey()) return -1;
				if(o1.getKey() == o2.getKey()) return 0;
				if(o1.getKey() > o2.getKey()) return 1;
				return 0;
			}
		};
		
		PriorityQueue<javafx.util.Pair<Integer, Integer>> monticuloMinimos = new PriorityQueue<javafx.util.Pair<Integer, Integer>>(comparador);
		for(int i = 0; i < aptitudes.length; i++)
		{
			monticuloMinimos.add(new javafx.util.Pair<Integer, Integer>(aptitudes[i], i));
		}
		
		
		//Cambiamos peores por la elite
		for(int i = 0; i < _numeroElites; i++)
		{
			_poblacion[monticuloMinimos.poll().getValue()].copiarCromosoma(elites[i]); //aux.poll().getValue()  <- posicion de los peores valores (chusma)
		}
	}
	
	public long getSemilla(){
		return _semilla;
	}
	
}
