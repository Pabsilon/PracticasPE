package amutacion;

import java.util.Random;

import implementacion.CArbol;
import implementacion.Hormiga;

public class AMutacion_Arbol implements AMutacion {

	@Override
	public void mutar(Hormiga individuo)
	{
		CArbol raiz = individuo.getCromosoma();

		//Elegir un subarbol aleatoriamente y mutarlo
		Random rand = new Random();
		CArbol aMutar = raiz.getSubarbol(rand.nextInt(raiz.getNumeroNodos()));
		
		aMutar.copiarArbol(new CArbol()); //TODO generar un arbol completo, no solo un nodo
	}

}
