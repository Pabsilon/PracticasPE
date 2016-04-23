package acruce;

import java.util.Arrays;
import java.util.Random;

import implementation.Cromosoma;
import implementation.FAuxiliares;
import implementation.SpainMap;

//Cruce por orden con posiciones prioritarias
public class ACruce_OX_PP implements ACruce {

	@Override
	public void cruzar(Cromosoma p1, Cromosoma p2, Cromosoma h1, Cromosoma h2)
	{
		int[] genPadre1 = p1.getGenotipo();
		int[] genPadre2 = p2.getGenotipo();
		int[] genHijo1 = new int[genPadre1.length];
		Arrays.fill(genHijo1, -1);
		genHijo1[0] = SpainMap.getCityID("Madrid");
		int[] genHijo2 = new int[genPadre2.length];
		Arrays.fill(genHijo2, -1);
		genHijo2[0] = SpainMap.getCityID("Madrid");
		
		//Elegimos aleatoriamente ciudades que no se cambiaran
		Random rand = new Random();
		boolean indices[][] = new boolean[2][genPadre1.length];
		Arrays.fill(indices[0], false);
		Arrays.fill(indices[1], false);
		for(int i = 1; i < genPadre1.length; i++)
		{
			//Si es menor que la probabilidad de no ser cambiado se guarda el indice;
			if(rand.nextFloat() < 0.25)
			{
				int ciudad = genPadre1[i];
				indices[0][i] = true;
				int indx2 = FAuxiliares.indiceDe(genPadre2, ciudad);
				indices[1][indx2] = true;
			}
		}
		
		//Hijo1
		//Copiar valores que no se cambian
		int aCopiar = 0;
		for(int i = 1; i < genPadre1.length; i++)
		{
			if(indices[0][i])
			{
				genHijo1[i] = genPadre1[i];
				aCopiar = (genHijo1[i] + 1) % SpainMap.getNumberOfCities(); //Guardamos el ultimo elemento sin modificar para empezar a partir de el
			}
		}
		//Modificamos el resto
		for(int i = 1; i < genPadre1.length; i++)
		{
			if(genHijo1[i] == -1)
			{
				while(FAuxiliares.numeroEnArray(genHijo1, aCopiar))
				{
					aCopiar = (aCopiar + 1) % SpainMap.getNumberOfCities();
				}
				genHijo1[i] = aCopiar;
			}
		}
		
		//Hijo2
		for(int i = 1; i < genPadre2.length; i++)
		{
			if(indices[1][i])
			{
				genHijo2[i] = genPadre2[i];
				aCopiar = (genHijo2[i] + 1) % SpainMap.getNumberOfCities(); //Guardamos el ultimo elemento sin modificar para empezar a partir de el
			}
		}
		//Modificamos el resto
		for(int i = 1; i < genPadre2.length; i++)
		{
			if(genHijo2[i] == -1)
			{
				while(FAuxiliares.numeroEnArray(genHijo2, aCopiar))
				{
					aCopiar = (aCopiar + 1) % SpainMap.getNumberOfCities();
				}
				genHijo2[i] = aCopiar;
			}
		}
		
		
		h1.setGenotipo(genHijo1);
		h2.setGenotipo(genHijo2);
	}

}
