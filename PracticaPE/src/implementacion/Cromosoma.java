package implementacion;

public abstract class Cromosoma { //Clase que implementa al individuo

	protected Gen[] _genes;
	protected float _fenotipo;
	protected float _aptitud;
	protected float _puntuacion;
	protected float _punt_acum;	
	protected int _longitud;
	
	public static int bin_dec(Gen gen){
		int retval = 0;
		for (int i=0; i<gen.getAlelos().length; i++){
			if (gen.getAlelos()[i]){
				retval = (int) (retval + Math.pow(2,i));
			}
		}
		
		
		return retval;
	}
	
	protected abstract float getFenotipo(); // Devuelve la representacion del gen
	
	protected abstract float getAptitud(); // Devuelve el valor de la funcion
	
}
