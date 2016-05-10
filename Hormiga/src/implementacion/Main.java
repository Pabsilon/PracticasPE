package implementacion;

import acruce.ACruce_Intercambio;

public class Main
{
	public static void main(String[] args)
	{
		/*Hormiga p1 = new Hormiga(4);
		Hormiga p2 = new Hormiga(4);
		Hormiga h1 = new Hormiga(4);
		Hormiga h2 = new Hormiga(4);
		
		ACruce_Intercambio c = new ACruce_Intercambio();
		
		c.cruzar(p1, p2, h1, h2);*/
		
		for(int i = 0; i < 15; i++)
		{
			Hormiga h = new Hormiga(4);
			System.out.println(h.getCromosoma().toString() + h.getCromosoma().getProfundidad());
		}
		
	}
}
