package amutacion;

import java.util.Random;

import implementacion.CArbol;
import implementacion.Hormiga;

public class AMutacion_Arbol implements AMutacion
{
	private int _profMax;
	public AMutacion_Arbol(int profundidadMaxima) {
		_profMax = profundidadMaxima;
	}
	@Override
	public void mutar(Hormiga individuo)
	{
		CArbol raiz = individuo.getCromosoma();

		//Elegir un subarbol aleatoriamente y mutarlo
		Random rand = new Random();
		CArbol aMutar = raiz.getSubarbol(rand.nextInt(raiz.getNumeroNodos()));		
		
		aMutar.copiarArbol(CArbol.generarArbolAleatorio(_profMax, "Creciente", new Random()));
		
		individuo.setAptitud(-1);
	}

}
