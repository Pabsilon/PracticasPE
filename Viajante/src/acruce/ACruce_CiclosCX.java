package acruce;

import java.util.Arrays;

import implementation.Cromosoma;
import implementation.FAuxiliares;
import implementation.SpainMap;

public class ACruce_CiclosCX implements ACruce {

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
		
		//Hijo1
		int indices[] = new int[genHijo1.length]; //Array que contendra los indices del ciclo en padre1
		indices[0] = 1; //Cogemos la primera ciudad distinta de Madrid
		int siguiente = genPadre2[1]; //Miramos cual es la ciudad siguiente a buscar
		genPadre2[1] = -1; //Borramos esa ciudad del padre 2
		int i = 1;
		while(siguiente != genPadre1[1] && i < SpainMap.getNumberOfCities())
		{
			indices[i] = FAuxiliares.indiceDe(genPadre1, siguiente);

			siguiente = genPadre2[indices[i]];
			genPadre2[indices[i]] = -1;
			i++;
		}
		//Copiamos los valores del ciclo en el hijo
		for(int j = 0; j < i; j++)
		{
			genHijo1[indices[j]] = genPadre1[indices[j]];
		}
		//Copiamos el resto de ciudades
		for(int j = 0; j < genPadre1.length; j++)
		{
			if(genHijo1[j] == -1)
			{
				//Buscamos una ciudad sin asignar
				int k = 1;
				while(genPadre2[k] == -1)
				{
					k++;
				}
				
				genHijo1[j] = genPadre2[k];
				genPadre2[k] = -1;
			}
			
		}
		
		//Hijo2
		genPadre2 = p2.getGenotipo();
		indices = new int[genHijo2.length]; //Array que contendra los indices del ciclo en padre1
		indices[0] = 1; //Cogemos la primera ciudad distinta de Madrid
		siguiente = genPadre1[1]; //Miramos cual es la ciudad siguiente a buscar
		genPadre1[1] = -1; //Borramos esa ciudad del padre 2
		i = 1;
		while(siguiente != genPadre2[1] && i < SpainMap.getNumberOfCities())
		{
			indices[i] = FAuxiliares.indiceDe(genPadre2, siguiente);

			siguiente = genPadre1[indices[i]];
			genPadre1[indices[i]] = -1;
			i++;
		}
		//Copiamos los valores del ciclo en el hijo
		for(int j = 0; j < i; j++)
		{
			genHijo2[indices[j]] = genPadre2[indices[j]];
		}
		//Copiamos el resto de ciudades
		for(int j = 0; j < genPadre2.length; j++)
		{
			if(genHijo2[j] == -1)
			{
				//Buscamos una ciudad sin asignar
				int k = 1;
				while(genPadre1[k] == -1)
				{
					k++;
				}
				
				genHijo2[j] = genPadre1[k];
				genPadre1[k] = -1;
			}
			
		}
		
		h1.setGenotipo(genHijo1);
		h2.setGenotipo(genHijo2);	
	}

}
