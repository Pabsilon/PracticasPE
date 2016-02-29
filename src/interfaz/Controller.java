package interfaz;

import implementacion.Algoritmo;

public class Controller {
	
	private GUI g;
	
	public Controller() {
		g = new GUI(this);
	}
	
	void comenzarSimulacion(String precision, String cruce, String mutacion, String metodoSelec, String problema, int numPoblacion, int numIteraciones)
	{
		//TODO: El último parámetro de la función es la n para el problema 4. De momento está a 0, pero hay que parsearlo desde la interfaz.
		//Crear un nuevo algoritmo
		Algoritmo ag = new Algoritmo(numPoblacion, Float.parseFloat(precision), Float.parseFloat(cruce) / 100, Float.parseFloat(mutacion) / 100, metodoSelec, problema, numIteraciones, 1);
		
		double[] mejorAbsoluto = new double[100];
		double[] mejorGeneracion = new double[100];
		double[] mediaGeneracion = new double[100];
		
		//Ejecutar el algoritmo
		String resultado;
		resultado = ag.simular(mejorAbsoluto, mejorGeneracion, mediaGeneracion);
		
		//Mostrar resultados
		g.fillPlot(mejorAbsoluto, mejorGeneracion, mediaGeneracion, numIteraciones, resultado);
	}

}
