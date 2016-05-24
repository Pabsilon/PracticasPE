package implementacion;

import abloating.ABloating;
import abloating.ABloating_Tarpeian;
import abloating.ABoating_Penalizacion;

public class Fabrica_ABloating
{
	public static ABloating generarAlgoritmoBloating(String algoritmo, int profMax){
		switch (algoritmo){
		case "Tarpeian":
			return new ABloating_Tarpeian();
		case "Penalizacion":
			return new ABoating_Penalizacion(profMax);
		default: return null;
		}
	}
}
