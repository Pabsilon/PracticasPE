package implementation;

public class Fabrica_ASeleccion 
{
	public static ASeleccion generarAlgoritmoSeleccion(String algoritmo)
	{
		switch(algoritmo)
		{
		case "Ruleta":
			return new ASeleccion_Ruleta();
		default:
			return null;
		}
	}
}
