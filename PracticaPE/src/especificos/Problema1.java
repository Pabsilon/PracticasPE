package especificos;
import java.util.Random;

import implementacion.*;

public class Problema1 extends Cromosoma{
	
	private final float _xmin = -250;
	private final float _xmax = 250;

	public Problema1(float precision, Random rand){
		_longitud = new int[1];
		_longitud[0] = (int) Math.ceil(((Math.log(1+(_xmax-_xmin)/precision))/Math.log(2)));
		_genes = new Gen[1];
		_genes[0]=new Gen(_longitud[0], rand);	
		
		_maximize = false;
	}
	
	public float[] getFenotipo(){
		float[] retval = new float[8];
		retval[0] = (float) (_xmin + (_xmax - _xmin) * bin_dec(_genes[0]) / (Math.pow(2, _longitud[0])-1));
		retval[1]=new Float("0");
		return retval;
	}

	public float getAptitud() {
		float x = getFenotipo()[0];
		//f(x) = -|x*sin(sqrt(|x|)|;
		return (float) -Math.abs(x*Math.sin(Math.sqrt(Math.abs(x))));
	}

	public boolean isMaximizing() {
		return _maximize;
	}

	
	@Override
	public String toString() 
	{
		String resultado = "Valor mejor: " + getAptitud() + " en X: " + getFenotipo()[0];
		return resultado;
	}

}
