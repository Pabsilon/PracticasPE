package aseleccion;

import implementation.Cromosoma;

//Al igual que en ruleta, se asignan las puntuaciones acumuladas.
//En vez de elegir con un número random, se utiliza un "salto" fijo.

public class ASeleccion_Restos implements ASeleccion {

	@Override
	public void seleccionar(Cromosoma[] poblacion, float[] aptitudes, Cromosoma[] seleccionados) {
		//Generar puntuaciones acumuladas
				float puntuaciones[] = new float[poblacion.length];
				float puntuacionesAcum[] = new float[poblacion.length];
				int sumaAptitudes = 0;
				for(int i = 0; i < poblacion.length; i++)
				{
					sumaAptitudes += aptitudes[i];
				}
				puntuaciones[0] = aptitudes[0] / sumaAptitudes;
				puntuacionesAcum[0] = puntuaciones[0];
				for(int i = 1; i < poblacion.length; i++)
				{
					puntuaciones[i] = aptitudes[i] / sumaAptitudes;
					puntuacionesAcum[i] = puntuaciones[i] + puntuacionesAcum[i - 1];
				}
				puntuacionesAcum[poblacion.length - 1] = 1; //Asegurarse que el ultimo valor de las puntuaciones es 1
				
				//Selección
				
				float distance = 1/seleccionados.length;
				float interval = 0;
				//TODO Siempre se van a seleccionar menos.
				// Hay que seleccionar los que faltan con otro algoritmo, sobretodo ruleta.
				for (int i = 0; i<poblacion.length; i++){
					
					interval += distance;
					int seleccionado = 0;
					while(interval > puntuacionesAcum[seleccionado])
					{
						seleccionado++;
					}
					
					seleccionados[i].copiarCromosoma(poblacion[seleccionado]);
					
				}
	}

}
