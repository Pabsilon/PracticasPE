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
	
	@Override
	public int getLongitudTotal() {
		return _genes.length;
	}
	
	@Override
	public void copiarCromosoma(Cromosoma cromosoma) {
		CromosomaReal cromReal = (CromosomaReal)cromosoma;
		if(_genes.length == cromReal._genes.length)
		{
			for(int i = 0; i < _genes.length; i++)
			{
				_genes[i] = cromReal._genes[i];
			}
		}
	}
	
	
	/** Cruza dos cromosomas. Cruce de punto
	 * 
	 * @param padreReal1 El primer padre a cruzar
	 * @param padreReal2 El segundo padre a cruzar
	 * @param hijoReal1 El primer hijo generado
	 * @param hijoReal2 El segundo hijo generado
	 * @param rand La función random a utilizar (se pasa por uso de semillas)
	 */
	public static void cruzarReal_externo(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2, Random rand){
		CromosomaReal padreReal1 = (CromosomaReal)padre1;
		CromosomaReal padreReal2 = (CromosomaReal)padre2;
		CromosomaReal hijoReal1 = (CromosomaReal)hijo1;
		CromosomaReal hijoReal2 = (CromosomaReal)hijo2;
		//Cruce externo
		for(int i = 0; i < hijoReal1._genes.length; i++)
		{
			hijoReal1._genes[i] = padreReal1._genes[i];
			hijoReal2._genes[i] = padreReal2._genes[i];
		}
		
		int puntoDeCorte;
		if(padreReal1.getLongitudTotal() == 1)
		{
			puntoDeCorte = 0;
		}
		else
		{
			puntoDeCorte = rand.nextInt(padreReal1.getLongitudTotal() - 1) + 1; //Genera un numero (0, longitudCromosoma)
		}
		
		//Hijo1
		//Copiar resto de genes al completo
		for(int i = puntoDeCorte; i < padreReal2._genes.length; i++)
		{
			hijoReal1._genes[i] = padreReal2._genes[i];
		}
		
		//Hijo2
		//Copiar resto de genes al completo
		for(int i = puntoDeCorte; i < padreReal1._genes.length; i++)
		{
			hijoReal2._genes[i] = padreReal1._genes[i];
		}
	}
	
	public static void cruzarReal_discretoUniforme(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2, Random rand)
	{
		CromosomaReal padreReal1 = (CromosomaReal)padre1;
		CromosomaReal padreReal2 = (CromosomaReal)padre2;
		CromosomaReal hijoReal1 = (CromosomaReal)hijo1;
		CromosomaReal hijoReal2 = (CromosomaReal)hijo2;
		//Cruce externo
		for(int i = 0; i < hijoReal1._genes.length; i++)
		{
			hijoReal1._genes[i] = padreReal1._genes[i];
			hijoReal2._genes[i] = padreReal2._genes[i];
		}
		
		//Factor de cruce por gen
		//TODO - Preguntar si Pi se genera aleatoriamento o se deja fijo. Lo dejo aleatorio porque da mejores resultados.
		float probCruceGen = rand.nextFloat() * 0.7f + 0.1f; //Numero entre [0.1, 0.8]
		//float probCruceGen = 0.4f;
		
		//Hijo1
		//Copiar resto de genes al completo
		for(int i = 0; i < padreReal2._genes.length; i++)
		{
			//Cruzar gen si la probabilidad es menor
			if(rand.nextFloat() < probCruceGen)
			{
				hijoReal1._genes[i] = padreReal2._genes[i];
			}
		}
		
		//Hijo2
		//Copiar resto de genes al completo
		for(int i = 0; i < padreReal1._genes.length; i++)
		{
			//Cruzar gen si la probabilidad es menor
			if(rand.nextFloat() < probCruceGen)
			{
				hijoReal2._genes[i] = padreReal1._genes[i];
			}
		}
	}
	
	//Cruce aritmetico
	public static void cruzarReal_aritmetico(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2, Random rand)
	{
		CromosomaReal padreReal1 = (CromosomaReal)padre1;
		CromosomaReal padreReal2 = (CromosomaReal)padre2;
		CromosomaReal hijoReal1 = (CromosomaReal)hijo1;
		CromosomaReal hijoReal2 = (CromosomaReal)hijo2;
		//Cruce externo
		for(int i = 0; i < hijoReal1._genes.length; i++)
		{
			hijoReal1._genes[i] = padreReal1._genes[i];
			hijoReal2._genes[i] = padreReal2._genes[i];
		}
		
		//Factor de cruce por gen
		float probCruceGen = rand.nextFloat() * 0.9f + 0.1f; //No es fijo (seria cruce aritmetico uniforme) porque da peores resultados. As� que generamos aleatorios entre [0.1, 1]
		
		//Hijo1 e Hijo 2
		//Copiar resto de genes al completo
		for(int i = 0; i < padreReal2._genes.length; i++)
		{
			hijoReal1._genes[i] = (padreReal2._genes[i] * probCruceGen + padreReal1._genes[i] * (1 - probCruceGen)) / 2.0f;
			hijoReal2._genes[i] = (padreReal2._genes[i] * (1 - probCruceGen) + padreReal1._genes[i] * probCruceGen) / 2.0f;
		}
	}
	
	public static void cruzarReal_SBX(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2, Random rand)
	{
		
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
				if(rand.nextFloat() < mutacionProb)
				{
					float aleatorio = rand.nextFloat() + 0.1f; //[0.1, 1.1]
					_genes[i] = (_genes[i] + aleatorio) % (float)Math.PI; //Trampa: Sabemos que el dominio de los genes del problame 4 (cromosoma real) es PI
				}
			}
	}
	
	
	/**Método para saber si el problema en el que trabajamos es de maximización o no.
	 * @return True si es un problema de maximización, false si es un problema de minimización.
	 */
	public abstract boolean isMaximizing();
	

}
