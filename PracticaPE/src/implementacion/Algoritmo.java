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
	private Cromosoma[] _chusma;

	//Valores problema concreto
	private float _mejorValor;
	private Cromosoma _mejorIndividuo;
	
	public Algoritmo(int poblacion, float precision, float cruce, float mutacion, String metodoSelec, boolean elitismo, String problema, int simulaciones, long semilla, int n, int participantes) 
	{
		//Seteo de los atributos necesarios
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
		if (_numElites == 0){
			_numElites=1;
		}
		if(_elitismo)
		{
			_elites = new Cromosoma[_numElites];
			_chusma = new Cromosoma[_numElites];
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
	}
	
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
			
			if(_elitismo)
			{
				introducirElites(aptitudes);
			}
		}
		
		return _mejorIndividuo.toString();
	}
	
	private void introducirElites(float[] aptitudes)
	{
		//generar chusma
		Comparator<javafx.util.Pair<Float, Integer>> comparador;
		if(_mejorIndividuo.isMaximizing())
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
		else
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
		
		//Guardamos la chusma
		for(int i = 0; i < _numElites; i++)
		{
			_chusma[i] = _poblacion[aux.poll().getValue()];
		}
				//reemplazar chusma por elite.
	}

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
	
	private void seleccionar(float[] aptitudes, float[] puntuacionesAcum)
	{
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
								if(o1.getKey() == o2.getKey()) return 0;
								if(o1.getKey() > o2.getKey()) return 1;
								return 0;
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
				_elites[i] = _poblacion[aux.poll().getValue()];
			}
		}
		
		Cromosoma[] seleccionados = new Cromosoma[_poblacionTamano];
		//Construimos el algoritmo de selección elegido
		AlgoritmoSeleccion ASeleccion = AlgoritmoSeleccionFabrica.getAlgoritmoDeSeleccion(_metodoSeleccion, _participantes);
		ASeleccion.seleccionar(aptitudes, puntuacionesAcum, seleccionados, _poblacionTamano, _poblacion, _mejorIndividuo.isMaximizing(), _rand);
		
		//Creamos la nueva población
		_poblacion = seleccionados;
		
	}
	
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
	
	private void mutar()
	{
		//Probamos la mutación.
		for(int i = 0; i < _poblacionTamano; i++)
		{
				_poblacion[i].mutar(_mutacionProb, _rand);
		}
	}
	
	public long getSemilla(){
		return _semilla;
	}
}
