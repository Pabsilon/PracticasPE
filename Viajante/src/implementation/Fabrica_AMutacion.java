package implementation;

public class Fabrica_AMutacion {
	
	public static AMutacion generarAlgoritmoMutacion(String algoritmo){
		// {"Insercion", "Intercambio", "Inversion", "Heuristica", "Propio"};
		switch (algoritmo){
		case "Insercion":
			return new AMutacion_Insercion();
		case "Intercambio":
			return new AMutacion_Intercambio();
		case "Inversion":
			return new AMutacion_Inversion();
		case "Heuristica":
			return new AMutacion_Heuristica();
		case "Propio":
			return new AMutacion_Propio();
		default: return null;
		}
	}

}
