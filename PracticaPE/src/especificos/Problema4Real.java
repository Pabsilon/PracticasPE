package especificos;

import java.util.Random;

import implementacion.CromosomaReal;

public class Problema4Real extends CromosomaReal{
	
	private float _xmax = (float) Math.PI;
	private float _xmin = 0;
	private int _n;
	
	public Problema4Real(float precision, int n, Random rand){
		_n = n;
		_genes = new float[_n];
		for (int i = 0; i<_n; i++){
			_genes[i]=rand.nextFloat()* (_xmax - _xmin) + _xmin;
		}
		_maximize = false;
		
	}

	protected float getFenotipo(int i) {
		return _genes[i];
	}
	
	protected float[] getFenotipo() {
		return _genes;
	}

	@Override
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

	@Override
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
