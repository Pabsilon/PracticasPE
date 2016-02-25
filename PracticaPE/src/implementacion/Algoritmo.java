package implementacion;

import java.util.Random;

import especificos.Problema1;

public abstract class Algoritmo {
	//Parte genetica
	protected int _poblacionTamaño, _simulaciones;
	protected Cromosoma[] _poblacion;
	protected float _precision, _cruceProb, _mutacionProb;

	//Valores problema concreto
	protected float _mejorValor;
	protected Cromosoma _mejorIndividuo;
	
	protected Algoritmo(int poblacion, float precision, float cruce, float mutacion, int simulaciones) 
	{
		_poblacionTamaño = poblacion;
		_precision = precision;
		_cruceProb = cruce;
		_mutacionProb = mutacion;
		_simulaciones = simulaciones;
		
		_poblacion = new Cromosoma[poblacion];
		for(int i = 0; i < poblacion; i++)
		{
			//Cambiar a cada problema especifo con un factory o algo asi
			_poblacion[i] = new Problema1(precision);
		}
		
		//CAMBIAR ESTO
		_mejorIndividuo = new Problema1(precision);;
	}
	
	public String simular(double[] mejorAbsoluto, double[] mejorGeneracion, double[] mediaGeneracion)
	{
		float[] aptitudes = new float[_poblacionTamaño];
		float[] puntuaciones = new float[_poblacionTamaño];
		float[] puntuacionesAcum = new float[_poblacionTamaño];
		
		for(int i = 0; i < _simulaciones; i++)
		{
			//Evaluar
			double[] infoGeneracion = new double[2]; //0 Mejor de la generacion, 1 Media de la generacion
			evaluar(aptitudes, puntuaciones, puntuacionesAcum, infoGeneracion);
			mejorAbsoluto[i] = _mejorValor;
			mejorGeneracion[i] = infoGeneracion[0];
			mediaGeneracion[i] = infoGeneracion[1];
			
			//Seleccionar
			seleccionar(puntuacionesAcum);
			
			//Cruzar
			cruzar();
			
			//Mutar
			mutar();
		}
		
		//CAMBIAR ESTO
		String resultado = "Valor mejor: " + _mejorValor + " en X: " + _mejorIndividuo.getFenotipo()[0];
		return resultado;
	}
	
	protected abstract void evaluar(float[] aptitudes, float[] puntuaciones, float[] puntuacionesAcum, double[] infoGeneracion);
	
	private void seleccionar(float[] puntuacionesAcum)
	{
		//CAMBIAR ESTO
		//Ruleta
		Random rand = new Random();
		Cromosoma[] seleccionados = new Cromosoma[_poblacionTamaño];
		for(int i = 0; i < _poblacionTamaño; i++)
		{
			//Generar número entre 0 - 1
			float prob = rand.nextFloat();
			//Buscar el elemento seleccionado (Nota: seleccionado nunca será mayor que el tamaño de la poblacion)
			int seleccionado = 0;
			while(prob > puntuacionesAcum[seleccionado])
			{
				seleccionado++;
			}
			seleccionados[i] = _poblacion[seleccionado];
		}
		
		//Crear la nueva poblacion
		_poblacion = seleccionados;
		
	}
	
	private void cruzar()
	{
		Random rand = new Random();
		int numeroSeleccionados = 0;
		int[] aCruzar = new int[_poblacionTamaño];
		
		//Obtener los cromosomas a cruzar
		for(int i = 0; i < _poblacionTamaño; i++)
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
		for(int i = 0; i < _poblacionTamaño; i++)
		{
				_poblacion[i].mutar(_mutacionProb);
		}
	}
}
