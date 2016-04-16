package implementation;

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
