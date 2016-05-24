package implementacion;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Random;
import java.util.Vector;

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
	
	//Genera un arbol de operador aleatorio
	public CArbol(Random rand)
	{
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
	
	//Crea un arbol igual al arbol pasado como parametro
	public CArbol(CArbol aCopiar)
	{
		copiarArbol(aCopiar);
	}
	
	//Convierte este arbol en una copia del arbol aCopiar
	public void copiarArbol(CArbol aCopiar)
	{
		_operador = aCopiar._operador;
		_tipoOperador = aCopiar._tipoOperador;
		_profundidad = aCopiar._profundidad;
		_numeroNodos = aCopiar._numeroNodos;		
		_padre = null;
		
		//Copiar recursivamente los hijos
		_hijos = new LinkedList<>();
		for(CArbol hijo : aCopiar._hijos)
		{
			this.addHijo(new CArbol(hijo));
		}
	}
	
	public void addHijo(CArbol h)
	{
		h._padre = this;
		_hijos.add(h);
		
		actualizarDatosHaciaAtras(this);
	}

	public void removerHijo(CArbol old)
	{
		old._padre = null;
		_hijos.remove(old);


		actualizarDatosHaciaAtras(this);
	}

	private static void actualizarDatosHaciaAtras(CArbol arbol)
	{
		if(arbol == null) return;
		
		//Actualiza la profundidad y numero de nodos recursivamente
		arbol._profundidad = 0;
		arbol._numeroNodos = 1;
		for(CArbol hijo : arbol._hijos)
		{
			arbol._profundidad = hijo._profundidad >= arbol._profundidad ? hijo._profundidad + 1 : arbol._profundidad;
			arbol._numeroNodos += hijo._numeroNodos;
		}
		
		actualizarDatosHaciaAtras(arbol._padre);
	}

	private void actualizarDatosHaciaDelante(CArbol arbol)
	{
		int numNodos = 1;
		int profundidad = 0;
		
		for(CArbol h: arbol._hijos)
		{
			actualizarDatosHaciaDelante(h);
			numNodos += h._numeroNodos;
			profundidad = profundidad >= h._profundidad ? h._profundidad + 1 : h._profundidad;
		}
		
		arbol._numeroNodos = numNodos;
		arbol._profundidad = profundidad;
		
	}
	
	public void actualizar()
	{
		actualizarDatosHaciaDelante(this);
	}

	//Genera un Arbol con sus hijos, etc. de forma aleatoria
	public static CArbol generarArbolAleatorio(int profundidadMaxima, String tipoGeneracion, Random rand)
	{
		CArbol raiz;
		if(tipoGeneracion.equalsIgnoreCase("Completa"))
		{
			raiz = inicializacionCompleta(profundidadMaxima, 0, rand);
		}
		else
		{
			raiz = inicializacionCreciente(profundidadMaxima, 0, rand);
		}
		
		return raiz;
	}
	
	static CArbol inicializacionCompleta(int profundidadMaxima, int profundidadActual, Random rand)
	{
		CArbol raiz = new CArbol(rand);
		if(profundidadActual < profundidadMaxima)
		{
			raiz._operador = CArbol.EOperador.fromInteger(rand.nextInt(3) + 3); //Las funciones estan en las posiciones 3, 4 y 5
			raiz._tipoOperador = ETipoOperador.FUNCION;
			
			if((raiz._operador == EOperador.SIC) || (raiz._operador == EOperador.PROGN2))
			{
				for(int i = 0; i < 2; i++)
				{
					raiz.addHijo(inicializacionCompleta(profundidadMaxima, profundidadActual + 1, rand));
				}
			}
			else
			{
				for(int i = 0; i < 3; i++)
				{
					raiz.addHijo(inicializacionCompleta(profundidadMaxima, profundidadActual + 1, rand));
				}
			}
		}
		else
		{
			raiz._operador = CArbol.EOperador.fromInteger(rand.nextInt(3)); //Los terminales estan en las posiciones 0, 1, 2
			raiz._tipoOperador = ETipoOperador.TERMINAL;
		}
		
		return raiz;
	}
	
	static CArbol inicializacionCreciente(int profundidadMaxima, int profundidadActual, Random rand)
	{
		CArbol raiz = new CArbol(rand);
		if(profundidadActual < profundidadMaxima)
		{			
			if((raiz._operador == EOperador.SIC) || (raiz._operador == EOperador.PROGN2))
			{
				for(int i = 0; i < 2; i++)
				{
					raiz.addHijo(inicializacionCompleta(profundidadMaxima, profundidadActual + 1, rand));
				}
			}
			else if(raiz._operador == EOperador.PROGN3)
			{
				for(int i = 0; i < 3; i++)
				{
					raiz.addHijo(inicializacionCompleta(profundidadMaxima, profundidadActual + 1, rand));
				}
			}
		}
		else
		{
			raiz._operador = CArbol.EOperador.fromInteger(rand.nextInt(3)); //Los terminales estan en las posiciones 0, 1, 2
			raiz._tipoOperador = ETipoOperador.TERMINAL;
		}
		
		return raiz;
	}
	
	static CArbol inicializacionRampedHalf(int profundidadMaxima, int profundidadActual, Random rand)
	{
		return null;
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
		return getSubArbol_index(indx);
	}
	
	//Cambia el subarbol en la posicion indx por el arbol del argumento
	public void setSubarbol(int indx, CArbol nuevo)
	{
		CArbol old = getSubarbol(indx);
		CArbol padreOld = old._padre;
		
		//Si no es el nodo raiz
		if(padreOld != null)
		{
			padreOld.removerHijo(old);
			padreOld.addHijo(nuevo);
		}
		else
		{
			//Si es el nodo raiz, es como copiar el arbol
			this.copiarArbol(nuevo);
		}
		
	}
	
	//Devuelve un array con todos los nodos del arbol que son terminales
	@SuppressWarnings("rawtypes")
	public Vector<CArbol> getAllTerminals()
	{
		Vector<CArbol> toRet = new Vector<>();
		
		Queue<CArbol> cola = new LinkedList<CArbol>();
		cola.add(this);
		while(!cola.isEmpty())
		{
			CArbol a = cola.poll();
			
			ListIterator it = a._hijos.listIterator();
			while(it.hasNext())
			{
				cola.add((CArbol) it.next());
			}
			
			if(a._tipoOperador == ETipoOperador.TERMINAL)
			{
				toRet.add(a);
			}
		}
		
		return toRet;
	}
	
	//Devuelve un array con todos los nodos del arbol que son funciones
	@SuppressWarnings("rawtypes")
	public Vector<CArbol> getAllFunctions()
	{
		Vector<CArbol> toRet = new Vector<>();
		
		Queue<CArbol> cola = new LinkedList<CArbol>();
		cola.add(this);
		while(!cola.isEmpty())
		{
			CArbol a = cola.poll();

			ListIterator it = a._hijos.listIterator();
			while(it.hasNext())
			{
				cola.add((CArbol) it.next());
			}
			
			a._tipoOperador = getTipoOperador(a._operador);
			if(a._tipoOperador == ETipoOperador.FUNCION)
			{
				toRet.add(a);
			}
		}
		
		return toRet;
	}
	
	//Hace un recorrido en anchura y devuelve el arbol en la posicion indx
	@SuppressWarnings("rawtypes")
	private CArbol getSubArbol_index(int indx)
	{
		if(indx > _numeroNodos) assert(false);
		
		Queue<CArbol> cola = new LinkedList<CArbol>();
		cola.add(this);
		int i = 0;
		while(i < indx)
		{
			CArbol a = cola.poll();
			ListIterator it = a._hijos.listIterator();
			while(it.hasNext())
			{
				cola.add((CArbol) it.next());
			}
			i++;
		}
		
		return cola.poll();
	}
	
	public int recorrerTablero()
	{
		Tablero tab = new Tablero();
		int pos[] = {0, 0}; //Posicion x,y
		int orientacion[] = {1}; //0 arriba, 1 derecha, 2 abajo, 3 izquierda
		int paso[] = {0};
		int sum = 0;
		while(paso[0] < 400)
		{
			sum += recorrerTablero_aux(pos, orientacion, tab, paso);
		}
		
		return sum;
	}
	
	public Tablero getTableroRecorrido()
	{
		Tablero tab = new Tablero();
		int pos[] = {0, 0}; //Posicion x,y
		int orientacion[] = {1}; //0 arriba, 1 derecha, 2 abajo, 3 izquierda
		int paso[] = {0};
		while(paso[0] < 400)
		{
			recorrerTablero_aux(pos, orientacion, tab, paso);
		}
		
		return tab;
	}
	
	private int recorrerTablero_aux(int[] pos, int orientacion[], Tablero tab, int paso[])
	{		
		int toRet = 0;
		if(tab.getValue(pos[0], pos[1]).equals("Comida"))
		{
			toRet = 1;
		}
		tab.pasoHormiga(pos[0], pos[1]);
		
		if(_operador == EOperador.SIC)
		{
			boolean comida = false;
			switch(orientacion[0])
			{
			case 0: //Arriba
				comida = tab.getValue(pos[0] , pos[1] - 1).equals("Comida") ? true : false;
				break;
			case 1: //Derecha
				comida = tab.getValue(pos[0] + 1, pos[1]).equals("Comida") ? true : false;
				break;
			case 2: //Abajo
				comida = tab.getValue(pos[0] , pos[1] + 1).equals("Comida") ? true : false;
				break;
			case 3: //Izquierda
				comida = tab.getValue(pos[0] - 1, pos[1]).equals("Comida") ? true : false;
				break;
			}
			
			//Si hay comida realizar la primera accion (hijo 1)
			if(comida)
			{
				toRet += _hijos.getFirst().recorrerTablero_aux(pos, orientacion, tab, paso);
			}
			else //Si no, hijo (2)
			{
				toRet += _hijos.getLast().recorrerTablero_aux(pos, orientacion, tab, paso);
			}
		}
		else if(_operador == EOperador.AVANZA)
		{
			int nuevaPos[] = new int[2];
			switch(orientacion[0])
			{
			case 0: //Arriba
				nuevaPos[0] = pos[0];
				nuevaPos[1] = pos[1] - 1;
				break;
			case 1: //Derecha
				nuevaPos[0] = pos[0] + 1;
				nuevaPos[1] = pos[1];
				break;
			case 2: //Abajo
				nuevaPos[0] = pos[0];
				nuevaPos[1] = pos[1] + 1;
				break;
			case 3: //Izquierda
				nuevaPos[0] = pos[0] - 1;
				nuevaPos[1] = pos[1];
				break;
			}
			
			pos[0] = nuevaPos[0];
			pos[1] = nuevaPos[1];
			
			paso[0]++;
			
			if(pos[0] < 0 || pos[0] >= Tablero._sizeT || pos[1] < 0 || pos[1] >= Tablero._sizeT)
			{
				paso[0] = 400;
			}
		}
		else if(_operador == EOperador.GIRA_IZQUIERDA) //Giramos en el sentido de las agujas del reloj (por lo tanto, izquierda gira en sentido contrario a las agujas)
		{
			orientacion[0] = orientacion[0] - 1;
			if(orientacion[0] < 0)
			{
				orientacion[0] = 3;
			}
			
			paso[0]++;
		}
		else if(_operador == EOperador.GIRA_DERECHA)
		{
			orientacion[0] = orientacion[0] + 1;
			if(orientacion[0] > 3)
			{
				orientacion[0] = 0;
			}
			
			paso[0]++;
		}
		else //Progn2 y progn3 ejecutan todos sus hijos
		{
			for(CArbol hijo : _hijos)
			{
				toRet += hijo.recorrerTablero_aux(pos, orientacion, tab, paso);
			}
		}
		
		return toRet;
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

	public int getProfundidad() {
		return _profundidad;
	}
	
	public void setOperador(EOperador op)
	{
		_operador = op;
		_tipoOperador = getTipoOperador(_operador);
	}

	public EOperador getOperador()
	{
		return _operador;
	}

	public void cortarArbol(int profundidadMaxima)
	{
		cortarArbolAux(this, 0, profundidadMaxima);
		actualizarDatosHaciaDelante(this);
	}
	
	private void cortarArbolAux(CArbol arbol, int profundidad, int profundidadMaxima)
	{
		if(profundidad < profundidadMaxima)
		{
			for(CArbol h : arbol._hijos)
			{
				cortarArbolAux(h, profundidad + 1, profundidadMaxima);
			}
		}
		else
		{
			if(arbol._tipoOperador == ETipoOperador.FUNCION)
			{
				Random rand = new Random();
				
				//Borrar hijos y generar terminal
				arbol._operador = CArbol.EOperador.fromInteger(rand.nextInt(3));
				arbol._tipoOperador = ETipoOperador.TERMINAL;
				
				arbol.clear();
			}
			
		}
	}
	
	void clear()
	{
		_hijos.clear();
		_profundidad = 0;
		_numeroNodos = 1;
	}
}
