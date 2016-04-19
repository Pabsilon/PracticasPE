package implementation;

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

}
