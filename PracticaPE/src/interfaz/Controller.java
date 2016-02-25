package interfaz;

import implementacion.Algoritmo;
import implementacion.AlgoritmoMin;

public class Controller {
	
	private GUI g;
	
	public Controller() {
		g = new GUI(this);
	}
	
	void comenzarSimulacion(String precision, String cruce, String mutacion, String metodoSelec, String problema)
	{
		//Cambiar esto (Fabrica para algoritmos min y max)
		Algoritmo ag = new AlgoritmoMin(100, Float.parseFloat(precision), Float.parseFloat(cruce) / 100, Float.parseFloat(mutacion) / 100, metodoSelec, problema, 100);
		
		double[] mejorAbsoluto = new double[100];
		double[] mejorGeneracion = new double[100];
		double[] mediaGeneracion = new double[100];
		
		String resultado;
		resultado = ag.simular(mejorAbsoluto, mejorGeneracion, mediaGeneracion);
		
		g.fillPlot(mejorAbsoluto, mejorGeneracion, mediaGeneracion, 100, resultado);
	}

}
