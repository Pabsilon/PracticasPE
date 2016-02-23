package especificos;
import implementacion.*;

public class Problema1 extends Cromosoma{
	
	private final float _xmin = -250;
	private final float _xmax = 250;
;
	public Problema1(float precision){
		_longitud = (int) Math.ceil(((Math.log(1+(_xmax-_xmin)/precision))/Math.log(2)));
		_genes = new Gen[1];
		_genes[0]=new Gen(_longitud);	
	}
	
	public float getFenotipo(){
		return (float) (_xmin + (_xmax - _xmin) * bin_dec(_genes[0]) / (Math.pow(2, _longitud)-1));
	}

	protected float getAptitud() {
		float x = getFenotipo();
		//f(x) = -|x*sin(sqrt(|x|)|;
		return (float) -Math.abs(x*Math.sin(Math.sqrt(Math.abs(x))));
	}
	
	

}
