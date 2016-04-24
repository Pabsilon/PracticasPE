package acruce;

import java.util.Arrays;
import java.util.Random;

import implementation.Cromosoma;
import implementation.FAuxiliares;
import implementation.SpainMap;

/** Implementacion de un cruce propio.
 * @author Jorge Sanchez
 *
 */

//Consiste en meter las ciudades de ambos padres en un array de longitud (longitudGenes * 2) cogiendo de forma aleatoria un ciudad del padre 1 o 2
//La siguiente vez que se coja una ciudad de ese padre sera la siguiente cogida a la anterior.
//Para generar el hijo 1 se cogen las ciudades del array en orden (si ya no están en el hijo)
//Las restantes se introducen en el hijo 2
public class ACruce_Propio implements ACruce {

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
		
		//Ir cogiendo ciudades de cada padre en orden aleatorio sequencialmente
		int ciudades[] = new int[genPadre1.length * 2 - 2];
		Random rand = new Random();
		int ind1 = 1, ind2 = 1, k = 0;
		while((ind1 < genPadre1.length) && (ind2 < genPadre2.length))
		{
			float numA = rand.nextFloat(); //Probabilidad de coger una ciudad del padre 1 o 2. si es menor que 0.5 se coge del padre 1
			if(numA < 0.5f)
			{
				ciudades[k] = genPadre1[ind1];
				ind1++;
			}
			else
			{
				ciudades[k] = genPadre2[ind2];
				ind2++;
			}
			k++;
		}
		//Meter las ciudades restantes
		while(ind1 < genPadre1.length)
		{
			ciudades[k] = genPadre1[ind1];
			ind1++;
			k++;
		}
		while(ind2 < genPadre2.length)
		{
			ciudades[k] = genPadre2[ind2];
			ind2++;
			k++;
		}
		
		//Hijo1
		for(int i = 0; i < genHijo1.length - 1;  i++)
		{
			int ciudad = ciudades[i];
			int aux = i;
			while((ciudad == -1) || (FAuxiliares.numeroEnArray(genHijo1, ciudad)))
			{
				aux++;
				ciudad = ciudades[aux];
			}
			
			genHijo1[i + 1] = ciudades[aux];
			ciudades[aux] = -1;
		}
		
		//Hijo 2
		for(int i = 0; i < genHijo2.length - 1;  i++)
		{
			int ciudad = ciudades[i];
			int aux = i;
			while((ciudad == -1) || (FAuxiliares.numeroEnArray(genHijo2, ciudad)))
			{
				aux++;
				ciudad = ciudades[aux];
			}
			
			genHijo2[i + 1] = ciudades[aux];
			ciudades[aux] = -1;
		}
		
		h1.setGenotipo(genHijo1);
		h2.setGenotipo(genHijo2);
	}

}
