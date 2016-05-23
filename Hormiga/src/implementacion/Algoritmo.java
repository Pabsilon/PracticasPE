package implementacion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

import abloating.ABloating;
import acruce.ACruce;
import amutacion.AMutacion;
import aseleccion.ASeleccion;
import javafx.util.Pair;

public class Algoritmo
{

	private Hormiga _poblacion[];
	private final int _generacionTotales;
	private final ACruce _agc;
	private final ASeleccion _ags;
	private final AMutacion _agm;
	private final ABloating _agb;
	private final boolean _bloating;
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
	 * @param metodoCreacion 
	 */
	public Algoritmo(int tamanoPoblacion, int generaciones, long semilla, ACruce agc, AMutacion agm, ASeleccion ags, ABloating agb, boolean bloating, float cruceProbabilidad, float mutacionProbabilidad, boolean elitismo, int profundidad, String metodoCreacion)
	{
		_generacionTotales = generaciones;
		_agc = agc;
		_ags = ags;
		_agm = agm;
		_agb = agb;
		_probabilidadCruce = cruceProbabilidad/100.0f;
		_probabilidadMutacion = mutacionProbabilidad/100.0f;
		_elitismo = elitismo;
		_numeroElites = (int)(0.02f * tamanoPoblacion);
		_profundidadMaxima = profundidad;
		_bloating = bloating;
		
		_semilla = semilla;
		//Generar poblacion usando la semilla de parametro
		if(_semilla == 0)
		{
			_semilla = System.currentTimeMillis();
		}
		Random rand = new Random(_semilla);
		_poblacion = new Hormiga[tamanoPoblacion];
		
		generarPoblacion(metodoCreacion, rand);
		
		_mejorIndividuo = _poblacion[0];
	}

	private void generarPoblacion(String metodoCreacion, Random rand) 
	{
		if(metodoCreacion.equals("RampedHalf"))
		{
			//Dividimos la poblacion en ProfundidmadMaxima-1 grupos
			int individuosPorGrupo = _poblacion.length / _profundidadMaxima;
			int k = 0;
			for(int i = 0; i < _profundidadMaxima; i++)
			{
				for(int j = 0; j < individuosPorGrupo / 2; j++)
				{
					_poblacion[k] = new Hormiga(i, "Completo", rand);
					k++;
				}
				for(int j = individuosPorGrupo / 2; j < individuosPorGrupo; j++)
				{
					_poblacion[k] = new Hormiga(i, "Creciente", rand);
					k++;
				}
			}
			//Si la division no es entera pueden quedar individuos sin crear
			int quedan = _poblacion.length - k;
			for(int i = 0; i < quedan / 2; i++)
			{
				_poblacion[k] = new Hormiga(i, "Completo", rand);
				k++;
			}
			for(int i = quedan / 2; i < quedan; i++)
			{
				_poblacion[k] = new Hormiga(i, "Creciente", rand);
				k++;
			}
		}
		else
		{
			for(int i = 0; i < _poblacion.length; i++)
			{
				_poblacion[i] = new Hormiga(_profundidadMaxima, metodoCreacion, rand);
			}
		}
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
		Hormiga elite[] = new Hormiga[_numeroElites];
		int aptitudes[] = new int[_poblacion.length];
		Hormiga seleccionados[] = new Hormiga[_poblacion.length]; //No inicializamos los elementos porque el algoritmo de seleccion ya lo hace
		
		//Evaluar
		evaluar(aptitudes, mejorAbsoluto, mejorGeneracion, mediaGeneracion, 0, elite);
		
		for(int g = 1; g < _generacionTotales; g++)
		{
			//Seleccionar
			_ags.seleccionar(_poblacion, aptitudes, seleccionados);
			//Cruzar
			cruzar(seleccionados);
			//Mutar
			//TODO Preguntar si la mutacion se elige cual usar o es una probabildiad
			mutar(seleccionados);
			
			//Cambiar por la nueva poblacion
			_poblacion = Arrays.copyOf(seleccionados, seleccionados.length);
			
			if(_elitismo)
			{
				introducirElites(elite);
			}
			
			//Evaluar
			evaluar(aptitudes, mejorAbsoluto, mejorGeneracion, mediaGeneracion, g, elite);
			
			if(_bloating)
			{
				_agb.controlBloating(_poblacion);
			}
		}
		
		return _mejorIndividuo;
	}

	private void introducirElites(Hormiga[] elite)
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
		for(int i = 0; i < _poblacion.length; i++)
		{
			monticuloMinimos.add(new javafx.util.Pair<Integer, Integer>(_poblacion[i].getAptitud(), i));
		}
		
		
		//Cambiamos peores por la elite
		for(int i = 0; i < _numeroElites; i++)
		{
			_poblacion[monticuloMinimos.poll().getValue()] = elite[i];
		}		
	}

	private void mutar(Hormiga[] seleccionados)
	{
		Random rand = new Random();
		for(int i = 0; i < _poblacion.length; i++)
		{
			if(rand.nextFloat() < _probabilidadMutacion)
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
			if(rand.nextFloat() < _probabilidadCruce)
			{
				Hormiga p1 = seleccionados[i];
				int indx2 = rand.nextInt(_poblacion.length);
				indx2 = indx2 == i ? (i + 1) % _poblacion.length : indx2; //Si el padre 2 es el mismo que el padre 1, elegir el siguiente.
				Hormiga p2 = seleccionados[indx2];
				Hormiga hijo1 = new Hormiga();
				Hormiga hijo2 = new Hormiga();
				
				_agc.cruzar(p1, p2, hijo1, hijo2);
				
				seleccionados[i] = hijo1;
				seleccionados[indx2] = hijo2;
			}
		}
	}

	private void evaluar(int[] aptitudes, double[] mejorAbsoluto, double[] mejorGeneracion, double[] mediaGeneracion, int generacion, Hormiga[] elite) 
	{
		PriorityQueue<Hormiga> mejores = Hormiga.crearColaPrioridadHormiga();
		float sumaApt = 0;
		for(int i = 0; i < _poblacion.length; i++)
		{
			aptitudes[i] = _poblacion[i].getAptitud();
			sumaApt += aptitudes[i];
			mejores.add(_poblacion[i]);
		}

		//Si el mejor de eta generacion es mejor que el maximo global, sustituir
		if(mejores.peek().getAptitud() > _mejorIndividuo.getAptitud())
		{
			_mejorIndividuo = mejores.peek().crearCopia();
		}
		
		mejorAbsoluto[generacion] = _mejorIndividuo.getAptitud();
		mejorGeneracion[generacion] = mejores.peek().getAptitud();
		mediaGeneracion[generacion] = sumaApt / _poblacion.length;
		
		for(int i = 0; i < _numeroElites; i++)
		{
			elite[i] = mejores.poll().crearCopia();
		}
	}

	public long getSemilla() {
		return _semilla;
	}
}
