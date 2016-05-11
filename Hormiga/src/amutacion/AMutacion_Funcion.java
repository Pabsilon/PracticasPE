package amutacion;

import java.util.Random;
import java.util.Vector;

import implementacion.CArbol;
import implementacion.CArbol.EOperador;
import implementacion.Hormiga;

public class AMutacion_Funcion implements AMutacion
{

	@Override
	public void mutar(Hormiga individuo)
	{
		CArbol raiz = individuo.getCromosoma();
		Vector<CArbol> funciones = raiz.getAllFunctions();
		//Elegir una funcion aleatoria y mutarla
		//Dado que solo hay una funcion de 3 argumentos (progn3) esta no se mutara.
		//TODO preguntar si se muta eliminando una rama
		Random rand = new Random();
		CArbol aMutar = funciones.get(rand.nextInt(funciones.size()));
		
		if(aMutar.getOperador() != EOperador.PROGN3)
		{
			aMutar.setOperador(CArbol.EOperador.fromInteger(rand.nextInt(2) + 3)); //Las funciones estan en las posiciones 3 y 4
		}
	}
	
}
