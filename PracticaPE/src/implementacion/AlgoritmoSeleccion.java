package implementacion;

import java.util.Random;

//Interfaz de los Algoritmos de la fase de seleccion
public interface AlgoritmoSeleccion 
{
	void seleccionar(float[] aptitudes, float[] puntuacionesAcumuladas, Cromosoma[] seleccionados, int tamPoblacion, Cromosoma[] poblacion, boolean minimizacion, Random rand);
}
