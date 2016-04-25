package gui;

import acruce.ACruce;
import amutacion.AMutacion;
import aseleccion.ASeleccion;
import gui.GUI;
import implementation.Cromosoma;
import implementation.Fabrica_ACruce;
import implementation.Fabrica_AMutacion;
import implementation.Fabrica_ASeleccion;
import implementation.Traveler;

/** Implementacion del controlador
 * @author Pablo Mac-Veigh, Jorge Sanchez
 *
 */
public class Controller {
	
	private GUI g;
	
	/**
	 * Builder del controlador y de la gui.
	 */
	public Controller() {
		g = new GUI(this);
	}

	/** Lanza la simulacion y se encarga de mostrarla por la gui.
	 * @param poblacion String de la poblacion total
	 * @param generaciones String del numero de generaciones
	 * @param metodoSeleccion String del metodo de seleccion
	 * @param elitismo Boolean sobre si usamos elitismo
	 * @param porcentageCruce String del porcentaje de cruce
	 * @param metodoCruce String del Metodo de cruce
	 * @param participantes String del numero de participantes en el cruce (en caso de torneo)
	 * @param porcentageMutacion String del porcentaje de participacion
	 * @param metodoMutacion String del metodo de mutacion
	 * @param semilla String de la semilla (para generar la misma poblacion inicial)
	 */
	void startSimulation(String poblacion, String generaciones, String metodoSeleccion, boolean elitismo, String porcentageCruce, String metodoCruce, String participantes, String porcentageMutacion, String metodoMutacion, String semilla, boolean intervaloCruce, boolean intervaloMutacion, boolean intervaloGeneraciones, int parametrosIntervalo[][]) {
		
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
		
		Cromosoma resultado;		
		resultado = algoritmo.ejecutarAlgoritmo(mejorAbsoluto,mejorGeneracion,mediaGeneracion);
		//Rellenamos la grafica con los valores.
		g.fillPlot(mejorAbsoluto, mejorGeneracion, mediaGeneracion, Integer.parseInt(generaciones), "Distancia: " +resultado.getAptitud() + "\nRecorrido: " + resultado.getFenotipo());
		//Dibujar mapa
		g.drawCities(resultado.getGenotipo());
		//Mostramos la semilla utilizada.
		g.setSeed(algoritmo.getSemilla());
		//Mostramos el tiempo de ejecucion
		timeAgo = System.currentTimeMillis() - timeAgo;
		g.setTime((float)timeAgo/1000);
		
		//Parte opcional
		if(intervaloCruce)
		{
			double opcional[] = new double[Integer.parseInt(generaciones)];
			int k = 0;
			for(int i = parametrosIntervalo[0]; i < parametrosIntervalo[1]; i = i + parametrosIntervalo[3])
			{
				mejorAbsoluto = new double[Integer.parseInt(generaciones)];
				algoritmo = new Traveler(Integer.parseInt(poblacion), Integer.parseInt(generaciones), Long.parseLong(semilla), agc,agm,ags, i, Float.parseFloat(porcentageMutacion),elitismo);
				opcional[k] = mejorAbsoluto[mejorAbsoluto.length - 1];
			}
			
			g.fillPlotOpcional(opcional);
		}
		else if(intervaloMutacion)
		{
			double opcional[] = new double[Integer.parseInt(generaciones)];
			int k = 0;
			for(int i = parametrosIntervalo[0]; i < parametrosIntervalo[1]; i = i + parametrosIntervalo[3])
			{
				mejorAbsoluto = new double[Integer.parseInt(generaciones)];
				algoritmo = new Traveler(Integer.parseInt(poblacion), Integer.parseInt(generaciones), Long.parseLong(semilla), agc,agm,ags, i, Float.parseFloat(porcentageMutacion),elitismo);
				opcional[k] = mejorAbsoluto[mejorAbsoluto.length - 1];
			}
			
			g.fillPlotOpcional(opcional);
		}
		else if(intervaloGeneraciones)
		{
			double opcional[] = new double[parametrosIntervalo[1]];
			int k = 0;
			for(int i = parametrosIntervalo[0]; i < parametrosIntervalo[1]; i = i + parametrosIntervalo[3])
			{
				mejorAbsoluto = new double[i];
				algoritmo = new Traveler(Integer.parseInt(poblacion), i, Long.parseLong(semilla), agc,agm,ags, i, Float.parseFloat(porcentageMutacion),elitismo);
				opcional[k] = mejorAbsoluto[mejorAbsoluto.length - 1];
			}
			
			g.fillPlotOpcional(opcional);
		}
	}

}
