package interfaz;

import implementacion.Algoritmo;

public class Controller {
	
	private GUI g;
	
	public Controller() {
		g = new GUI(this);
	}
	
	void comenzarSimulacion(String precision, String cruce, String mutacion, String metodoSelec, String problema, String numPoblacion, String numGeneraciones, String semilla, String n, String participantes)
	{
		//TODO: El último parámetro de la función es la n para el problema 4. De momento está a 0, pero hay que parsearlo desde la interfaz.
		//Crear un nuevo algoritmo
		Algoritmo ag = new Algoritmo(Integer.parseInt(numPoblacion), Float.parseFloat(precision), Float.parseFloat(cruce) / 100, Float.parseFloat(mutacion) / 100, metodoSelec, problema, Integer.parseInt(numGeneraciones), Long.parseLong(semilla), Integer.parseInt(n), Integer.parseInt(participantes));
		
		double[] mejorAbsoluto = new double[Integer.parseInt(numGeneraciones)];
		double[] mejorGeneracion = new double[Integer.parseInt(numGeneraciones)];
		double[] mediaGeneracion = new double[Integer.parseInt(numGeneraciones)];
		
		//Ejecutar el algoritmo
		String resultado;
		resultado = ag.simular(mejorAbsoluto, mejorGeneracion, mediaGeneracion);
		
		//Mostrar resultados
		g.fillPlot(mejorAbsoluto, mejorGeneracion, mediaGeneracion, Integer.parseInt(numGeneraciones), resultado);
	}

}
