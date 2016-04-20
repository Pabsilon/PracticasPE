package gui;

import acruce.ACruce;
import amutacion.AMutacion;
import aseleccion.ASeleccion;
import gui.GUI;
import implementation.Fabrica_ACruce;
import implementation.Fabrica_AMutacion;
import implementation.Fabrica_ASeleccion;
import implementation.Traveler;

public class Controller {
	
	private GUI g;
	
	/**
	 * Builder del controlador y de la gui.
	 */
	public Controller() {
		g = new GUI(this);
	}

	void startSimulation(String poblacion, String generaciones, String metodoSeleccion, boolean elitismo, String porcentageCruce, String metodoCruce, String participantes, String porcentageMutacion, String metodoMutacion, String semilla) {
		
		long timeAgo = System.currentTimeMillis();
		//Parseo del algoritmo de selecci�n
		ASeleccion ags = Fabrica_ASeleccion.generarAlgoritmoSeleccion(metodoSeleccion, Integer.parseInt(participantes));
		//Parseo del Algoritmo de cruce
		ACruce agc = Fabrica_ACruce.generarAlgoritmoCruce(metodoCruce);
		//Parseo del algoritmo de mutacion
		AMutacion agm = Fabrica_AMutacion.generarAlgoritmoMutacion(metodoMutacion);
		
		
		//Creacion del algoritmo que vamos a ejecutar.
		//Algoritmo ag = new Algoritmo(Integer.parseInt(poblacion), Integer.parseInt(iteraciones), Long.parseLong(semilla), agc,agm,ags,Float.parseFloat(mutacion),elitismo);
		Traveler algoritmo = new Traveler(Integer.parseInt(poblacion), Integer.parseInt(generaciones), Long.parseLong(semilla), agc,agm,ags, Float.parseFloat(porcentageCruce), Float.parseFloat(porcentageMutacion),elitismo);
		
		double[] mejorAbsoluto = new double[Integer.parseInt(generaciones)];
		double[] mejorGeneracion = new double[Integer.parseInt(generaciones)];
		double[] mediaGeneracion = new double[Integer.parseInt(generaciones)];
		
		String resultado;
		
		resultado = algoritmo.ejecutarAlgoritmo(mejorAbsoluto,mejorGeneracion,mediaGeneracion);
		//Rellenamos la grafica con los valores.
		g.fillPlot(mejorAbsoluto, mejorGeneracion, mediaGeneracion, Integer.parseInt(generaciones), resultado);
		//Mostramos la semilla utilizada.
		g.setSeed(algoritmo.getSemilla());
		//Mostramos el tiempo de ejecucion
		timeAgo = System.currentTimeMillis() - timeAgo;
		g.setTime((float)timeAgo/1000);		
	}

}
