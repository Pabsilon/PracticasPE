package gui;

import gui.GUI;
import implementation.ACruce;
import implementation.AMutacion;
import implementation.ASeleccion;
import implementation.Fabrica_ACruce;
import implementation.Fabrica_AMutacion;
import implementation.Fabrica_ASeleccion;

public class Controller {
	
	private GUI g;
	
	/**
	 * Builder del controlador y de la gui.
	 */
	public Controller() {
		g = new GUI(this);
	}

	void startSimulation(String poblacion, String iteraciones, String metodoSeleccion, boolean elitismo, String cruce, String metodoCruce, String participantes, String mutacion, String metodoMutacion, String semilla) {
		
		long timeAgo = System.currentTimeMillis();
		ASeleccion ags = Fabrica_ASeleccion.generarAlgoritmoSeleccion(metodoSeleccion, Integer.parseInt(participantes));
		ACruce agc = Fabrica_ACruce.generarAlgoritmoCruce(metodoCruce);
		AMutacion agm = Fabrica_AMutacion.generarAlgoritmoMutacion(metodoMutacion);
		
		
		
		timeAgo = System.currentTimeMillis() - timeAgo;
		g.setTime((float)timeAgo/1000);		
	}

}
