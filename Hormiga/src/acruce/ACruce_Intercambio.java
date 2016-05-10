package acruce;

import java.util.Random;

import implementacion.CArbol;
import implementacion.Hormiga;

public class ACruce_Intercambio implements ACruce {

	@Override
	public void cruzar(Hormiga padre1, Hormiga padre2, Hormiga hijo1,
			Hormiga hijo2)
	{
		CArbol Arbolh1 = padre1.getCromosoma().crearCopia();
		CArbol Arbolh2 = padre2.getCromosoma().crearCopia();
		
		Random rand = new Random();		
		//Generamos dos puntos de corte para cada padre
		int puntoCorte1 = rand.nextInt(Arbolh1.getNumeroNodos());
		int puntoCorte2 = rand.nextInt(Arbolh2.getNumeroNodos());
		
		//Intercambiar subarboles
		CArbol subArbol1 = Arbolh1.getSubarbol(puntoCorte1).crearCopia();
		CArbol subArbol2 = Arbolh2.getSubarbol(puntoCorte2).crearCopia();
		Arbolh1.setSubarbol(puntoCorte1, subArbol2);
		Arbolh2.setSubarbol(puntoCorte2, subArbol1);
		
		hijo1.setCromosoma(Arbolh1);
		hijo2.setCromosoma(Arbolh2);
	}

}
