package acruce;

import java.util.Random;

import implementacion.CArbol;
import implementacion.Hormiga;

public class ACruce_Intercambio implements ACruce {

	@Override
	public void cruzar(Hormiga padre1, Hormiga padre2, Hormiga hijo1,
			Hormiga hijo2)
	{
		CArbol Arbolp1 = padre1.getCromosoma();
		CArbol Arbolp2 = padre2.getCromosoma();
		CArbol Arbolh1 = hijo1.getCromosoma();
		CArbol Arbolh2 = hijo2.getCromosoma();
		
		Random rand = new Random();		
		//Generamos dos puntos de corte para cada padre
		int puntoCorte1 = rand.nextInt(Arbolp1.getNumeroNodos());
		int puntoCorte2 = rand.nextInt(Arbolp2.getNumeroNodos());
		
		//Intercambiar subarboles
		CArbol subArbol1 = Arbolh1.getSubarbol(puntoCorte1);
		CArbol subArbol2 = Arbolh2.getSubarbol(puntoCorte2);
		Arbolh1.setSubarbol(puntoCorte1, subArbol2);
		Arbolh2.setSubarbol(puntoCorte2, subArbol1);
	}

}
