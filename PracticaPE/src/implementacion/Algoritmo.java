package implementacion;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

import javafx.util.Pair;
import especificos.ProblemaFabrica;

public class Algoritmo {
	//Parte genetica
	private int _poblacionTamano, _simulaciones;
	private Cromosoma[] _poblacion;
	private float _precision, _cruceProb, _mutacionProb;
	private String _metodoSeleccion;
	private boolean _elitismo;
	private String _problema;
	private int _n;
	private Random _rand;
	private int _participantes;
	private long _semilla;
	private int _numElites;
	private Cromosoma[] _elites;

	//Valores problema concreto
	private float _mejorValor;
	private Cromosoma _mejorIndividuo;
	
	/**Builder de algoritmo.
	 * Necesita como parametros una población y precisión, un pocentaje de cruce, un porcentaje de mutacion, un método de seleción, saber si hay elitismo, el problema a resolver, cuantas generaciones, una semilla, un numero entero (ciertos problemas) y el numero de participantes del torneo. 
	 * @param poblacion Número de individuos de la simulación.
	 * @param precision Precisión con la que se busca el resultado.
	 * @param cruce Porcentaje de cruce (0..100)
	 * @param mutacion Porcentaje de mutación (0..100)
	 * @param metodoSelec Método de selección (ruleta,torneo,torneoProbl)
	 * @param elitismo True si guardamos la elite, false si no.
	 * @param problema String con el nombre del problema (Problema1..5)
	 * @param simulaciones Número de generaciones de la simulación.
	 * @param semilla El valor al que ha de inicializarse la semilla (0 para random)
	 * @param n El número de parametros de entrada del problema 4.
	 * @param participantes El número de participantes en el torneo.
	 */
	public Algoritmo(int poblacion, float precision, float cruce, float mutacion, String metodoSelec, boolean elitismo, String problema, int simulaciones, long semilla, int n, int participantes) 
	{
		//Inicialización de atributos.
		_poblacionTamano = poblacion;
		_precision = precision;
		_cruceProb = cruce;
		_mutacionProb = mutacion;
		_simulaciones = simulaciones;
		_metodoSeleccion = metodoSelec;
		_problema = problema;
		_rand = new Random();
		_n = n;
		_participantes = participantes;
		_semilla =semilla;
		_elitismo = elitismo;
		_numElites = (int) (_poblacionTamano * 0.02); //2% de elites
		if (_numElites == 0){ //Si buscamos élite, que al menos haya uno.
			_numElites=1; 
		}
		
		//Tratamiento del random. 0 es una semilla random, otros valores son semillas a pincho.
		if (semilla !=0){
			_rand.setSeed(semilla);
		}else{
			_semilla = System.currentTimeMillis();
			_rand = new Random(_semilla);
		}
		//Inicialización de la poblacion.
		_poblacion = new Cromosoma[poblacion];
		for(int i = 0; i < poblacion; i++)
		{
			//Llevamos un parámetro de mas sobretodo para el problema 4.
			_poblacion[i] = ProblemaFabrica.getCromosomaProblema(problema, precision, n, _rand);
		}
		
		//El mejor individuo de la primera generación lo elegimos aleatoriamente
		//Esto es solo para representar correctamente la grafica.
		_mejorIndividuo = ProblemaFabrica.getCromosomaProblema(problema, precision, n, _rand);
		
		//Si hay elitismo, tenemos que inicializar la array en la que guardaremos los élites.
		if(_elitismo)
		{
			_elites = new Cromosoma[_numElites];
			
			for(int i = 0; i < _numElites; i++)
			{
				_elites[i] = ProblemaFabrica.getCromosomaProblema(problema, precision, n, _rand);
			}
		}
		
	}
	
	/** Esta función ejecuta el algoritmo con los parámetros del builder.
	 * @param mejorAbsoluto Un array donde se contendrá el mejor absoluto hasta la generación.
	 * @param mejorGeneracion Un array donde se guardará el mejor de cada generación.
	 * @param mediaGeneracion Un array donde se acumulará la media de cada generación.
	 * @return Un string con el mejor absoluto encontrado en el espacio de búsqueda.
	 */
	public String simular(double[] mejorAbsoluto, double[] mejorGeneracion, double[] mediaGeneracion)
	{
		//Variables para guardar los resultados obtenidos
		float[] aptitudes = new float[_poblacionTamano];
		float[] puntuaciones = new float[_poblacionTamano];
		float[] puntuacionesAcum = new float[_poblacionTamano];
		
		for(int i = 0; i < _simulaciones; i++)
		{
			//Se evaluan todos los valores de la población
			double[] infoGeneracion = new double[2]; //0 Mejor de la generacion, 1 Media de la generacion
			evaluar(aptitudes, puntuaciones, puntuacionesAcum, infoGeneracion,_poblacion[0].isMaximizing());
			mejorAbsoluto[i] = _mejorValor;
			mejorGeneracion[i] = infoGeneracion[0];
			mediaGeneracion[i] = infoGeneracion[1];
			
			//Seleccionamos para el cruce
			seleccionar(aptitudes, puntuacionesAcum);
			
			//Cruzamos
			cruzar();
			
			//Mutamos
			mutar();
			
			//Si hay elitismo, evaluamos otra vez para obtener los peores y sustituirlos por la elite.
			if(_elitismo)
			{
				evaluar(aptitudes, puntuaciones, puntuacionesAcum, infoGeneracion,_poblacion[0].isMaximizing());
				introducirElites(aptitudes);
			}
		}
		
		return _mejorIndividuo.toString();
	}
	
