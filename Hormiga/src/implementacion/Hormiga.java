package implementacion;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

//Hormiga = Individuo
public class Hormiga
{
	private CArbol _cromosoma;
	private int _aptitud;
	
	public Hormiga(int profundidadMaxima, Random rand)
	{
		_cromosoma = CArbol.generarArbolAleatorio(profundidadMaxima, rand);
		_aptitud = -1;
	}

	public CArbol getCromosoma()
	{
		return _cromosoma;
	}
	
	public void setCromosoma(CArbol nuevoCrom)
	{
		_cromosoma = nuevoCrom.crearCopia();
	}
	
	public int getAptitud()
	{
		if(_aptitud == -1)
		{
			calcularAptitud();
		}
		
		return _aptitud;
	}
	
	private void calcularAptitud()
	{
		_aptitud = _cromosoma.recorrerTablero();
	}

		//Crea una nueva cola de prioridad de maximos de Hormiga
		public static PriorityQueue<Hormiga> crearColaPrioridadHormiga()
		{
			Comparator<Hormiga> comparador;
			comparador = new Comparator<Hormiga>()
			{
				@Override
				public int compare(Hormiga o1, Hormiga o2)
				{
					if(o1.getAptitud() > o2.getAptitud()) return -1;
					if(o1.getAptitud() == o2.getAptitud()) return 0;
					if(o1.getAptitud() < o2.getAptitud()) return 1;
					return 0;
				}
			};
			
			PriorityQueue<Hormiga> monticuloMinimos = new PriorityQueue<Hormiga>(comparador);
			
			return monticuloMinimos;
		}

		public Hormiga crearCopia()
		{
			Hormiga toRet = new Hormiga(0, new Random());
			toRet._aptitud = _aptitud;
			toRet._cromosoma = _cromosoma.crearCopia();
			
			return toRet;
		}

		public String getFenotipo() {
			return _cromosoma.toString();
		}
		
		@Override
		public String toString()
		{
			return getFenotipo();
		}
}
