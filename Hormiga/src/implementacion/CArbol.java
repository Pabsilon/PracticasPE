package implementacion;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

//Custom arbol para representar el individuo
public class CArbol 
{
	static public enum EOperador
	{
		AVANZA("AVANZA"), 
		GIRA_DERECHA("GIRA_DERECHA"),
		GIRA_IZQUIERDA("GIRA_IZQUIERDA"),
		SIC("SIC"), //Si comida delante ejecuta a else b
		PROGN2("PROGN2"), //Evalua a, luego b y devuelve valor b
		PROGN3("PROGN3"); //Evalua a, b, c y deveulve c
		
		private String _name;
		private EOperador(String s)
		{
			_name = s;
		}
		
		@Override
		public String toString() {
			return _name;
		}
		
		public static EOperador fromInteger(int x)
		{
			switch(x)
			{
			case 0:
				return AVANZA;
			case 1:
				return GIRA_DERECHA;
			case 2:
				return GIRA_IZQUIERDA;
			case 3:
				return SIC;
			case 4:
				return PROGN2;
			case 5:
				return PROGN3;
			default:
					assert(false);
					return null;
			}
		}

		public static int getSize() {
			return 6;
		}
	}
	
	static public enum ETipoOperador
	{
		FUNCION,
		TERMINAL
	}
	
	private EOperador _operador;
	private ETipoOperador _tipoOperador;
	private CArbol _padre;
	private LinkedList<CArbol> _hijos;
	private int _profundidad;
	private int _numeroNodos;
	
	//Genera un arbol de operador aleatorio
	public CArbol()
	{
		Random rand = new Random();
		_operador = EOperador.fromInteger(rand.nextInt(EOperador.getSize()));
		_tipoOperador = getTipoOperador(_operador);
		_padre = null;
		_hijos = new LinkedList<>();
		_profundidad = 0;
		_numeroNodos = 1;
	}
	
	public CArbol(EOperador op)
	{
		_operador = op;
		_tipoOperador = getTipoOperador(_operador);
		_padre = null;
		_hijos = new LinkedList<>();
		_profundidad = 0;
		_numeroNodos = 1;
	}
	
	//Crea un nuevo arbol y le añade como hijo el Arbol h
	public CArbol(EOperador tp, CArbol h)
	{
		_operador = tp;
		_tipoOperador = getTipoOperador(_operador);
		_padre = null;
		_hijos = new LinkedList<>();
		
		addHijo(h);
	}
<<<<<<< HEAD
	
	//Crea un arbol igual al arbol pasado como parametro
	public CArbol(CArbol aCopiar)
	{
		_operador = aCopiar._operador;
		_tipoOperador = aCopiar._tipoOperador;
		_padre = aCopiar._padre;
		_profundidad = aCopiar._profundidad;
		_numeroNodos = aCopiar._numeroNodos;
		
		//Copiar recursivamente los hijos
		_hijos = new LinkedList<>();
		for(CArbol hijo : aCopiar._hijos)
		{
			_hijos.add(new CArbol(hijo));
		}
	}
	
=======

>>>>>>> 8d807887c835dcdcc5be0af8f0e109b296d43e40
	public void addHijo(CArbol h)
	{
		h._padre = this;
		_hijos.add(h);
		
		actualizarDatosPadre(this);
	}

	private static void actualizarDatosPadre(CArbol padre)
	{
		if(padre == null) return;
		
		//Actualiza la profundidad y numero de nodos de los padres recursivamente
		padre._profundidad = 0;
		padre._numeroNodos = 0;
		for(CArbol hijo : padre._hijos)
		{
			padre._profundidad += hijo._profundidad;
			padre._numeroNodos += hijo._numeroNodos;
		}
		padre._numeroNodos++; //Anadirse a si mismo
		padre._profundidad++;
		
		actualizarDatosPadre(padre._padre);
	}

