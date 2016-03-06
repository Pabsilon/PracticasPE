package implementacion;

import java.util.Random;

public abstract class Cromosoma { //Clase que implementa al individuo

	protected Gen[] _genes;
	protected int _longitud[];
	protected boolean _maximize;
	
	
	/** Devuelve el valor codificado del gen
	 * @param gen, el gen en binario
	 * @return el valir del gen
	 */
	protected static int bin_dec(Gen gen){
		int retval = 0;
		for (int i=0; i<gen.getAlelosCopy().length; i++){
			if (gen.getAlelosCopy()[i]){
				retval = (int) (retval + Math.pow(2,i));
			}
		}
		
		
		return retval;
	}
	
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
	public static void cruzar(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2, Random rand)
	{
		for(int i = 0; i < hijo1._genes.length; i++)
		{
			hijo1._genes[i].setAlelos(padre1._genes[i].getAlelos());
			hijo2._genes[i].setAlelos(padre2._genes[i].getAlelos());
		}
		
		
		int puntoDeCorte = rand.nextInt(padre1.getLongitudTotal() - 1) + 1; //Genera un numero (0, longitudCromosoma)
		
		//Obtenemos el gen donde cae el punto de corte
		int primerGen = 0;
		int longitudAcum = hijo1._longitud[0];
		while(longitudAcum < puntoDeCorte)
		{
			primerGen++;
			longitudAcum += hijo1._longitud[primerGen];
		}
		
		//Hijo1
		int aux = longitudAcum - puntoDeCorte; //Posicion (empezando por el finak) del gen en donde cae el punto de corte
		boolean[] genPadre = padre2._genes[primerGen].getAlelosCopy();
		boolean[] genHijo = hijo1._genes[primerGen].getAlelosCopy();
		for(int i = aux; i > 0; i--)
		{
			genHijo[genHijo.length - i] = genPadre[genHijo.length - i];
		}
		hijo1._genes[primerGen].setAlelos(genHijo);
		
		//Copiar resto de genes al completo
		for(int i = primerGen + 1; i < padre2._genes.length; i++)
		{
			hijo1._genes[i].setAlelos(padre2._genes[i].getAlelos());
		}
		
		//Hijo2
		genPadre = padre1._genes[primerGen].getAlelosCopy();
		genHijo = hijo2._genes[primerGen].getAlelosCopy();
		for(int i = aux; i > 0; i--)
		{
			genHijo[genHijo.length - i] = genPadre[genHijo.length - i];
		}
		hijo2._genes[primerGen].setAlelos(genHijo);
		
		//Copiar resto de genes al completo
		for(int i = primerGen + 1; i < padre1._genes.length; i++)
		{
			hijo2._genes[i].setAlelos(padre1._genes[i].getAlelos());
		}
	}
	
	/**Método que muta a la poblacion. Recorre todos los genes y decide si alterarlos.
	 * 
	 * @param mutacionProb Probabilidad de que mute un gen
	 * @param rand Función de random a utilizar (se pasa por uso de semillas)
	 */
	public void mutar(float mutacionProb, Random rand)
	{
		//Recorrer todos los alelos de los genes y mutar si procede
		for(int i = 0; i < _genes.length; i++)
		{
			boolean[] alelos = _genes[i].getAlelos();
			for(int j = 0; j < alelos.length; j++)
			{
				if(rand.nextFloat() < mutacionProb)
				{
					alelos[j] = !alelos[j];
				}
			}
		}
	}
	
	/**Getter de la longitud del cromosoma utilizado. Esta varía en función del problema y de la precisión.
	 * @return La longitud del cromosoma.
	 */
	public int getLongitudTotal()
	{
		int longitudTotal = 0;
		for(int i = 0; i < _longitud.length; i++)
		{
			longitudTotal += _longitud[i];
		}
		
		return longitudTotal;
	}

	/**Copia el genotipo de un cromosoma a otro
	 * @param cromosoma El cromosoma a copiar.
	 */
	public void copiarCromosoma(Cromosoma cromosoma)
	{
		if(_genes.length == cromosoma._genes.length)
		{
			for(int i = 0; i < _genes.length; i++)
			{
				_genes[i].setAlelos(cromosoma._genes[i].getAlelos());
			}
		}
		
	}
	
	/**Método para saber si el problema en el que trabajamos es de maximización o no.
	 * @return True si es un problema de maximización, false si es un problema de minimización.
	 */
	public abstract boolean isMaximizing();
}
