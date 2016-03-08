package especificos;
import java.util.Random;

import implementacion.*;

/**f(xi|i=1..n) = -Sum (from i=1 to n) sen(xi) * sen^20((i+1)*xi^2)/PI)
 * @author pabs
 *
 */
public class Problema4 extends Cromosoma{

	private float _xmax = (float) Math.PI;
	private float _xmin = 0;
	private int _n;
	
	
	/** Constructora del problema 4: Tiene n variables.
	 * @param precision La precisión del problema
	 * @param n El número de parametros a utlizar
	 * @param rand La funcion random a utilizar (se pasa por uso de semillas)
	 */
	public Problema4(float precision, int n, Random rand){
		_n = n;
				
		_longitud = new int[_n];		
		for (int i =0; i<_n; i++){
			_longitud[i] = (int) Math.ceil(((Math.log(1+(_xmax-_xmin)/precision))/Math.log(2)));
		}
		_genes = new Gen[_n];
		for (int i = 0; i<_n; i++){
			_genes[i]=new Gen(_longitud[i], rand);
		}
		
		_maximize = false;
	}
	
	protected float[] getFenotipo() {
		float[] retval = new float[_n];
		for (int i = 0; i<_n; i++){
			retval[i] = (float) (_xmin + (_xmax - _xmin) * bin_dec(_genes[i]) / (Math.pow(2, _longitud[i])-1));		
		}
		return retval;
	}
	
	//Similar al anterior, pero ya devuelve un fenotipo concreto (más rapido para N llamadas a la funcion)
	private float getFenotipo(int i)
	{
		return (float) (_xmin + (_xmax - _xmin) * bin_dec(_genes[i]) / (Math.pow(2, _longitud[i])-1));
	}

	protected float getAptitud() {
		float retval = 0;
		//f(xi|i=1..n) = -Sum (from i=1 to n) sen(xi) * sen^20((i+1)*xi^2)/PI);
		for (int i = 1; i<=_n; i++){
			float x = getFenotipo(i - 1);
			retval += Math.sin(x) * Math.pow(Math.sin((i+1)*Math.pow(x, 2)/Math.PI), 20);
			//retval+= -Math.sin(x)*Math.pow(20,(Math.sin((i+1) * Math.pow(2, x)/Math.PI)));
		}
		return -retval;
	}
	
	public boolean isMaximizing() {
		return _maximize;
	}
	
	public String toString() 
	{
		String resultado = "Valor mejor: " + getAptitud();		
		for (int i=0; i<_n; i++){
			resultado = resultado + ", x" + (i + 1) +" = " + getFenotipo()[i];
		}
		
		return resultado;
	}

}
