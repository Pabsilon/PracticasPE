package amutacion;

import java.util.Random;

import implementation.Cromosoma;

/**Implementacion de la mutacion por intercambio
 * @author Pablo Mac-Veigh
 *
 */
public class AMutacion_Intercambio implements AMutacion {

	@Override
	public void mutar(Cromosoma[] poblacion, float mutacionProb, Random rand) {
		
		for (int i=0; i<poblacion.length; i++)
		{
			int alelos[] = poblacion[i].getGenotipo();
			//Recorremos todos los genes. Si mutan se intercambia el gen actual con otro aleatorio
			for (int j = 1; j<alelos.length; j++)
			{
				if (rand.nextFloat()<mutacionProb)
				{
					//Elegimos el elemento a intercambiar [1-28]
					int exchange = (int) ((alelos.length - 2) * rand.nextFloat()) + 1;
					//Lo intercambiamos
					int aux = alelos[j];
					alelos[j]=alelos[exchange];
					alelos[exchange]=aux;
				}
			}
			//cambiamos el genotipo
			poblacion[i].setGenotipo(alelos);
		}
	}

}