	/**Este método introduce los valores locales de élites en los valores locales de población, reemplazando la chusma.
	 * @param aptitudes La aptitud actual de los evaluados.
	 */
	private void introducirElites(float[] aptitudes)
	{
		//Generamos la chusma
		////////////////////////////////////////////////////////////////////////
		Comparator<javafx.util.Pair<Float, Integer>> comparador;
		if(_mejorIndividuo.isMaximizing()) //Maximización
		{
			comparador = new Comparator<javafx.util.Pair<Float, Integer>>()
			{

				@Override
				public int compare(Pair<Float, Integer> o1,
						Pair<Float, Integer> o2) {
					if(o1.getKey() < o2.getKey()) return -1;
					if(o1.getKey() == o2.getKey()) return 0;
					if(o1.getKey() > o2.getKey()) return 1;
					return 0;
				}
			};
		}
		else //Minimización
		{
			comparador = new Comparator<javafx.util.Pair<Float, Integer>>()
					{
		
						@Override
						public int compare(Pair<Float, Integer> o1,
								Pair<Float, Integer> o2) {
							if(o1.getKey() > o2.getKey()) return -1;
							if(o1.getKey() == o2.getKey()) return 0;
							if(o1.getKey() < o2.getKey()) return 1;
							return 0;
						}
					};	
		}
		PriorityQueue<javafx.util.Pair<Float, Integer>> aux = new PriorityQueue<javafx.util.Pair<Float, Integer>>(comparador);
		for(int i = 0; i < _poblacionTamano; i++)
		{
			aux.add(new javafx.util.Pair<Float, Integer>(aptitudes[i], i));
		}
		
		//////////////////////////////////////////////////////////////////////////
		
		//Cambiamos chusma por la elite
		for(int i = 0; i < _numElites; i++)
		{
			_poblacion[aux.poll().getValue()].copiarCromosoma(_elites[i]); //aux.poll().getValue()  <- posicion de los peores valores (chusma)
		}
	}

	/**Método que evalua la población actual.
	 * @param aptitudes Array de aptitudes de la poblacíon en la generación actual.
	 * @param puntuaciones Array de puntuaciones de la poblacíon en la generación actual.
	 * @param puntuacionesAcum Array de puntuaciones acumuladas de la poblacíon en la generación actual.
	 * @param infoGeneracion Array[2] que contiene en 0 la mejor puntuación y en 1 la media.
	 * @param maximizacion Booleano para saber si es un problema de maximización (true) o no (false)
	 */
	private void evaluar(float[] aptitudes, float[] puntuaciones, float[] puntuacionesAcum, double[] infoGeneracion, boolean maximizacion)
	{
		//En caso de que sea un problema de maximización.
		float sumaAptitudes = 0;
		float mejorAptitudEnGeneracion;
		if (maximizacion){
			mejorAptitudEnGeneracion = Float.MIN_VALUE;
			int mejorCromGeneracion = 0;
			
			//Calculamos las aptitudes y su suma total
			for(int i = 0; i < _poblacionTamano; i++)
			{
				aptitudes[i] = _poblacion[i].getAptitud();
				sumaAptitudes += aptitudes[i];
				
				//Actualizamos el mejor de la generacion
				if(aptitudes[i] > mejorAptitudEnGeneracion)
				{
					mejorAptitudEnGeneracion = aptitudes[i];
					mejorCromGeneracion = i;
				}
			}
			//Actualizamos el mejor global
			if(mejorAptitudEnGeneracion > _mejorValor)
			{
				_mejorValor = mejorAptitudEnGeneracion;
				
				_mejorIndividuo.copiarCromosoma(_poblacion[mejorCromGeneracion]);
			}
		}else{
			//Si es un problema de minimización
			mejorAptitudEnGeneracion = Float.MAX_VALUE;
			int mejorCromGeneracion = 0;
			
			//Calculamos las aptitudes y su suma total
			for(int i = 0; i < _poblacionTamano; i++)
			{
				aptitudes[i] = _poblacion[i].getAptitud();
				sumaAptitudes += aptitudes[i];
				
				//Actualizar el mejor de la generacion
				if(aptitudes[i] < mejorAptitudEnGeneracion)
				{
					mejorAptitudEnGeneracion = aptitudes[i];
					mejorCromGeneracion = i;
				}
			}
			
			//Actualizamos el mejor global
			if(mejorAptitudEnGeneracion < _mejorValor)
			{
				_mejorValor = mejorAptitudEnGeneracion;
				
				_mejorIndividuo.copiarCromosoma(_poblacion[mejorCromGeneracion]);
			}
		}
		
		//Calculamos las puntuaciones para la fase de seleccion
		puntuaciones[0] = aptitudes[0] / sumaAptitudes;
		puntuacionesAcum[0] = puntuaciones[0];
		for(int i = 1; i < _poblacionTamano; i++)
		{
			puntuaciones[i] = aptitudes[i] / sumaAptitudes;
			puntuacionesAcum[i] = puntuaciones[i] + puntuacionesAcum[i - 1];
		}
		puntuacionesAcum[_poblacionTamano - 1] = 1; //Asegurarse que el ultimo valor de las puntuaciones es 1
		
		//Informacion de la generacion (Mejor y media)
		infoGeneracion[0] = mejorAptitudEnGeneracion;
		infoGeneracion[1] = sumaAptitudes / _poblacionTamano;
	}
	
