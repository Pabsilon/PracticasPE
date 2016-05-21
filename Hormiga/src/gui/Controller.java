package gui;

import abloating.ABloating;
import acruce.ACruce_Intercambio;
import amutacion.AMutacion;
import aseleccion.ASeleccion;
import implementacion.Algoritmo;
import implementacion.Fabrica_ABloating;
import implementacion.Fabrica_AMutacion;
import implementacion.Fabrica_ASeleccion;
import implementacion.Hormiga;
import implementacion.Tablero;

public class Controller {
	
	private GUI g;
	
	public Controller(){
		g = new GUI(this);
	}
	
	public void comenzarSimulacion (String poblacion, String generaciones, String torneoParticipantes, String metodoCruce, String probCruece, String metodoMutacion, String probMutacion, String metodoSeleccion, boolean elitismo, String semilla, boolean bloating, String metodoBloating, String profundidad)
	{		
		long timeAgo = System.currentTimeMillis();
		//Parseo del algoritmo de selecci�n
		ASeleccion ags = Fabrica_ASeleccion.generarAlgoritmoSeleccion(metodoSeleccion, Integer.parseInt(torneoParticipantes));
		//Parseo del algoritmo de mutacion
		AMutacion agm = Fabrica_AMutacion.generarAlgoritmoMutacion(metodoMutacion);
		
		ABloating agb = Fabrica_ABloating.generarAlgoritmoBloating(metodoBloating);
		
		
		//Creacion del algoritmo que vamos a ejecutar.
		//Algoritmo ag = new Algoritmo(Integer.parseInt(poblacion), Integer.parseInt(iteraciones), Long.parseLong(semilla), agc,agm,ags,Float.parseFloat(mutacion),elitismo);
		Algoritmo algoritmo = new Algoritmo(Integer.parseInt(poblacion), Integer.parseInt(generaciones), Long.parseLong(semilla), new ACruce_Intercambio(), agm,ags,agb, bloating, Float.parseFloat(probCruece), Float.parseFloat(probMutacion),elitismo, Integer.parseInt(profundidad));
		
		double[] mejorAbsoluto = new double[Integer.parseInt(generaciones)];
		double[] mejorGeneracion = new double[Integer.parseInt(generaciones)];
		double[] mediaGeneracion = new double[Integer.parseInt(generaciones)];
		
		Hormiga resultado;		
		resultado = algoritmo.ejecutarAlgoritmo(mejorAbsoluto,mejorGeneracion,mediaGeneracion);

		//Rellenamos la grafica con los valores.
		g.fillPlot(mejorAbsoluto, mejorGeneracion, mediaGeneracion, resultado.getAptitud() + " " + resultado.getFenotipo());
		//Dibujar mapa
		g.actualizaMapa(resultado.getTableroRecorrido());
		//Mostramos la semilla utilizada.
		g.setSeed(algoritmo.getSemilla());
		//Mostramos el tiempo de ejecucion
		timeAgo = System.currentTimeMillis() - timeAgo;
		g.setTime((float)timeAgo/1000);
	}

}
