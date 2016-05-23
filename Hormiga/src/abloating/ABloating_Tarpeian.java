package abloating;

import java.util.Random;

import implementacion.Hormiga;

public class ABloating_Tarpeian implements ABloating {

	@Override
	public void controlBloating(Hormiga[] pob, int aptitudes[])
	{
		//El problema de Tarpeian es que la media puede variar y subir en cada iteracion un punto (mas o menos). Por lo que alcabao de muchas generaciones la profundidad media ha subido bastante
		double media = 0;
		for(int i = 0; i < pob.length; i++)
		{
			media += pob[i].getCromosoma().getProfundidad();
		}
		media = Math.ceil(media / pob.length);
		
		final float N = 2;
		Random rand = new Random();
		for(int i = 0; i < pob.length; i++)
		{
			if((pob[i].getCromosoma().getProfundidad() > media) && (rand.nextFloat() <= 1/N))
			{
				pob[i].setAptitud(0);
				aptitudes[i] = 0;
			}
		}

	}

}
