package implementacion;

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
		_poblacion = new Hormiga[tamanoPoblacion];
		for(int i = 0; i < tamanoPoblacion; i++)
		{
			_poblacion[i] = new Hormiga();
		}
		
		_mejorIndividuo = null;
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
		for(int g = 0; g < _generacionTotales; g++)
		{
			//Evaluar
			//Seleccionar
			//Cruzar
			//Mutar
		}
		
		return _mejorIndividuo;
	}
}
