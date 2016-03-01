package implementacion;

public class AlgoritmoSeleccionFabrica 
{
	static AlgoritmoSeleccion getAlgoritmoDeSeleccion(String algoritmo, int participantes)
	{
		//Devuelve el algoritmo solicitado
		switch(algoritmo)
		{
		case "Ruleta":
			return new Seleccion_Ruleta();
		case "Torneo":
			return new Seleccion_Torneo(participantes);
		case "Torneo_Probabilistico":
			return new Seleccion_TorneoProb(participantes);
		default:
			return new Seleccion_Ruleta();
		}
	}
}
