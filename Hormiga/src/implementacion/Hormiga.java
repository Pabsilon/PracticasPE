package implementacion;

import java.util.Comparator;
import java.util.PriorityQueue;

//Hormiga = Individuo
public class Hormiga
{
	private CArbol _cromosoma;
	private int _aptitud;
	
	public Hormiga(int profundidadMaxima)
	{
		_cromosoma = CArbol.generarArbolAleatorio(profundidadMaxima);
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

		public Hormiga crearCopia() {
			// TODO Auto-generated method stub
			return null;
		}
}
