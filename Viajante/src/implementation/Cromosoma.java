package implementation;

import java.util.Arrays;
import java.util.Random;

/** Implementacion de Cromosoma
 * @author Jorge Sanchez
 *
 */
public class Cromosoma {
	
	private int _genList[];
	
	/**
	 * Builder por defecto con semilla aleatoria
	 */
	public Cromosoma()
	{
		this(new Random());
	}
	
	/**Builder con semilla dada
	 * @param rand El objeto random a utilizar
	 */
	public Cromosoma(Random rand)
	{
		//Generar genes. El gen 0 (Madrid) es fijo
		_genList = new int[SpainMap.getNumberOfCities()];
		_genList[0] = SpainMap.getCityID("Madrid");
		
		boolean ciudadesGeneradas[] = new boolean[SpainMap.getNumberOfCities()]; //True si esa ciudad ha sido generada
		Arrays.fill(ciudadesGeneradas, false);
		ciudadesGeneradas[_genList[0]] = true; //Madrid ya esta cogida
		for(int i = 1; i < _genList.length; i++)
		{
			//Coger una ciudad libre
			int randNumber = Math.abs(rand.nextInt() % _genList.length);
			while(ciudadesGeneradas[randNumber])
			{
				randNumber = Math.abs(rand.nextInt() % _genList.length);
			}
			
			_genList[i] = randNumber;
			ciudadesGeneradas[randNumber] = true;
		}
	}
	
	/** Devuelve el fenotimo
	 * @return Un string con las ciudades recorridas.
	 */
	public String getFenotipo()
	{
		String toRet = "Madrid";
		
		for(int i = 1; i < _genList.length; i++)
		{
			toRet = toRet + ", " + SpainMap.getCityName(_genList[i]);
		}
		
		return toRet;
	}
	
	/**Getter de genotipo
	 * @return un array con el genotipo
	 */
	public int[] getGenotipo()
	{
		return _genList.clone();
	}
	
	/**Setter del genotipo
	 * @param un array con el genotipo
	 */
	public void setGenotipo(int[] genes){
		_genList = genes.clone();
	}

	//TODO Se podria guardar una variable con la aptitudes una vez calculada. Así solo se calcula una vez
	/**Calcula la distancia del recorrido del cromosoma actual.
	 * @return
	 */
	public int getAptitud()
	{
		int sumaDistancias = 0;
		for(int i = 1; i < _genList.length; i++)
		{
			sumaDistancias += SpainMap.getDistance(_genList[i-1], _genList[i]);
		}
		sumaDistancias += SpainMap.getDistance(_genList[_genList.length - 1], _genList[0]); //Distancia de la ultima a Madrid
		
		return sumaDistancias;
	}
	
	/**Metodo para copiar un cromosoma sin problemas de 'punteros'
	 * @param src
	 */
	public void copiarCromosoma(final Cromosoma src)
	{
		System.arraycopy(src._genList, 0, _genList, 0, _genList.length);
	}

}
