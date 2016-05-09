package implementacion;

//Hormiga = Individuo
public class Hormiga
{
	private CArbol _cromosoma;
	
	public Hormiga(int profundidadMaxima)
	{
		_cromosoma = CArbol.generarArbolAleatorio(profundidadMaxima);
	}

	public CArbol getCromosoma()
	{
		return _cromosoma;
	}
}
