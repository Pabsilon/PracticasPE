package implementation;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

import acruce.ACruce;
import amutacion.AMutacion;
import aseleccion.ASeleccion;
import javafx.util.Pair;

//Distancia optima dada por el profesor: 5298
/**Implementacion del Viajante
 * @author Jorge Sanchez, Pablo Mac-Veigh
 *
 */
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
	
	
	/**Constructora de Traveler
	 * @param tamanoPoblacion El tama�o de la poblacion
	 * @param generaciones El numero de generaciones
	 * @param semilla La semilla utilizada para generar la poblacion inicial
	 * @param agc El algoritmo de cruce
	 * @param agm El algoritmo de mutacion
	 * @param ags El algoritmo de seleccion
	 * @param cruceProbabilidad La probabilidad de Cruce
	 * @param mutacionProbabilidad La probabilidad de Mutacion
	 * @param elitismo Si hay elitismo
	 */
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
	/**Ejecuta las generaciones y devuelve el mejor elemento
	 * @param mejorAbsoluto Array que contiene el mejor absoluto hasta esta generacion
	 * @param mejorGeneracion Array que contiene el mejor de la generacion en esta generacion
	 * @param mediaGeneracion Array que contiene la media de la generacion en esta generacion
	 * @return El mejor valor encontrado
	 */
	public Cromosoma ejecutarAlgoritmo(double[] mejorAbsoluto, double[] mejorGeneracion, double[] mediaGeneracion)
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
		
		return _mejorCromosoma;
	}
	
	/**Evalua la poblacion. Calcula diferentes datos y devuelve aptitudes, elites y actualiza las arrays de datos para mostrar
	 * @param aptitudes El array de aptitudes
	 * @param elites El array de los elites
	 * @param mejorAbsoluto El array del mejor absoluto 
	 * @param mejorGeneracion El array del mejor de la generacion
	 * @param mediaGeneracion El array de la media de la generacion
	 * @param generacion la generacion actual
	 */
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
	
	/** Devuelve a la elite
	 * @param aptitudes las aptitudes de la poblacion
	 * @param elites el array de los elites
	 */
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
	
	/**Devuelve la semilla utilizada para generar la poblacion inicial
	 * @return
	 */
	public long getSemilla(){
		return _semilla;
	}
	
}