	//Genera un Arbol con sus hijos, etc. de forma aleatoria
	public static CArbol generarArbolAleatorio(int profundidadMaxima)
	{
		//Cola con los arboles aun sin terminar
		Queue<CArbol> cola = new LinkedList<CArbol>();
			
		CArbol raiz = new CArbol();
		if(raiz._tipoOperador == ETipoOperador.TERMINAL) //Si la raiz es un terminal, terminamos
		{
			return raiz;
		}
		cola.add(raiz);
		while(!cola.isEmpty() && raiz._profundidad - 1 < profundidadMaxima) //Generamos hasta profundiad - 1 como maximo
		{
			CArbol arbolAux = cola.poll();
			
			//Generar dependiendo del tipo de operacion
			if((arbolAux._operador == EOperador.SIC) || (arbolAux._operador == EOperador.PROGN2))
			{
				//Generamos dos hijos
				CArbol hijo1 = new CArbol();
				CArbol hijo2 = new CArbol();
				
				arbolAux.addHijo(hijo1);
				arbolAux.addHijo(hijo2);
				
				//Add a la cola solo si es de tipo funcion
				if(hijo1._tipoOperador == ETipoOperador.FUNCION)
				{
					cola.add(hijo1);
				}
				if(hijo2._tipoOperador == ETipoOperador.FUNCION)
				{
					cola.add(hijo2);
				}
			}
			else
			{
				//Generamos tres hijos
				CArbol hijo1 = new CArbol();
				CArbol hijo2 = new CArbol();
				CArbol hijo3 = new CArbol();
				
				arbolAux.addHijo(hijo1);
				arbolAux.addHijo(hijo2);
				arbolAux.addHijo(hijo3);
				
				//Add a la cola solo si es de tipo funcion
				if(hijo1._tipoOperador == ETipoOperador.FUNCION)
				{
					cola.add(hijo1);
				}
				if(hijo2._tipoOperador == ETipoOperador.FUNCION)
				{
					cola.add(hijo2);
				}
				if(hijo3._tipoOperador == ETipoOperador.FUNCION)
				{
					cola.add(hijo3);
				}
			}
		}
		
		//Estamos en la ultima profundidad, por lo que solo se deben generar funciones terminales
		while(!cola.isEmpty())
		{
			CArbol arbolAux = cola.poll();
			
			Random rand = new Random();
			arbolAux.addHijo(new CArbol(CArbol.EOperador.fromInteger(rand.nextInt(3)))); //Operadores terminales van de 0 - 2
		}
		
		
		return raiz;
	}
	
	//Devuelve el tipo de un operador
	private ETipoOperador getTipoOperador(EOperador op)
	{
		switch(op)
		{
		case SIC:
		case PROGN2:
		case PROGN3:
			return ETipoOperador.FUNCION;
		case AVANZA:
		case GIRA_DERECHA:
		case GIRA_IZQUIERDA:
			return ETipoOperador.TERMINAL;
		default:
			return ETipoOperador.TERMINAL;
		}
	}

	public int getNumeroNodos() {
		return _numeroNodos;
	}
	
	//Devuelve referencia al subarbol en la posicion indx
	public CArbol getSubarbol(int indx)
	{
		//Recorrido en anchura
		return recorridoEnAnchura(indx);
	}
	
	public void setSubarbol(int indx, CArbol nuevo)
	{
		CArbol old = this.getSubarbol(indx);
		CArbol padreOld = old._padre;
		
		padreOld._hijos.remove(old);
		padreOld.addHijo(nuevo);
	}
	
	//Hace un recorrido en anchura y devuelve el arbol en la posicion indx
	private CArbol recorridoEnAnchura(int indx)
	{
		if(indx > _numeroNodos) assert(false);
		
		Queue<CArbol> cola = new LinkedList<CArbol>();
		cola.add(this);
		int i = 0;
		while(i < indx)
		{
			CArbol a = cola.poll();
			cola.addAll(a._hijos);
			i++;
		}
		
		return cola.peek();
	}
	
	@Override
	public String toString()
	{
		String toRet = " (" + _operador.toString();
		for(CArbol h: _hijos)
		{
			toRet += h.toString();
		}
		
		toRet += ")";
		
		return toRet;
	}
	
	public CArbol crearCopia()
	{
		return new CArbol(this);
	}
}
