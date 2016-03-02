package especificos;

import java.util.Random;

import implementacion.*;

public class Problema2 extends Cromosoma{
	
	private final float _xmin = -6;
	private final float _xmax = 6;
	private final float _ymin = -6;
	private final float _ymax = 6;
	
	public Problema2 (float precision, Random rand){
		_longitud = new int[2]; //Dos longitudes de gen - aunque sea la misma.
		//Cálculo de bits necesarios
		_longitud[0] = (int) Math.ceil(((Math.log(1+(_xmax-_xmin)/precision))/Math.log(2)));
		_longitud[1] = (int) Math.ceil(((Math.log(1+(_ymax-_ymin)/precision))/Math.log(2)));
		_genes = new Gen[2];
		_genes[0]=new Gen(_longitud[0], rand);
		_genes[1]=new Gen(_longitud[1], rand);
		
		_maximize = true;
	}
	
	protected float[] getFenotipo() {
		float[] retval = new float[8];
		//Conversión a fenotipo.
		retval[0] = (float) (_xmin + (_xmax - _xmin) * bin_dec(_genes[0]) / (Math.pow(2, _longitud[0])-1));
		retval[1] = (float) (_ymin + (_ymax - _ymin) * bin_dec(_genes[1]) / (Math.pow(2, _longitud[1])-1));
		return retval;
	}
	@Override
	protected float getAptitud() {
		float x = getFenotipo()[0];
		float y = getFenotipo()[1];
		//f(x,y) = (2186-(x²+y-11)² -(x+y²-7)²)/2186		
		return (float) ((2186 -Math.pow(2,(Math.pow(2, x) + y -11)) -Math.pow(2,(x + Math.pow(2, y) -7))) /2186);
	}
	
	public boolean isMaximizing() {
		return _maximize;
	}

	@Override
	public String toString() 
	{
		String resultado = "Valor mejor: " + getAptitud() + " en X: " + getFenotipo()[0] + " Y: " + getFenotipo()[1];
		return resultado;
	}
}