package acruce;

import java.util.Arrays;
import java.util.Random;

import implementation.Cromosoma;
import implementation.FAuxiliares;
import implementation.Fabrica_ACruce;
import implementation.SpainMap;

public class ACruce_PMX implements ACruce {

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
		
		//Elegimos dos puntos de corte
		Random rand = new Random();
		int c1 = rand.nextInt(genPadre1.length);
		int c2 = rand.nextInt(genPadre1.length);
		
		if((c1 == 0) && (c2 == genPadre1.length) || (c1 == c2))
		{
			h1.setGenotipo(genPadre1);
			h2.setGenotipo(genPadre2);
			return;
		}		
		if(c1 > c2)
		{
			int tmp = c1;
			c1 = c2;
			c2 = tmp;
		}
		
		//Copiamos los genes que no copian
		for(int i = c1; i < c2; i++)
		{
			genHijo1[i] = genPadre2[i];
			genHijo2[i] = genPadre1[i];
		}
		
		//Intercambiamos genes
		//Parte derecha
		for(int i = c2; i < genHijo1.length; i++)
		{
			//Hijo 1
			int ciudadP1 = genPadre1[i];
			if(!FAuxiliares.numeroEnArray(genHijo1, ciudadP1))
			{
				genHijo1[i] = ciudadP1;
			}
			else //Si el numero ya esta en el array
			{
				int indx = FAuxiliares.indiceDe(genHijo1, ciudadP1);
				int num = genHijo2[indx];
				while(FAuxiliares.numeroEnArray(genHijo1, num)) //Si sigue estando, ir haciendo transitividad
				{
					indx = FAuxiliares.indiceDe(genHijo1, num);
					num = genHijo2[indx];
				}
				
				genHijo1[i] = num;
			}
			
			//Hijo2
			int ciudadP2 = genPadre2[i];
			if(!FAuxiliares.numeroEnArray(genHijo2, ciudadP2))
			{
				genHijo2[i] = ciudadP2;
			}
			else //Si el numero ya esta en el array
			{
				int indx = FAuxiliares.indiceDe(genHijo2, ciudadP2);
				int num = genHijo1[indx];
				while(FAuxiliares.numeroEnArray(genHijo2, num))
				{
					indx = FAuxiliares.indiceDe(genHijo2, num);
					num = genHijo1[indx];
				}
				
				genHijo2[i] = num;
			}
		}
		
		//Parte izquierda
		for(int i = 1; i < c1; i++)
		{
			//Hijo 1
			int ciudadP1 = genPadre1[i];
			if(!FAuxiliares.numeroEnArray(genHijo1, ciudadP1))
			{
				genHijo1[i] = ciudadP1;
			}
			else //Si el numero ya esta en el array
			{
				int indx = FAuxiliares.indiceDe(genHijo1, ciudadP1);
				int num = genHijo2[indx];
				while(FAuxiliares.numeroEnArray(genHijo1, num))
				{
					indx = FAuxiliares.indiceDe(genHijo1, num);
					num = genHijo2[indx];
				}
				
				genHijo1[i] = num;
			}
			
			//Hijo2
			int ciudadP2 = genPadre2[i];
			if(!FAuxiliares.numeroEnArray(genHijo2, ciudadP2))
			{
				genHijo2[i] = ciudadP2;
			}
			else //Si el numero ya esta en el array
			{
				int indx = FAuxiliares.indiceDe(genHijo2, ciudadP2);
				int num = genHijo1[indx];
				while(FAuxiliares.numeroEnArray(genHijo2, num))
				{
					indx = FAuxiliares.indiceDe(genHijo2, num);
					num = genHijo1[indx];
				}
				
				genHijo2[i] = num;
			}
		}
		
		h1.setGenotipo(genHijo1);
		h2.setGenotipo(genHijo2);		
	}

}
