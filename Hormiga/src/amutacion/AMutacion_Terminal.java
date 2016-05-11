package amutacion;

import java.util.Vector;
import java.util.Random;

import implementacion.CArbol;
import implementacion.Hormiga;

public class AMutacion_Terminal implements AMutacion
{

	@Override
	public void mutar(Hormiga individuo)
	{
		CArbol raiz = individuo.getCromosoma();
		Vector<CArbol> terminales = raiz.getAllTerminals();
		//Elegir un terminal aleatoriamente y mutarlo
		Random rand = new Random();
		CArbol aMutar = terminales.get(rand.nextInt(terminales.size()));
		
		aMutar.setOperador(CArbol.EOperador.fromInteger(rand.nextInt(3)));
	}
}
