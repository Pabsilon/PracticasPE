package implementation;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**Clase auxiliar para operaciones utilizadas a menudo entre varias clases.
 * @author Jorge Sanchez, Pablo Mac-Veigh
 *
 */
public class FAuxiliares 
{
	/**Devuelve si un numero esta en el Array.
	 * @param A el array
	 * @param num el numero
	 * @return true si está, false si no está
	 */
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
	
	
	/**Inserta el elemento k en la posicion k del array array, redimensionandolo al tamaño acorde.
	 * @param array el array
	 * @param pos la posicion
	 * @param k el valor
	 * @return el array actualizado
	 */
	public static int[] insertElement(int[] array, int pos, int k) {
		final int length = array.length;
		int result[] = new int[length+1];
		if (array != null) {
			System.arraycopy(array, 0, result, 0, pos);
			result[pos]=k;
			System.arraycopy(array, pos, result, pos +1, length-pos);
		}
		return result;
	}
	
	/**Elimina el elemento element del array, redimensionandolo al tamaño acorde.
	 * @param array el array
	 * @param element el elemento
	 * @return el array actualizado
	 */
	public static int[] removeElement(int[] array, int element) {
        if (array != null) {
            final int length = array.length;
            for (int i = 0; i < length; i++) {
                if (array[i] == element) {
                    if (length == 1) {
                        return null;
                    }
                    int[] result = new int[length-1];
                    System.arraycopy(array, 0, result, 0, i);
                    System.arraycopy(array, i + 1, result, i, length - i - 1);
                    return result;
                }
            }
        }
        return array;
    }
	
	
	/**Intercambia los valos de posA y posB en el array array
	 * @param array el array
	 * @param posA primer elemento
	 * @param posB segundo elemento
	 * @return el array actualizado
	 */
	public static int[] swapElements(int[] array, int posA, int posB){
		int aux = array[posA];
		array[posA]=array[posB];
		array[posB]=aux;
		return array;
	}
	
	/** Devuelve el indice de ese valor en el array.
	 * @param A El array en el que buscar
	 * @param num el valor a buscar
	 * @return -1 si no se ha encontrado
	 */
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
	
	/**Comprueba si un array no tiene elementos duplicados
	 * @param gen el array de genes
	 * @return true si hay un elemento repetido, false si no
	 */
	public static boolean cromosomaCorrecto(int[] gen)
	{
		HashSet<Integer> aux = new HashSet<Integer>();
		  for (int i : gen)
		  {
		    if (aux.contains(i)) 
		    	return false;
		    aux.add(i);
		  }
		  return true;
	}
	
	/** Implementa una cola de prioridad
	 * @return Un heap de minimos para los cromosomas
	 */
	public static PriorityQueue<Cromosoma> getColaPrioridadMinimos()
	{
		Comparator<Cromosoma> comparador;
		comparador = new Comparator<Cromosoma>()
		{

			@Override
			public int compare(Cromosoma o1, Cromosoma o2) {
				if(o1.getAptitud() < o2.getAptitud()) return -1;
				if(o1.getAptitud() == o2.getAptitud()) return 0;
				if(o1.getAptitud() > o2.getAptitud()) return 1;
				return 0;
			}
		};
		
		PriorityQueue<Cromosoma> monticuloMinimos = new PriorityQueue<Cromosoma>(comparador);
		
		return monticuloMinimos;
	}
	

}
