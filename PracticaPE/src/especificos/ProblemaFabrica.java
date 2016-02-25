package especificos;

import implementacion.Cromosoma;

public class ProblemaFabrica 
{
	static Cromosoma getAlgoritmoDeSeleccion(String algoritmo, float precision, int n)
	{
		//Devuelve el algoritmo solicitado
		switch(algoritmo)
		{
		case "Problema1":
			return new Problema1(precision);
		case "Problema2":
			return new Problema2(precision);
		case "Problema3":
			return new Problema3(precision);
		case "Problema4":
			return new Problema4(precision,n);
		case "Problema5":
			return new Problema5(precision);
		default:
			return new Problema1(0.001f);
		}
	}
}