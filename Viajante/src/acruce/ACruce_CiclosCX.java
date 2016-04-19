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
		//Creamos el ciclo
		int ciclo[] = new int[genHijo1.length];
		Arrays.fill(ciclo, -1);
		int indices[] = new int[genHijo1.length];
		//Madrid es fija, cogemos la siguiente como inicio del ciclo
		ciclo[0] = genPadre1[1];
		indices[0] = 1;
		int siguiente = genPadre2[1];
		genPadre2[0] = -1;
		genPadre2[1] = -1; //Borramos el cogido del padre
		int i = 1;
		while(i < SpainMap.getNumberOfCities())
		{
			//Cogemos el indicie del numero a buscar en padre1
			int indx = FAuxiliares.indiceDe(genPadre1, siguiente);
			if(FAuxiliares.numeroEnArray(ciclo, siguiente))
			{
				//Si ya existe (ciclo cerrado) salimos
				break;
			}
			ciclo[i] = genPadre1[indx]; //Guardar ciudad
			indices[i] = indx;
			siguiente = genPadre2[indx]; //Siguiente ciudad cogida del padre 2
			genPadre2[indx] = -1;
			i++;
		}
		//Rellenamos el hijo con los valores del ciclo
		for(int j = 0; j < i; j++)
		{
			genHijo1[indices[j]] = ciclo[j];
		}
		//Copiamos el resto
		for(i = 0; i < genHijo1.length; i++)
		{
			if(genPadre2[i] != -1)
			{
				genHijo1[i] = genPadre2[i];
			}
		}
		
		//Hijo2
		genPadre2 = p2.getGenotipo();
		//Creamos el ciclo
		Arrays.fill(ciclo, -1);
		ciclo[0] = genPadre2[1];
		indices[0] = 1;
		siguiente = genPadre1[1];
		genPadre1[0] = -1;
		genPadre1[1] = -1; //Borramos el cogido del padre
		i = 1;
		while(i < SpainMap.getNumberOfCities())
		{
			int indx = FAuxiliares.indiceDe(genPadre2, siguiente);
			if(FAuxiliares.numeroEnArray(ciclo, genPadre2[indx]))
			{
				break;
			}
			ciclo[i] = genPadre2[indx];
			indices[i] = indx;
			siguiente = genPadre1[indx];
			genPadre1[indx] = -1;
			i++;
		}
		//Rellenamos el hijo con los valores del ciclo
		for(int j = 0; j < i; j++)
		{
			genHijo2[indices[j]] = ciclo[j];
		}
		//Copiamos el resto
		for(i = 0; i < genHijo2.length; i++)
		{
			if(genPadre1[i] != -1)
			{
				genHijo2[i] = genPadre1[i];
			}
		}
		
		h1.setGenList(genHijo1);
		h2.setGenList(genHijo2);	
	}

}
