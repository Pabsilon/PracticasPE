package amutacion;

import java.util.Random;

import implementacion.CArbol;
import implementacion.Hormiga;

public class AMutacion_Terminal implements AMutacion{

	@Override
	public void mutar(Hormiga[] poblacion, float mutationProb, Random rand) {
		for (int i = 0; i<poblacion.length; i++){
			CArbol.ETipoOperador[] terminales = new CArbol.ETipoOperador[2];
			//TODO Igualar la variable terminales a los terminales de cada arbol.
			for (int j = 0; j<=terminales.length; j++){
				if (rand.nextFloat() < mutationProb){
					//TODO
					//Cambiar el valor de los terminales
					//terminales[j].setOperador();
				}
			}
		}	
	}
}
