package amutacion;

import java.util.Random;

import implementacion.CArbol;
import implementacion.Hormiga;

public class AMutacion_Funcion implements AMutacion{

	@Override
	public void mutar(Hormiga[] poblacion, float mutationProb, Random rand) {
		CArbol.ETipoOperador[] funciones = new CArbol.ETipoOperador[2];
		//TODO Igualar la variable funciones a las funciones de cada arbol.
		for (int j = 0; j<=funciones.length; j++){
			if (rand.nextFloat() < mutationProb){
				//TODO
				//Cambiar el valor de las funciones
				//terminales[j].setOperador();
			}
		}
	}		
}
