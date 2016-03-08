package interfaz;

import implementacion.Algoritmo;

/**Clase controladora de la GUI
 * @author pabs
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
	
	/**Método de controlador que hace la llamada principal del programa.
	 * @param precision La precisión del problema.
	 * @param cruce La probabilidad de cruce.
	 * @param mutacion La probabilidad de mutación.
	 * @param metodoSelec El método de selección.
	 * @param elitismo Boolean para saber si usamos el elitismo.
	 * @param problema El nombre del problema.
	 * @param numPoblacion El total de población.
	 * @param numGeneraciones El número de generaciones.
	 * @param semilla La semilla con la que realizaremos la simulacion.
	 * @param n El número de parámetros para el problema 4.
	 * @param participantes El número de participantes en la selección tipo torneo.
	 */
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
