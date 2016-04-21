package acruce;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Vector;

import implementation.Cromosoma;
import implementation.SpainMap;

/** Implementacion de cruce por Coordinación Ordinal
 * @author Jorge Sanchez
 *
 */
public class ACruce_CodOrdinal implements ACruce {

	@Override
	public void cruzar(Cromosoma p1, Cromosoma p2, Cromosoma h1, Cromosoma h2)
	{
		int[] genPadre1 = p1.getGenotipo();
		int[] genPadre2 = p2.getGenotipo();
		int[] genHijo1 = new int[genPadre1.length];
		Arrays.fill(genHijo1, -1);
		genHijo1[0] = SpainMap.getCityID("Madrid");
		int[] genHijo2 = new int[genPadre2.length];
		Arrays.fill(genHijo2, -1);
		genHijo2[0] = SpainMap.getCityID("Madrid");
		
		obtenerListaDeIndices(genHijo1, genHijo2, genPadre1, genPadre2);
		
		//Elegimos punto de corte aleatorio
		Random rand = new Random();
		int puntoCorte = rand.nextInt(genPadre1.length - 1) + 1;
		
		Vector<Integer> listaDinamicaP1 = new Vector<>();
		Vector<Integer> listaDinamicaP2 = new Vector<>();
		obtenerVectorOrdenado(listaDinamicaP1, listaDinamicaP2, genPadre1, genPadre2);
		
		//Primera parte que se queda igual
		for(int i = 1; i < puntoCorte; i++)
		{
			//Usamos los indices (que estan guardados en los genes) para obtener la ciudad correct
			int ind1 = genHijo1[i];
			int ind2 = genHijo2[i];
			genHijo1[i] = listaDinamicaP1.get(ind1);
			genHijo2[i] = listaDinamicaP2.get(ind2);
			
			listaDinamicaP1.remove(ind1);
			listaDinamicaP2.remove(ind2);
		}
		
		//Segunda parte se intercambia
		for(int i = puntoCorte; i < genPadre1.length; i++)
		{
			//Usamos los indices (que estan guardados en los genes) para obtener la ciudad correct
			int ind1 = genHijo1[i];
			int ind2 = genHijo2[i];
			genHijo1[i] = listaDinamicaP1.get(ind2); //Los indices se intercambian
			genHijo2[i] = listaDinamicaP2.get(ind1);
			
			listaDinamicaP1.remove(ind2);
			listaDinamicaP2.remove(ind1);
		}
		
		h1.setGenotipo(genHijo1);
		h2.setGenotipo(genHijo2);
	}

	private void obtenerListaDeIndices(int[] genHijo1, int[] genHijo2, int[] genPadre1, int[] genPadre2) {
		//Ordenar las ciudades según su numero (Madrid no se toca)
		Vector<Integer> listaDinamicaP1 = new Vector<>();
		Vector<Integer> listaDinamicaP2 = new Vector<>();
		obtenerVectorOrdenado(listaDinamicaP1, listaDinamicaP2, genPadre1, genPadre2);
		
		//Obtener los indices (Usamos los genes de los hijos 
		for(int i = 1; i < genPadre1.length; i++)
		{
			genHijo1[i] = listaDinamicaP1.indexOf(genPadre1[i]);
			genHijo2[i] = listaDinamicaP2.indexOf(genPadre2[i]);
			
			listaDinamicaP1.remove(genHijo1[i]);
			listaDinamicaP2.remove(genHijo2[i]);
		}
		
	}

	//TODO Como sabemos que es una cadena de 0 - Nº Ciudades, esto podria crearse directamente con un bucle for
	private void obtenerVectorOrdenado(Vector<Integer> listaDinamicaP1, Vector<Integer> listaDinamicaP2, int[] genPadre1, int[] genPadre2)
	{
		PriorityQueue<Integer> colaP1 = new PriorityQueue<Integer>();
		PriorityQueue<Integer> colaP2 = new PriorityQueue<Integer>();
		for(int i = 1; i < genPadre1.length; i++)
		{
			colaP1.add(genPadre1[i]);
			colaP2.add(genPadre2[i]);
		}

		for(int i = 1; i < genPadre2.length; i++)
		{
			listaDinamicaP1.add(colaP1.poll());
			listaDinamicaP2.add(colaP2.poll());
		}
		
	}

}
