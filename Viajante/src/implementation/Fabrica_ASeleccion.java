package implementation;

import aseleccion.ASeleccion;
import aseleccion.ASeleccion_Ranking;
import aseleccion.ASeleccion_Restos;
import aseleccion.ASeleccion_Ruleta;
import aseleccion.ASeleccion_Torneo;
import aseleccion.Aseleccion_Truncamiento;

public class Fabrica_ASeleccion 
{
	public static ASeleccion generarAlgoritmoSeleccion(String algoritmo, int n)
	{
		//{"Ruleta", "Torneo", "Ranking", "Restos", "Truncamiento"};
		switch(algoritmo)
		{
		case "Ruleta":
			return new ASeleccion_Ruleta();
		case "Torneo":
			return new ASeleccion_Torneo(n);
		case "Ranking":
			return new ASeleccion_Ranking();
		case "Restos":
			return new ASeleccion_Restos();
		case "Truncamiento":
			return new Aseleccion_Truncamiento();
		default:
			return null;
		}
	}
}
