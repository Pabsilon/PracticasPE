package implementacion;

import java.util.Random;

import especificos.ProblemaFabrica;

public class Algoritmo {
	//Parte genetica
	protected int _poblacionTamano, _simulaciones;
	protected Cromosoma[] _poblacion;
	protected float _precision, _cruceProb, _mutacionProb;
	protected String _metodoSeleccion;
	protected String _problema;
	protected int _n;
	protected Random _rand;
	protected int _participantes;
	protected long _semilla;

	//Valores problema concreto
	protected float _mejorValor;
	protected Cromosoma _mejorIndividuo;
	
	public Algoritmo(int poblacion, float precision, float cruce, float mutacion, String metodoSelec, String problema, int simulaciones, long semilla, int n, int participantes) 
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
		String resultado="";
		
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
		}
		
		//Salida del algoritmo
		if (!_problema.equals("Problema4")){
			//Problemas 1-3 y 5
			 resultado = "Valor mejor: " + _mejorValor + " en X: " + _mejorIndividuo.getFenotipo()[0];
			if (_mejorIndividuo.getFenotipo()[1]!=new Float("0")){
				resultado = resultado + " Y: " + _mejorIndividuo.getFenotipo()[1];
				
			}
		}else{
			//Problema 4
			resultado = "Valor mejor: " + _mejorValor;
			for (int i=0; i<_n; i++){
				resultado = resultado + ", x" + i +" = " + _mejorIndividuo.getFenotipo()[i];
			}
		}
		return resultado;
	}
	
	protected void evaluar(float[] aptitudes, float[] puntuaciones, float[] puntuacionesAcum, double[] infoGeneracion, boolean minMax)
	{
		//En caso de que sea un problema de maximización.
		if (minMax){
			float mejorAptitudEnGeneracion = Float.MIN_VALUE;
			int mejorCromGeneracion = 0;
			
			//Calculamos las aptitudes y su suma total
			float sumaAptitudes = 0;
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
			
			//Calculamos las puntuaciones para la fase de seleccion
			puntuaciones[0] = aptitudes[0] / sumaAptitudes;
			puntuacionesAcum[0] = puntuaciones[0];
			for(int i = 1; i < _poblacionTamano; i++)
			{
				puntuaciones[i] = aptitudes[i] / sumaAptitudes;
				puntuacionesAcum[i] = puntuaciones[i] + puntuacionesAcum[i - 1];
			}
			
			//Informacion de la generacion (Maximo y media)
			infoGeneracion[0] = mejorAptitudEnGeneracion;
			infoGeneracion[1] = sumaAptitudes / _poblacionTamano;
		}else{
			//Si es un problema de minimización
			float mejorAptitudEnGeneracion = Float.MAX_VALUE;
			int mejorCromGeneracion = 0;
			
			//Calculamos las aptitudes y su suma total
			float sumaAptitudes = 0;
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
			
			//Calculamos las puntuaciones para la fase de seleccion
			puntuaciones[0] = aptitudes[0] / sumaAptitudes;
			puntuacionesAcum[0] = puntuaciones[0];
			for(int i = 1; i < _poblacionTamano; i++)
			{
				puntuaciones[i] = aptitudes[i] / sumaAptitudes;
				puntuacionesAcum[i] = puntuaciones[i] + puntuacionesAcum[i - 1];
			}
			puntuacionesAcum[_poblacionTamano - 1] = 1; //Asegurarse que el ultimo valor de las puntuaciones es 1
			
			//Informacion de la generacion (Minimo y media)
			infoGeneracion[0] = mejorAptitudEnGeneracion;
			infoGeneracion[1] = sumaAptitudes / _poblacionTamano;
		}
	}
	
	private void seleccionar(float[] aptitudes, float[] puntuacionesAcum)
	{
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
