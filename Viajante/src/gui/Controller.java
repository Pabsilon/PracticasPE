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
		//Parseo del algoritmo de selección
		ASeleccion ags = Fabrica_ASeleccion.generarAlgoritmoSeleccion(metodoSeleccion, Integer.parseInt(participantes));
		//Parseo del Algoritmo de cruce
		ACruce agc = Fabrica_ACruce.generarAlgoritmoCruce(metodoCruce);
		//Parseo del algoritmo de mutacion
		AMutacion agm = Fabrica_AMutacion.generarAlgoritmoMutacion(metodoMutacion);
		//Creacion del algoritmo que vamos a ejecutar.
		Algoritmo ag = new Algoritmo(Integer.parseInt(poblacion), Integer.parseInt(iteraciones), Long.parseLong(semilla), agc,agm,ags,elitismo);
		
		double[] mejorAbsoluto = new double[Integer.parseInt(iteraciones)];
		double[] mejorGeneracion = new double[Integer.parseInt(iteraciones)];
		double[] mediaGeneracion = new double[Integer.parseInt(iteraciones)];
		
		String resultado;
		
		resultado = ag.simular(mejorAbsoluto,mejorGeneracion,mediaGeneracion);
		//Rellenamos la grafica con los valores.
		g.fillPlot(mejorAbsoluto, mejorGeneracion, mediaGeneracion, Integer.parseInt(iteraciones), resultado);
		//Mostramos la semilla utilizada.
		g.setSeed(ag.getSeed());
		//Mostramos el tiempo de ejecucion
		timeAgo = System.currentTimeMillis() - timeAgo;
		g.setTime((float)timeAgo/1000);		
	}

}
