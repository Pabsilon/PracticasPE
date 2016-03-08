package implementacion;

import java.util.Random;

/**Clase que implementa los genes de cada individuo.
 * @author pabs
 *
 */
public class Gen {
	
	//Un gen está compuesto de varios alelos.
	private boolean[] _alelo;
	
	/**Constructora del gen.
	 * @param n El número de alelos.
	 * @param rand El objeto random utilizado.
	 */
	public Gen(int n, Random rand){
		_alelo = new boolean[n];
		for (int i = 0; i<n; i++){
			_alelo[i]=rand.nextBoolean();
		}
	}

	/**Getter (clone) de los alelos del gen.
	 * @return Una copia de los alelos del gen.
	 */
	public boolean[] getAlelosCopy(){
		return _alelo.clone();
	}
	
	/**Getter de los alelos del gen.
	 * @return Los alelos del gen.
	 */
	public boolean[] getAlelos(){
		return _alelo;
	}
	/**Setter de los alelos.
	 * @param alelo un array de booleanos a los que vamos a setear.
	 */
	public void setAlelos(boolean[] alelo){
		_alelo = alelo.clone();
	}
	
}
