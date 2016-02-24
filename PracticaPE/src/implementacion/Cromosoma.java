package implementacion;

import java.util.Random;

public abstract class Cromosoma { //Clase que implementa al individuo

	protected Gen[] _genes;
	protected int _longitud[];
	
	
	protected static int bin_dec(Gen gen){
		int retval = 0;
		for (int i=0; i<gen.getAlelosCopy().length; i++){
			if (gen.getAlelosCopy()[i]){
				retval = (int) (retval + Math.pow(2,i));
			}
		}
		
		
		return retval;
	}
	
	protected abstract float[] getFenotipo(); // Devuelve la representacion del gen
	//Hay que cambiarlo para que pueda devolver mas de un solo elemento (ej, X,Y)
	
	protected abstract float getAptitud(); // Devuelve el valor de la funcion
	
	public static void cruzar(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2)
	{
		for(int i = 0; i < hijo1._genes.length; i++)
		{
			hijo1._genes[i].setAlelos(padre1._genes[i].getAlelos());
			hijo2._genes[i].setAlelos(padre2._genes[i].getAlelos());
		}
		
		Random rand = new Random();
		
		int puntoDeCorte = rand.nextInt(padre1.getLongitudTotal() - 1) + 1; //Genera un numero (0, longitudCromosoma)
		
		//Obtenemos el gen donde cae el punto de corte
		int primerGen = 0;
		int longitudAcum = hijo1._longitud[0];
		while(longitudAcum < puntoDeCorte)
		{
			primerGen++;
			longitudAcum += hijo1._longitud[primerGen + 1];
		}
		
		//Hijo1
		int aux = longitudAcum - puntoDeCorte; //Posicion (empezando por el finak) del gen en donde cae el punto de corte
		boolean[] genPadre = padre2._genes[primerGen].getAlelosCopy();
		boolean[] genHijo = hijo1._genes[primerGen].getAlelosCopy();
		for(int i = aux; i > 0; i--)
		{
			genHijo[genHijo.length - i] = genPadre[genHijo.length - i];
		}
		hijo1._genes[primerGen].setAlelos(genHijo);
		
		//Copiar resto de genes al completo
		for(int i = primerGen + 1; i < padre2._genes.length; i++)
		{
			hijo1._genes[i].setAlelos(padre2._genes[i].getAlelos());
		}
		
		//Hijo2
		genPadre = padre1._genes[primerGen].getAlelosCopy();
		genHijo = hijo2._genes[primerGen].getAlelosCopy();
		for(int i = aux; i > 0; i--)
		{
			genHijo[genHijo.length - i] = genPadre[genHijo.length - i];
		}
		hijo2._genes[primerGen].setAlelos(genHijo);
		
		//Copiar resto de genes al completo
		for(int i = primerGen + 1; i < padre1._genes.length; i++)
		{
			hijo2._genes[i].setAlelos(padre1._genes[i].getAlelos());
		}
	}
	
	public void mutar(float mutacion)
	{
		Random rand = new Random();
		//Recorrer todos los alelos de los genes y mutar si procede
		for(int i = 0; i < _genes.length; i++)
		{
			boolean[] alelos = _genes[i].getAlelos();
			for(int j = 0; j < alelos.length; j++)
			{
				if(rand.nextFloat() < mutacion)
				{
					alelos[j] = !alelos[j];
				}
			}
		}
	}
	
	public int getLongitudTotal()
	{
		int longitudTotal = 0;
		for(int i = 0; i < _longitud.length; i++)
		{
			longitudTotal += _longitud[i];
		}
		
		return longitudTotal;
	}

	//Copia el genotipo de un cromosoma a otro
	public void copiar(Cromosoma cromosoma)
	{
		if(_genes.length == cromosoma._genes.length)
		{
			for(int i = 0; i < _genes.length; i++)
			{
				_genes[i].setAlelos(cromosoma._genes[i].getAlelos());
			}
		}
		
	}
}
