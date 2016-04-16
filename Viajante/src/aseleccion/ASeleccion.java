package aseleccion;

import implementation.Cromosoma;

public interface ASeleccion
{
	public void seleccionar(Cromosoma[] poblacion, float[] aptitudes, Cromosoma[] seleccionados);
}
