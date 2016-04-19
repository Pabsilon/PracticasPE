package implementation;

import java.util.Random;

import acruce.ACruce;
import amutacion.AMutacion;
import aseleccion.ASeleccion;

public class Algoritmo {
	
	private int _poblacion;
	private int _simulaciones;
	private long _semilla;
	private Random _rand;
	private ACruce _agc;
	private AMutacion _agm;
	private ASeleccion _ags;
	private boolean _elitismo;
	
	public Algoritmo(int poblacion, int simulaciones, long semilla, ACruce agc, AMutacion agm, ASeleccion ags, boolean elitismo){
		_poblacion = poblacion;
		_simulaciones = simulaciones;
		_rand=new Random();
		if (semilla !=0){
			_rand.setSeed(semilla);
		}else{
			_semilla = System.currentTimeMillis();
			_rand = new Random(_semilla);
		}
		_agc = agc;
		_agm = agm;
		_ags = ags;
		_elitismo = elitismo;
	}

	public String simular(double[] mejorAbsoluto, double[] mejorGeneracion, double[] mediaGeneracion) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getSeed() {
		// TODO Auto-generated method stub
		return 0;
	}

}
