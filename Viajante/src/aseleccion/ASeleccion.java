package aseleccion;

import implementation.Cromosoma;

//http://www.dca.fee.unicamp.br/~gomide/courses/EA072/artigos/Genetic_Algorithm_TSPR_eview_Larranaga_1999.pdf
//Trabajo que contiene bastante de lo que hacemos.

public interface ASeleccion
{
	public void seleccionar(Cromosoma[] poblacion, int[] aptitudes, Cromosoma[] seleccionados);
}
