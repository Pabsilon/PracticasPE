package gui;

import acruce.ACruce;
import acruce.ACruce_Intercambio;
import amutacion.AMutacion;
import aseleccion.ASeleccion;
import implementacion.Algoritmo;
import implementacion.Fabrica_AMutacion;
import implementacion.Fabrica_ASeleccion;
import implementacion.Hormiga;
import implementacion.Tablero;

public class Controller {
	
	private GUI g;
	
	public Controller(){
		g = new GUI(this);
		Tablero t = new Tablero();
		actualizaMapa(t);
	}
	
	public void comenzarSimulacion (String poblacion, String generaciones, String torneoParticipantes, String metodoCruce, String probCruece, String metodoMutacion, String probMutacion, String metodoSeleccion, boolean elitismo, String semilla)
	{
		long timeAgo = System.currentTimeMillis();
		//Parseo del algoritmo de selecci�n
		ASeleccion ags = Fabrica_ASeleccion.generarAlgoritmoSeleccion(metodoSeleccion, Integer.parseInt(torneoParticipantes));
		//Parseo del algoritmo de mutacion
		AMutacion agm = Fabrica_AMutacion.generarAlgoritmoMutacion(metodoMutacion);
		
		
		//Creacion del algoritmo que vamos a ejecutar.
		//Algoritmo ag = new Algoritmo(Integer.parseInt(poblacion), Integer.parseInt(iteraciones), Long.parseLong(semilla), agc,agm,ags,Float.parseFloat(mutacion),elitismo);
		Algoritmo algoritmo = new Algoritmo(Integer.parseInt(poblacion), Integer.parseInt(generaciones), Long.parseLong(semilla), new ACruce_Intercambio(), agm,ags, Float.parseFloat(probCruece), Float.parseFloat(probMutacion),elitismo);
		
		double[] mejorAbsoluto = new double[Integer.parseInt(generaciones)];
		double[] mejorGeneracion = new double[Integer.parseInt(generaciones)];
		double[] mediaGeneracion = new double[Integer.parseInt(generaciones)];
		
		Hormiga resultado;		
		resultado = algoritmo.ejecutarAlgoritmo(mejorAbsoluto,mejorGeneracion,mediaGeneracion);
		//Rellenamos la grafica con los valores.
		g.fillPlot(mejorAbsoluto, mejorGeneracion, mediaGeneracion, Integer.parseInt(generaciones), resultado.getFenotipo());
		//Dibujar mapa
		g.drawHormiga(resultado);
		//Mostramos la semilla utilizada.
		//g.setSeed(algoritmo.getSemilla());
		//Mostramos el tiempo de ejecucion
		timeAgo = System.currentTimeMillis() - timeAgo;
		//g.setTime((float)timeAgo/1000);
	}
	
	public void actualizaMapa(Tablero t){
		for (int i=0; i<32; i++){
			for (int j = 0; j<32; j++){
				if (t.getValue(i, j).equalsIgnoreCase("Comida")){
					g.setComida(i, j);
				}
			}
		}
	}

}
