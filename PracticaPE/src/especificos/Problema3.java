package especificos;

import implementacion.*;

public class Problema3 extends Cromosoma{

	private final float _xmin = -3.0f;
	private final float _xmax = 12.1f;
	private final float _ymin = 4.1f;
	private final float _ymax = 5.8f;
	
	public Problema3(float precision){
		_longitud = new int[2];
		_longitud[0] = (int) Math.ceil(((Math.log(1+(_xmax-_xmin)/precision))/Math.log(2)));
		_longitud[1] = (int) Math.ceil(((Math.log(1+(_ymax-_ymin)/precision))/Math.log(2)));
		_genes = new Gen[2];
		_genes[0]=new Gen(_longitud[0]);
		_genes[1]=new Gen(_longitud[1]);
	}
	
	protected float[] getFenotipo() {
		float[] retval = new float[2];
		retval[0] = (float) (_xmin + (_xmax - _xmin) * bin_dec(_genes[0]) / (Math.pow(2, _longitud[0])-1));
		retval[1] = (float) (_ymin + (_ymax - _ymin) * bin_dec(_genes[1]) / (Math.pow(2, _longitud[1])-1));
		return retval;
	}

	protected float getAptitud() {
		float x = getFenotipo()[0];
		float y = getFenotipo()[1];
		//f(x,y) = 21.5 + x*sen(4π*x) + y*sen(20π*y)
		return (float) (21.5 + x * Math.sin(4*Math.PI*x) + y * Math.sin(20*Math.PI*y));
	}

}