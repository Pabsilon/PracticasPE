package implementacion;

public class AlgoritmoSeleccionFabrica 
{
	static AlgoritmoSeleccion getAlgoritmoDeSeleccion(String algoritmo)
	{
		//Devuelve el algoritmo solicitado
		switch(algoritmo)
		{
		case "Ruleta":
			return new Seleccion_Ruleta();
		case "Torneo":
			return new Seleccion_Torneo();
		default:
			return new Seleccion_Ruleta();
		}
	}
}
