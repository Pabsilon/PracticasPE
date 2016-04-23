package acruce;

import java.util.Arrays;
import java.util.Random;

import implementation.Cromosoma;
import implementation.FAuxiliares;
import implementation.SpainMap;

public class ACruce_OX_OP implements ACruce {

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
		
		//Elegimos aleatoriamente las posiciones a cambiar
		Random rand = new Random();
		boolean indices[] = new boolean[genPadre1.length];
		Arrays.fill(indices, false);
		for(int i = 1; i < genPadre1.length; i++)
		{
			//Si es menor que la probabilidad se elige el indice
			if(rand.nextFloat() < 0.25)
			{
				indices[i] = true;
			}
		}
		
		//Hijo 1
		int ciudades[] = new int[indices.length];
		Arrays.fill(ciudades, -1);
		for(int i = 1; i < genPadre1.length; i++)
		{
			//Obtener indices de las ciudades del padre 2 que se modifican
			if(indices[i])
			{
				ciudades[i] = FAuxiliares.indiceDe(genPadre2, genPadre1[i]);
			}
		}
		
		//Copiar las que se mantienen fijas
		for(int i = 1; i < genPadre1.length; i++)
		{
			if(ciudades[i] == -1)
			{
				genHijo1[i] = ciudades[i];
			}
		}
		//Copiar las que se cambiaron
		for(int i = 1; i < genPadre1.length; i++)
		{
			if(genHijo1[i] == -1)
			{
				genHijo1[i] = genPadre1[i];
			}
		}
		
		//Hijo 2
		Arrays.fill(ciudades, -1);
		for(int i = 1; i < genPadre2.length; i++)
		{
			//Obtener indices de las ciudades del padre 2 que se modifican
			if(indices[i])
			{
				ciudades[i] = FAuxiliares.indiceDe(genPadre1, genPadre2[i]);
			}
		}
		
		//Copiar las que se mantienen fijas
		for(int i = 1; i < genPadre2.length; i++)
		{
			if(ciudades[i] == -1)
			{
				genHijo2[i] = ciudades[i];
			}
		}
		//Copiar las que se cambiaron
		for(int i = 1; i < genPadre2.length; i++)
		{
			if(genHijo2[i] == -1)
			{
				genHijo2[i] = genPadre2[i];
			}
		}

		h1.setGenotipo(genHijo1);
		h2.setGenotipo(genHijo2);
	}

}
