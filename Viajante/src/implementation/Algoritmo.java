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
	private float _probMut;
	private boolean _elitismo;
	private Cromosoma _mejorIndividuo;
	
	public Algoritmo(int poblacion, int simulaciones, long semilla, ACruce agc, AMutacion agm, ASeleccion ags, float probMut, boolean elitismo){
		_poblacion = poblacion;
		_simulaciones = simulaciones;
		_rand=new Random();
		_probMut = probMut;
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
		Traveler t = new Traveler(_poblacion);
		t.ejecutarAlgoritmo(_simulaciones, _ags, _agc, _agm, _probMut, _rand);
		
		return _mejorIndividuo.toString();
	}

	public long getSeed() {
		// TODO Auto-generated method stub
		return 0;
	}

}
