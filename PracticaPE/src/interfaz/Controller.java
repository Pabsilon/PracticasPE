package interfaz;

import implementacion.Algoritmo;

public class Controller {
	
	private GUI g;
	
	public Controller() {
		g = new GUI(this);
	}
	
	void comenzarSimulacion(String precision, String cruce, String mutacion, String metodoSelec, String problema)
	{
		//TODO: El último parámetro de la función es la n para el problema 4. De momento está a 0, pero hay que parsearlo desde la interfaz.
		Algoritmo ag = new Algoritmo(100, Float.parseFloat(precision), Float.parseFloat(cruce) / 100, Float.parseFloat(mutacion) / 100, metodoSelec, problema, 100, 0);
		
		double[] mejorAbsoluto = new double[100];
		double[] mejorGeneracion = new double[100];
		double[] mediaGeneracion = new double[100];
		
		String resultado;
		resultado = ag.simular(mejorAbsoluto, mejorGeneracion, mediaGeneracion);
		
		g.fillPlot(mejorAbsoluto, mejorGeneracion, mediaGeneracion, 100, resultado);
	}

}
