package amutacion;

import java.util.Random;
import implementacion.Hormiga;

public interface AMutacion {
	
	public void mutar(Hormiga[] poblacion, float mutationProb, Random rand);

}