	/**Método que decide qué cromosomas se cruzan, en función del algoritmo de selección.
	 * @param aptitudes Array de aptitudes de la poblacíon en la generación actual.
	 * @param puntuacionesAcum Array de la puntuación acumulada de la poblacíon en la generación actual.
	 */
	private void seleccionar(float[] aptitudes, float[] puntuacionesAcum)
	{
		//Si hemos seleccionado la opción de elitismo buscamos la élite.
		if(_elitismo)
		{			
			Comparator<javafx.util.Pair<Float, Integer>> comparador;
			if(_mejorIndividuo.isMaximizing())
			{
				comparador = new Comparator<javafx.util.Pair<Float, Integer>>()
				{
	
					@Override
					public int compare(Pair<Float, Integer> o1,
							Pair<Float, Integer> o2) {
						if(o1.getKey() > o2.getKey()) return -1;
						if(o1.getKey() == o2.getKey()) return 0;
						if(o1.getKey() < o2.getKey()) return 1;
						return 0;
					}
				};
			}
			else
			{
				comparador = new Comparator<javafx.util.Pair<Float, Integer>>()
						{
			
							@Override
							public int compare(Pair<Float, Integer> o1,
									Pair<Float, Integer> o2) {
								if(o1.getKey() < o2.getKey()) return -1;
								else if(o1.getKey() == o2.getKey()) return 0;
								else if(o1.getKey() > o2.getKey()) return 1;
								else return 0;
							}
						};	
			}
			
			PriorityQueue<javafx.util.Pair<Float, Integer>> aux = new PriorityQueue<javafx.util.Pair<Float, Integer>>(comparador);
			for(int i = 0; i < _poblacionTamano; i++)
			{
				aux.add(new javafx.util.Pair<Float, Integer>(aptitudes[i], i));
			}
			
			//Guardamos los elites
			for(int i = 0; i < _numElites; i++)
			{
				_elites[i].copiarCromosoma(_poblacion[aux.poll().getValue()]);
			}
		}
		
		Cromosoma[] seleccionados = new Cromosoma[_poblacionTamano];
		for(int i = 0; i < _poblacionTamano; i++)
		{
			seleccionados[i] = ProblemaFabrica.getCromosomaProblema(_problema, _precision, _n, _rand);
		}
		//Construimos el algoritmo de selección elegido
		AlgoritmoSeleccion ASeleccion = AlgoritmoSeleccionFabrica.getAlgoritmoDeSeleccion(_metodoSeleccion, _participantes);
		ASeleccion.seleccionar(aptitudes, puntuacionesAcum, seleccionados, _poblacionTamano, _poblacion, _mejorIndividuo.isMaximizing(), _rand);
		
		//Creamos la nueva población
		_poblacion = seleccionados;
		
	}
	
	/** Mëtodo que cruza la población.
	 * 
	 */
	private void cruzar()
	{
		int numeroSeleccionados = 0;
		int[] aCruzar = new int[_poblacionTamano];
		
		//Obtenemos los cromosomas a cruzar
		for(int i = 0; i < _poblacionTamano; i++)
		{
			if(_rand.nextFloat() < _cruceProb)
			{
				aCruzar[numeroSeleccionados] = i;
				numeroSeleccionados++;
			}
		}
		//Redondeo a par
		if(numeroSeleccionados % 2 == 1)
		{
			numeroSeleccionados--;
		}
		
		//Cruzamos
		for(int i = 0; i < (numeroSeleccionados / 2); i++)
		{
			Cromosoma hijo1 = ProblemaFabrica.getCromosomaProblema(_problema, _precision, _n, _rand);
			Cromosoma hijo2 = ProblemaFabrica.getCromosomaProblema(_problema, _precision, _n, _rand);
			Cromosoma.cruzar(_poblacion[aCruzar[i]], _poblacion[aCruzar[i + 1]], hijo1, hijo2, _rand);
			
			//Sustituimos a los padres
			_poblacion[aCruzar[i]] = hijo1;
			_poblacion[aCruzar[i + 1]] = hijo2;
		}
	}
	
	/**Método que muta a la población.
	 * 
	 */
	private void mutar()
	{
		for(int i = 0; i < _poblacionTamano; i++)
		{
				_poblacion[i].mutar(_mutacionProb, _rand);
		}
	}
	
	/**Getter de la semilla random utilizada.
	 * @return el valor de la semilla.
	 */
	public long getSemilla(){
		return _semilla;
	}
}
