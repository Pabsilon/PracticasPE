package implementation;

import java.util.Arrays;
import java.util.Random;

public class Cromosoma {
	
	private int _genList[];
	
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

}
