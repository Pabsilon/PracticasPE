package especificos;

import java.util.Random;

import implementacion.Cromosoma;

public class ProblemaFabrica 
{
	public static Cromosoma getCromosomaProblema(String algoritmo, float precision, int n, Random rand)
	{
		//Devuelve el algoritmo solicitado
		switch(algoritmo)
		{
		case "Problema1":
			return new Problema1(precision, rand);
		case "Problema2":
			return new Problema2(precision, rand);
		case "Problema3":
			return new Problema3(precision, rand);
		case "Problema4":
			return new Problema4(precision,n, rand);
		case "Problema5":
			return new Problema5(precision, rand);
		default:
			return new Problema1(precision, rand);
		}
	}
}