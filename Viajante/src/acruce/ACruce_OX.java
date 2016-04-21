package acruce;

import java.util.Arrays;
import java.util.Random;

import implementation.Cromosoma;
import implementation.FAuxiliares;
import implementation.SpainMap;

/** Implementación del cruce OX
 * @author Jorge Sanchez
 *
 */
public class ACruce_OX implements ACruce {

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
		
		//Elegimos aleatoriamente dos puntos de corte c1, c2. Donde c1 < c2
		Random rand = new Random();
		int c1 = rand.nextInt(genPadre1.length); //Entre [0 y length-1]
		int c2 = rand.nextInt((genPadre1.length - c1) + 1) + c1; //Entre [c1, length]
		
		if(c1 == c2)
			return;
		if(c1 == 0 && c2 == genPadre1.length)
			return;
		
		//Hijo1
		//Copiar valores entre c1 y c2
		for(int i = c1; i < c2; i++)
		{
			genHijo1[i] = genPadre1[i];
		}
		//Copiar valores a la derecha de c2
		int aCopiar = (genHijo1[c2 - 1] + 1) % SpainMap.getNumberOfCities();
		for(int i = c2; i < genHijo1.length; i++)
		{
			while(FAuxiliares.numeroEnArray(genHijo1, aCopiar))
			{
				aCopiar = (aCopiar + 1) % SpainMap.getNumberOfCities();
			}
			
			genHijo1[i] = aCopiar;
			aCopiar = (aCopiar + 1) % SpainMap.getNumberOfCities();
		}
		//Copiar valores a la izquierda de c1
		aCopiar = (genHijo1[genHijo1.length - 1] + 1) % SpainMap.getNumberOfCities();
		for(int i = 1; i < c1; i++)
		{
			while(FAuxiliares.numeroEnArray(genHijo1, aCopiar))
			{
				aCopiar = (aCopiar + 1) % SpainMap.getNumberOfCities();
			}
			
			genHijo1[i] = aCopiar;
			
			aCopiar = (aCopiar + 1) % SpainMap.getNumberOfCities();
		}
		
		//Hijo2
		//Copiar valores entre c1 y c2
		for(int i = c1; i < c2; i++)
		{
			genHijo2[i] = genPadre2[i];
		}
		//Copiar valores a la derecha de c2
		aCopiar = (genHijo2[c2 - 1] + 1) % SpainMap.getNumberOfCities();
		for(int i = c2; i < genHijo2.length; i++)
		{
			while(FAuxiliares.numeroEnArray(genHijo2, aCopiar))
			{
				aCopiar = (aCopiar + 1) % SpainMap.getNumberOfCities();
			}
			
			genHijo2[i] = aCopiar;
			aCopiar = (aCopiar + 1) % SpainMap.getNumberOfCities();
		}
		//Copiar valores a la izquierda de c1
		aCopiar = (genHijo2[genHijo2.length - 1] + 1) % SpainMap.getNumberOfCities();
		for(int i = 1; i < c1; i++)
		{
			while(FAuxiliares.numeroEnArray(genHijo2, aCopiar))
			{
				aCopiar = (aCopiar + 1) % SpainMap.getNumberOfCities();
			}
			
			genHijo2[i] = aCopiar;
			
			aCopiar = (aCopiar + 1) % SpainMap.getNumberOfCities();
		}
		
		
		h1.setGenotipo(genHijo1);
		h2.setGenotipo(genHijo2);
		
	}

}
