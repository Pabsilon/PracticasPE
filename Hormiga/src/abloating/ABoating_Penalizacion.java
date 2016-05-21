package abloating;

import implementacion.Hormiga;

public class ABoating_Penalizacion implements ABloating {

	@Override
	public void controlBloating(Hormiga[] pob)
	{
		//F'(x) = F(x) + C(t) * numNodos(x)
		//C(t) = Cov(l,f)/Var(l)
		//l = tam programa, f = fitness
		
		float cov = calcularCovarianza(pob);
		float var = calcularVarianza(pob);
		float C = cov / var;
		
		for(int i = 0; i < pob.length; i++)
		{
			int f = pob[i].getAptitud();
			pob[i].setAptitud(f + (int)(C * pob[i].getCromosoma().getNumeroNodos()));
		}
	}

	private float calcularVarianza(Hormiga[] pob)
	{
		float media = 0;
		float sumSquared = 0;
		for(int i = 0; i < pob.length; i++)
		{
			media += pob[i].getCromosoma().getProfundidad();
			int tam = pob[i].getCromosoma().getProfundidad();
			sumSquared += tam * tam;
		}
		media = media / pob.length;
		media = media * media;
		
		sumSquared = sumSquared / pob.length;
		
		return sumSquared - media;
	}

	private float calcularCovarianza(Hormiga[] pob)
	{
		float mediaL = 0;
		float mediaF = 0;
		
		int sum = 0;
		for(int i = 0; i < pob.length; i++)
		{
			int f = pob[i].getAptitud();
			int l = pob[i].getCromosoma().getProfundidad();
			
			sum += l * f;
			mediaL += l;
			mediaF += f;
		}
		
		sum = sum / pob.length;
		mediaL = mediaL / pob.length;
		mediaF = mediaF / pob.length;
		
		
		return sum - (mediaL * mediaF);
	}

}
