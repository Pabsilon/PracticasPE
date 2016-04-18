package amutacion;

import java.util.Random;

import implementation.Cromosoma;

public interface AMutacion {

	public void mutar(Cromosoma[] poblacion, float mutacionProb, Random rand);
	
}
