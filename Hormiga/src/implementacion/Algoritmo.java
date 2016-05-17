package implementacion;

import java.util.PriorityQueue;
import java.util.Random;

import acruce.ACruce;
import amutacion.AMutacion;
import aseleccion.ASeleccion;

public class Algoritmo
{

	private Hormiga _poblacion[];
	private final int _generacionTotales;
	private final ACruce _agc;
	private final ASeleccion _ags;
	private final AMutacion _agm;
	private final float _probabilidadCruce, _probabilidadMutacion;
	private final boolean _elitismo;
	private final int _numeroElites;
	private long _semilla;
	private int _profundidadMaxima;
	
	private Hormiga _mejorIndividuo;
	
	
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
	public Algoritmo(int tamanoPoblacion, int generaciones, long semilla, ACruce agc, AMutacion agm, ASeleccion ags, float cruceProbabilidad, float mutacionProbabilidad, boolean elitismo)
	{
		_generacionTotales = generaciones;
		_agc = agc;
		_ags = ags;
		_agm = agm;
		_probabilidadCruce = cruceProbabilidad/100.0f;
		_probabilidadMutacion = mutacionProbabilidad/100.0f;
		_elitismo = elitismo;
		_numeroElites = (int)(0.02f * tamanoPoblacion);
		_profundidadMaxima = 4; //TODO cambiar esto a argumento
		
		_semilla = semilla;
		//Generar poblacion usando la semilla de parametro
		if(_semilla == 0)
		{
			_semilla = System.currentTimeMillis();
		}
		Random rand = new Random(_semilla);
		_poblacion = new Hormiga[tamanoPoblacion];
		for(int i = 0; i < tamanoPoblacion; i++)
		{
			//TODO hacer esto
			_poblacion[i] = new Hormiga(_profundidadMaxima, rand);
		}
		
		_mejorIndividuo = _poblacion[0];
	}

	//Ejecuta las generaciones y devuelve el mejor elemento
	/**Ejecuta las generaciones y devuelve el mejor elemento
	 * @param mejorAbsoluto Array que contiene el mejor absoluto hasta esta generacion
	 * @param mejorGeneracion Array que contiene el mejor de la generacion en esta generacion
	 * @param mediaGeneracion Array que contiene la media de la generacion en esta generacion
	 * @return El mejor valor encontrado
	 */
	public Hormiga ejecutarAlgoritmo(double[] mejorAbsoluto, double[] mejorGeneracion, double[] mediaGeneracion)
	{
		//Cola de maximos donde se guarda la poblacion. Los mejores son los primeros
		PriorityQueue<Hormiga> elite = Hormiga.crearColaPrioridadHormiga();
		int aptitudes[] = new int[_poblacion.length];
		Hormiga seleccionados[] = new Hormiga[_poblacion.length]; //No inicializamos los elementos porque el algoritmo de seleccion ya lo hace
		
		for(int g = 0; g < _generacionTotales; g++)
		{
			//Evaluar
			evaluar(mejorAbsoluto, mejorGeneracion, mediaGeneracion, g, elite);
			//Seleccionar
			_ags.seleccionar(_poblacion, aptitudes, seleccionados);
			//Cruzar
			cruzar(seleccionados);
			//Mutar
			//TODO Preguntar si la mutacion se elige cual usar o es una probabildiad
			mutar(seleccionados);
			
			//TODO elitismo aqui
			
			//Cambiar a la nueva poblacion
			_poblacion = seleccionados;
		}
		
		return _mejorIndividuo;
	}

	private void mutar(Hormiga[] seleccionados)
	{
		Random rand = new Random();
		for(int i = 0; i < _poblacion.length; i++)
		{
			if(_probabilidadMutacion <= rand.nextFloat())
			{
				_agm.mutar(seleccionados[i]);
			}
		}
	}

	private void cruzar(Hormiga[] seleccionados)
	{
		Random rand = new Random();
		for(int i = 0; i < seleccionados.length; i++)
		{
			if(rand.nextFloat() <= _probabilidadCruce)
			{
				Hormiga p1 = seleccionados[i];
				int indx2 = rand.nextInt(_poblacion.length);
				indx2 = indx2 == i ? (i + 1) % _poblacion.length : indx2; //Si el padre 2 es el mismo que el padre 1, elegir el siguiente.
				Hormiga p2 = seleccionados[indx2];
				Hormiga hijo1 = new Hormiga(0, rand);
				Hormiga hijo2 = new Hormiga(0, rand);
				
				_agc.cruzar(p1, p2, hijo1, hijo2);
				
				seleccionados[i] = hijo1;
				seleccionados[indx2] = hijo2;
			}
		}
	}

	private void evaluar(double[] mejorAbsoluto, double[] mejorGeneracion, double[] mediaGeneracion, int generacion, PriorityQueue<Hormiga> elite) 
	{
		float sumaApt = 0;
		for(Hormiga h : _poblacion)
		{
			sumaApt += h.getAptitud();			
			elite.add(h);
		}
		//Si el mejor de eta generacion es mejor que el maximo global, sustituir
		if(elite.peek().getAptitud() > _mejorIndividuo.getAptitud())
		{
			_mejorIndividuo = elite.peek().crearCopia();
		}
		
		mejorAbsoluto[generacion] = _mejorIndividuo.getAptitud();
		mejorGeneracion[generacion] = elite.peek().getAptitud();
		mediaGeneracion[generacion] = sumaApt / _poblacion.length;
	}

	public long getSemilla() {
		return _semilla;
	}
}
