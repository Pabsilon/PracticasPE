package implementacion;

import abloating.ABloating;
import abloating.ABloating_Tarpeian;
import abloating.ABoating_Penalizacion;
import amutacion.AMutacion;
import amutacion.AMutacion_Arbol;
import amutacion.AMutacion_Funcion;
import amutacion.AMutacion_Terminal;

public class Fabrica_ABloating
{
	public static ABloating generarAlgoritmoBloating(String algoritmo){
		switch (algoritmo){
		case "Tarpeian":
			return new ABloating_Tarpeian();
		case "Penalizacion":
			return new ABoating_Penalizacion();
		default: return null;
		}
	}
}
