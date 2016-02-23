package implementacion;

import java.util.Random;

public class Gen {

	private boolean[] _alelo;
	
	public Gen(int n){
		
		_alelo = new boolean[n];
		Random rand = new Random();
		for (int i = 0; i<n; i++){
			_alelo[i]=rand.nextBoolean();
		}
	}
	
	public boolean[] getAlelos(){
		return _alelo;
	}
	
	public void setAlelos(boolean[] alelo){
		_alelo = alelo;
	}
	
}
