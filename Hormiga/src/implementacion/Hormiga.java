package implementacion;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

//Hormiga = Individuo
public class Hormiga
{
	private CArbol _cromosoma;
	private int _aptitud;
	
	public Hormiga(int profundidadMaxima, String tipoInicializacion, Random rand)
	{
		_cromosoma = CArbol.generarArbolAleatorio(profundidadMaxima, tipoInicializacion, rand);
		_aptitud = -1;
	}

	public Hormiga() {
		_cromosoma = null;
		_aptitud = -1;
	}

	public CArbol getCromosoma()
	{
		return _cromosoma;
	}
	
	public void setCromosoma(CArbol nuevoCrom)
	{
		_cromosoma = nuevoCrom.crearCopia();
		_aptitud = -1;
	}
	
	public int getAptitud()
	{
		if(_aptitud <= -1)
		{
			calcularAptitud();
		}
		
		return _aptitud;
	}
	
	//Obliga a recalcular la aptitud
	public int getAptitudForced()
	{
		calcularAptitud();
		
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
			Hormiga toRet = new Hormiga();
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

		public Tablero getTableroRecorrido()
		{
			return _cromosoma.getTableroRecorrido();
		}

		public void setAptitud(int i)
		{
			_aptitud = i;
			
		}
}
