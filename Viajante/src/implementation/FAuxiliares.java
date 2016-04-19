package implementation;

import java.util.HashSet;

//Clase auxiliar para operaciones auxiliares
public class FAuxiliares 
{
	//Devuelve si un numero esta en el Array.
	public static boolean numeroEnArray(int[] A, int num)
	{
		for(int i = 0; i < A.length; i++)
		{
			if(A[i] == num)
			{
				return true;
			}
		}
		
		return false;
	}
	
	//Devuelve el indice de ese valor en el array. -1 si no se encuentra
	public static int indiceDe(int[] A, int num)
	{
		for(int i = 0; i < A.length; i++)
		{
			if(A[i] == num)
			{
				return i;
			}
		}
		
		return -1;
	}
	
	//Comprueba si un array no tiene elementos duplicados
	public static boolean cromosomaCorrecto(int[] gen)
	{
		HashSet<Integer> aux = new HashSet<Integer>();
		  for (int i : gen)
		  {
		    if (aux.contains(i)) return false;
		    aux.add(i);
		  }
		  return true;
	}

}
