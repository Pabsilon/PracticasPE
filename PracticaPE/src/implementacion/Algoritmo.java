package implementacion;

import java.util.Random;

import especificos.Problema1;
import especificos.ProblemaFabrica;

public class Algoritmo {
	//Parte genetica
	protected int _poblacionTamano, _simulaciones;
	protected Cromosoma[] _poblacion;
	protected float _precision, _cruceProb, _mutacionProb;
	protected String _metodoSeleccion;

	//Valores problema concreto
	protected float _mejorValor;
	protected Cromosoma _mejorIndividuo;
	
	public Algoritmo(int poblacion, float precision, float cruce, float mutacion, String metodoSelec, String problema, int simulaciones) 
	{
		_poblacionTamano = poblacion;
		_precision = precision;
		_cruceProb = cruce;
		_mutacionProb = mutacion;
		_simulaciones = simulaciones;
		_metodoSeleccion = metodoSelec;
		
		_poblacion = new Cromosoma[poblacion];
		for(int i = 0; i < poblacion; i++)
		{
			_poblacion[i] = ProblemaFabrica.getCromosomaProblema(problema, precision, 0);
		}
		
		//CAMBIAR ESTO
		_mejorIndividuo = new Problema1(precision);;
	}
	
	public String simular(double[] mejorAbsoluto, double[] mejorGeneracion, double[] mediaGeneracion)
	{
		float[] aptitudes = new float[_poblacionTamano];
		float[] puntuaciones = new float[_poblacionTamano];
		float[] puntuacionesAcum = new float[_poblacionTamano];
		
		for(int i = 0; i < _simulaciones; i++)
		{
			//Evaluar
			double[] infoGeneracion = new double[2]; //0 Mejor de la generacion, 1 Media de la generacion
			evaluar(aptitudes, puntuaciones, puntuacionesAcum, infoGeneracion,_poblacion[0].isMaximizing());
			mejorAbsoluto[i] = _mejorValor;
			mejorGeneracion[i] = infoGeneracion[0];
			mediaGeneracion[i] = infoGeneracion[1];
			
			//Seleccionar
			seleccionar(aptitudes, puntuacionesAcum);
			
			//Cruzar
			cruzar();
			
			//Mutar
			mutar();
		}
		
		//CAMBIAR ESTO
		String resultado = "Valor mejor: " + _mejorValor + " en X: " + _mejorIndividuo.getFenotipo()[0];
		return resultado;
	}
	
	protected void evaluar(float[] aptitudes, float[] puntuaciones, float[] puntuacionesAcum, double[] infoGeneracion, boolean minMax)
	{
		if (minMax){
			float mejorAptitudEnGeneracion = Float.MIN_VALUE;
			int mejorCromGeneracion = 0;
			
			//Calculamos las aptitudes y su suma total
			float sumaAptitudes = 0;
			for(int i = 0; i < _poblacionTamano; i++)
			{
				aptitudes[i] = _poblacion[i].getAptitud();
				sumaAptitudes += aptitudes[i];
				
				//Actualizar el mejor de la generacion
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
			
			//Informacion de la generacion (M�ximo y media)
			infoGeneracion[0] = mejorAptitudEnGeneracion;
			infoGeneracion[1] = sumaAptitudes / _poblacionTamano;
		}else{
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
			
			//Informacion de la generacion (M�ximo y media)
			infoGeneracion[0] = mejorAptitudEnGeneracion;
			infoGeneracion[1] = sumaAptitudes / _poblacionTamano;
		}
	}
	
	private void seleccionar(float[] aptitudes, float[] puntuacionesAcum)
	{
		Cromosoma[] seleccionados = new Cromosoma[_poblacionTamano];
		
		AlgoritmoSeleccion ASeleccion = AlgoritmoSeleccionFabrica.getAlgoritmoDeSeleccion(_metodoSeleccion);
		ASeleccion.seleccionar(aptitudes, puntuacionesAcum, seleccionados, _poblacionTamano, _poblacion, _mejorIndividuo.isMaximizing());
		
		//Crear la nueva poblacion
		_poblacion = seleccionados;
		
	}
	
	private void cruzar()
	{
		Random rand = new Random();
		int numeroSeleccionados = 0;
		int[] aCruzar = new int[_poblacionTamano];
		
		//Obtener los cromosomas a cruzar
		for(int i = 0; i < _poblacionTamano; i++)
		{
			if(rand.nextFloat() < _cruceProb)
			{
				aCruzar[numeroSeleccionados] = i;
				numeroSeleccionados++;
			}
		}
		//Hacerlos pares
		if(numeroSeleccionados % 2 == 1)
		{
			numeroSeleccionados--;
		}
		
		//Cruzarlos
		for(int i = 0; i < (numeroSeleccionados / 2); i++)
		{
			Cromosoma hijo1 = new Problema1(_precision);
			Cromosoma hijo2 = new Problema1(_precision);
			Cromosoma.cruzar(_poblacion[aCruzar[i]], _poblacion[aCruzar[i + 1]], hijo1, hijo2);
			
			//Sustituir a los padres
			_poblacion[aCruzar[i]] = hijo1;
			_poblacion[aCruzar[i + 1]] = hijo2;
		}
	}
	
	private void mutar()
	{
		for(int i = 0; i < _poblacionTamano; i++)
		{
				_poblacion[i].mutar(_mutacionProb);
		}
	}
}
