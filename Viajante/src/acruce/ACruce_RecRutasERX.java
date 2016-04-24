package acruce;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import implementation.Cromosoma;
import implementation.FAuxiliares;
import implementation.SpainMap;

/**Implementacion del cruce por recombinacion de rutas ERX
 * @author Jorge Sanchez
 *
 */
public class ACruce_RecRutasERX implements ACruce {

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
		
		//Construir tabla de conectividades
		@SuppressWarnings("unchecked")
		HashSet<Integer> tablaConectividades[] = new HashSet[SpainMap.getNumberOfCities()];
		for(int i = 0; i < SpainMap.getNumberOfCities(); i++)
		{
			tablaConectividades[i] = new HashSet<Integer>();
		}
		for(int i = 0; i < SpainMap.getNumberOfCities(); i++)
		{
			//Coger conexiones
			int conexion[] = new int[4]; //Maximo 4 conexiones
			if(i == 0) //Madrid
			{
				conexion[0] = genPadre1[genPadre1.length - 1]; //Ciudad izquierda
				conexion[1] = genPadre1[i+1]; //Ciudad derecha
				conexion[2] = genPadre2[genPadre2.length - 1]; //Ciudad izquierda
				conexion[3] = genPadre2[i+1]; //Ciudad derecha
			}
			else if(i == genPadre1.length - 1)//Ultima ciudad
			{
				conexion[0] = genPadre1[genPadre1.length - 2]; //Ciudad izquierda
				conexion[1] = genPadre1[0]; //Ciudad derecha
				int ciudad = genPadre1[i];
				int indx = FAuxiliares.indiceDe(genPadre2, ciudad);
				if(indx == genPadre1.length - 1) //Si tambien es la ultima ciudad
				{
					conexion[2] = genPadre2[genPadre2.length - 2]; //Ciudad izquierda
					conexion[3] = genPadre2[0];
				}
				else
				{
					conexion[2] = genPadre2[indx - 1];
					conexion[3] = genPadre2[indx + 1];
				}
			}
			else
			{
				conexion[0] = genPadre1[i - 1]; //Ciudad izquierda
				conexion[1] = genPadre1[i + 1]; //Ciudad derecha
				int ciudad = genPadre1[i];
				int indx = FAuxiliares.indiceDe(genPadre2, ciudad);
				if(indx == genPadre1.length - 1) //Si es la ultima ciudad
				{
					conexion[2] = genPadre2[genPadre2.length - 2]; //Ciudad izquierda
					conexion[3] = genPadre2[0];
				}
				else
				{
					conexion[2] = genPadre2[indx - 1];
					conexion[3] = genPadre2[indx + 1];
				}
			}
			
			for(int j = 0; j < 4; j++)
			{
				//Introducir en la tabla si no esta repedita
				if(!tablaConectividades[i].contains(conexion[j]))
				{
					tablaConectividades[i].add(conexion[j]);
				}
			}
		}
		
		//Hijo1
		boolean bloqueo = false;
		Random rand = new Random();
		genHijo1[0] = genPadre1[0];
		for(int i = 1; i < genPadre1.length; i++)
		{
			int ciudad = -1;
			int size = 4;
			for(int j : tablaConectividades[i])
			{
				int aux = tablaConectividades[j].size();
				if(aux < size)
				{
					size = aux;
					ciudad = j;
				}
			}
			
			if(size == 4)
			{
				int limit = 0;
				while(true)
				{
					int aux = rand.nextInt(4);
					int elem = -1;
					Iterator<Integer> it = tablaConectividades[i].iterator();
					for(int k = 0; (k < aux) && (k < tablaConectividades[i].size()); k++, elem = it.next());
					aux = it.hasNext() ? it.next() : elem;
					if(!FAuxiliares.numeroEnArray(genHijo1, aux))
					{
						genHijo1[i] = aux;
						break;
					}
					limit++;
					if(limit == 4) //Hemos llegado a un bloqueo, empezamos de nuevo.
					{
						bloqueo = true;
						break;
					}
				}
			}
			
			//Si hay un bloqueo clonamos del padre
			if(bloqueo)
			{
				genHijo1 = genPadre1.clone();
				break;
			}
			
			if(!FAuxiliares.numeroEnArray(genHijo1, ciudad))
				genHijo1[i] = ciudad;
			else
			{
				//Si ya esta elegida se coge una aleatoria
				int limit = 0;
				while(true)
				{
					int aux = rand.nextInt(4);
					int elem = -1;
					Iterator<Integer> it = tablaConectividades[i].iterator();
					for(int k = 0; (k < aux) && (k < tablaConectividades[i].size()); k++, elem = it.next());
					aux = it.hasNext() ? it.next() : elem;
					if(!FAuxiliares.numeroEnArray(genHijo1, aux))
					{
						genHijo1[i] = aux;
						break;
					}
					limit++;
					if(limit == 4) //Hemos llegado a un bloqueo, empezamos de nuevo.
					{
						bloqueo = true;
						break;
					}
				}
			}
			
			if(bloqueo)
			{
				genHijo1 = genPadre1.clone();
				break;
			}
		}
		
		//Hijo2
		bloqueo = false;
		genHijo2[0] = genPadre2[0];
		for(int i = 1; i < genPadre2.length; i++)
		{
			int ciudad = -1;
			int size = 4;
			for(int j : tablaConectividades[i])
			{
				int aux = tablaConectividades[j].size();
				if(aux < size)
				{
					size = aux;
					ciudad = j;
				}
			}
			
			if(size == 4)
			{
				int limit = 0;
				while(true)
				{
					int aux = rand.nextInt(4);
					int elem = -1;
					Iterator<Integer> it = tablaConectividades[i].iterator();
					for(int k = 0; (k < aux) && (k < tablaConectividades[i].size()); k++, elem = it.next());
					aux = it.hasNext() ? it.next() : elem;
					if(!FAuxiliares.numeroEnArray(genHijo2, aux))
					{
						genHijo2[i] = aux;
						break;
					}
					limit++;
					if(limit == 4) //Hemos llegado a un bloqueo, empezamos de nuevo.
					{
						bloqueo = true;
						break;
					}
				}
			}
			
			if(bloqueo)
			{
				genHijo2 = genPadre2.clone();
				break;
			}
			
			if(!FAuxiliares.numeroEnArray(genHijo2, ciudad))
				genHijo2[i] = ciudad;
			else
			{
				//Si ya esta elegida se coge una aleatoria
				int limit = 0;
				while(true)
				{
					int aux = rand.nextInt(4);
					int elem = -1;
					Iterator<Integer> it = tablaConectividades[i].iterator();
					for(int k = 0; (k < aux) && (k < tablaConectividades[i].size()); k++, elem = it.next());
					aux = it.hasNext() ? it.next() : elem;
					if(!FAuxiliares.numeroEnArray(genHijo2, aux))
					{
						genHijo2[i] = aux;
						break;
					}
					limit++;
					if(limit == 4) //Hemos llegado a un bloqueo, empezamos de nuevo.
					{
						bloqueo = true;
						break;
					}
				}
			}
			
			if(bloqueo)
			{
				genHijo2 = genPadre2.clone();
				break;
			}
		}
		
		h1.setGenotipo(genHijo1);
		h2.setGenotipo(genHijo2);
		
	}

}
