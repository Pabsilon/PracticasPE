package implementation;

import java.util.Arrays;
import java.util.Random;

public class Cromosoma {
	
	private int _genList[];
	
	public Cromosoma()
	{
		this(new Random());
	}
	
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
	
	public String getFenotipo()
	{
		String toRet = "Madrid";
		
		for(int i = 1; i < _genList.length; i++)
		{
			toRet = toRet + ", " + SpainMap.getCityName(_genList[i]);
		}
		
		return toRet;
	}
	
	public int[] getGenotipo()
	{
		return _genList.clone();
	}
	
	public void setGenotipo(int[] genes){
		_genList = genes.clone();
	}

	//TODO Se podria guardar una variable con la aptitudes una vez calculada. As� solo se calcula una vez
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
	
	public void copiarCromosoma(final Cromosoma src)
	{
		System.arraycopy(src._genList, 0, _genList, 0, _genList.length);
	}

}
