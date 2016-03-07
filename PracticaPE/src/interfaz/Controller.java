package interfaz;

import implementacion.Algoritmo;

public class Controller {
	
	private GUI g;
	
	public Controller() {
		g = new GUI(this);
	}
	
	void comenzarSimulacion(String precision, String cruce, String mutacion, String metodoSelec, Boolean elitismo, String problema, String numPoblacion, String numGeneraciones, String semilla, String n, String participantes)
	{
		//Parseo y construccion del nuevo algoritmo.
		
		long timeAgo = System.currentTimeMillis();
		Algoritmo ag = new Algoritmo(Integer.parseInt(numPoblacion), Float.parseFloat(precision), Float.parseFloat(cruce) / 100, Float.parseFloat(mutacion) / 100, metodoSelec, elitismo, problema, Integer.parseInt(numGeneraciones), Long.parseLong(semilla), Integer.parseInt(n), Integer.parseInt(participantes));
		
		double[] mejorAbsoluto = new double[Integer.parseInt(numGeneraciones)];
		double[] mejorGeneracion = new double[Integer.parseInt(numGeneraciones)];
		double[] mediaGeneracion = new double[Integer.parseInt(numGeneraciones)];
		
		//Ejecución del algoritmo
		String resultado;
		resultado = ag.simular(mejorAbsoluto, mejorGeneracion, mediaGeneracion);
		
		//Mostrar resultados
		g.fillPlot(mejorAbsoluto, mejorGeneracion, mediaGeneracion, Integer.parseInt(numGeneraciones), resultado);
		//Mostrar la semilla utilizada
		g.setSeed(ag.getSemilla());
		timeAgo = System.currentTimeMillis() - timeAgo;
		g.setTime((float)timeAgo/1000);
	}

}
