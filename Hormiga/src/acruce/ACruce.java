package acruce;

import implementacion.Hormiga;

public interface ACruce
{
	//Cruza dos padres y devuelve los hijos creados
	void cruzar(Hormiga padre1, Hormiga padre2, Hormiga hijo1, Hormiga hijo2);
}
