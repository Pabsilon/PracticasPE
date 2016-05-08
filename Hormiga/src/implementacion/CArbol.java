package implementacion;

import java.util.LinkedList;

//Custom arbol para representar el individuo
public class CArbol 
{
	static public enum ETipo
	{
		AVANZA, 
		GIRA_DERECHA,
		GIRA_IZQUIERDA,
		SIC,
		PROGN2,
		PROGN3 
	}
	
	private ETipo _tipo;
	private CArbol _padre;
	private LinkedList<CArbol> _hijos;
	private int _profundidad;
	
	public CArbol(ETipo tp)
	{
		_tipo = tp;
		_padre = null;
		_hijos = new LinkedList<>();
		_profundidad = 0;
	}
	
	//Crea un nuevo arbol y le añade como hijo el Arbol h
	public CArbol(ETipo tp, CArbol h)
	{
		_tipo = tp;
		_padre = null;
		_hijos = new LinkedList<>();
		
		addHijo(h);
	}
	
	public void addHijo(CArbol h)
	{
		h._padre = this;
		_hijos.add(h);
		_profundidad = h._profundidad + 1;
	}

	//Genera un Arbol con sus hijos, etc. de forma aleatoria
	public static CArbol generarArbolAleatorio()
	{
		
		
		
		return null;
	}
}
