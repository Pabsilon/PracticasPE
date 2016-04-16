package gui;

import acruce.ACruce;
import amutacion.AMutacion;
import aseleccion.ASeleccion;
import gui.GUI;
import implementation.Algoritmo;
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
		Algoritmo ag = new Algoritmo(Integer.parseInt(poblacion), Integer.parseInt(iteraciones), Integer.parseInt(semilla), agc,agm,ags,elitismo);
		
		
		
		timeAgo = System.currentTimeMillis() - timeAgo;
		g.setTime((float)timeAgo/1000);		
	}

}
