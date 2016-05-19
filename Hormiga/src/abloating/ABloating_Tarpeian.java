package abloating;

import java.util.Random;

import implementacion.Hormiga;

public class ABloating_Tarpeian implements ABloating {

	@Override
	public void controlBloating(Hormiga[] pob)
	{
		double media = 0;
		for(int i = 0; i < pob.length; i++)
		{
			media += pob[i].getCromosoma().getProfundidad();
		}
		media = Math.ceil(media / pob.length);
		
		//25% prob de que se elimine
		final float N = 4;
		Random rand = new Random();
		for(int i = 0; i < pob.length; i++)
		{
			if((pob[i].getCromosoma().getProfundidad() > media) && (rand.nextFloat() <= 1/N))
			{
				pob[i].setAptitud(0);
			}
		}

	}

}
