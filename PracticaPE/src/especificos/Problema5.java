package especificos;
import implementacion.*;

public class Problema5 extends Cromosoma{

	//x1 y x2 son tratados como x e y
	private final float _xmin = -10;
	private final float _xmax = 10f;
	private final float _ymin = -10;
	private final float _ymax = 10;
	
	public Problema5(float precision){
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
		float aux1 = 0;
		float aux2 = 0;
		for (int i = 1; i<=5; i++){
			aux1 += i*Math.cos((i+1)*x+1);
		}
		for (int i = 1; i<=5; i++){
			aux2+= i*Math.cos((i+1)*y+1);
		}
		return aux1*aux2;
	}

}
