package especificos;
import implementacion.*;

public class Problema4 extends Cromosoma{

	private float _xmax = (float) Math.PI;
	private float _xmin = 0;
	private int _n;
	
	public Problema4(float precision, int n){
		_n =n;
		_longitud = new int[_n];
		
		for (int i =0; i<_n; i++){
			_longitud[i] = (int) Math.ceil(((Math.log(1+(_xmax-_xmin)/precision))/Math.log(2)));
		}
		_genes = new Gen[_n];
		for (int i = 0; i<_n; i++){
			_genes[i]=new Gen(_longitud[i]);
		}
	}
	
	protected float[] getFenotipo() {
		float[] retval = new float[_n];
		for (int i = 0; i<_n; i++){
			retval[i] = (float) (_xmin + (_xmax - _xmin) * bin_dec(_genes[i]) / (Math.pow(2, _longitud[i])-1));		
		}
		return retval;
	}

	protected float getAptitud() {
		//TODO CORRECT
		float retval = 0;
		//f(xi|i=1..n) = -Sum (from i=1 to n) sen(xi) * sen²⁰((i+1)*xi²)/π);
		for (int i = 1; i<=_n; i++){
			float x = getFenotipo()[i-1];
			retval+= -Math.sin(x)*Math.pow(20,(Math.sin((i+1) * Math.pow(2, x)/Math.PI)));
		}
		return retval;
	}

}
