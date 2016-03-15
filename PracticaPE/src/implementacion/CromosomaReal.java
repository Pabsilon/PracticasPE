package implementacion;

import java.util.Random;

public abstract class CromosomaReal extends Cromosoma{
	
	//TODO EVERYTHING
	
	protected float[] _genes;
	protected int _longitud[];
	protected boolean _maximize;
	
	/**Getter del fenotipo.
	 * @return La reprensentacion del gen.
	 */
	protected abstract float[] getFenotipo();
	
	/**Getter de la actitud.
	 * @return El valor de la funcion en el punto dado.
	 */
	protected abstract float getAptitud();
	
	/** Cruza dos cromosomas.
	 * 
	 * @param padre1 El primer padre a cruzar
	 * @param padre2 El segundo padre a cruzar
	 * @param hijo1 El primer hijo generado
	 * @param hijo2 El segundo hijo generado
	 * @param rand La función random a utilizar (se pasa por uso de semillas)
	 */
	public static void cruzar (CromosomaReal padre1, CromosomaReal padre2, CromosomaReal hijo1, CromosomaReal hijo2, Random rand){
		
	}
	
	/**Método que muta a la poblacion. Recorre todos los genes y decide si alterarlos.
	 * 
	 * @param mutacionProb Probabilidad de que mute un gen
	 * @param rand Función de random a utilizar (se pasa por uso de semillas)
	 */
	public void mutar(float mutacionProb, Random rand){
		
	}
	
	
	/**Método para saber si el problema en el que trabajamos es de maximización o no.
	 * @return True si es un problema de maximización, false si es un problema de minimización.
	 */
	public abstract boolean isMaximizing();
	

}
