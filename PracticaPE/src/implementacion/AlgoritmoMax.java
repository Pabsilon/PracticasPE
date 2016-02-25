package implementacion;

public class AlgoritmoMax extends Algoritmo {

	public AlgoritmoMax(int poblacion, float precision, float cruce, float mutacion, String metodoSelec, int simulaciones) {
		super(poblacion, precision, cruce, mutacion, metodoSelec, simulaciones);
		
		_mejorValor = Float.MIN_VALUE;
	}

	@Override
	protected void evaluar(float[] aptitudes, float[] puntuaciones, float[] puntuacionesAcum, double[] infoGeneracion)
	{
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
	}

}
