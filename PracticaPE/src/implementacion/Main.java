package implementacion;
import especificos.*;

public class Main {

	public static void main(String[] args) {
		Cromosoma crom[] = new Cromosoma[10];
		for (int i = 0; i<10; i++){
			crom[i]=new Problema1(0.01f);
			System.out.println(crom[i].getAptitud());
		}
		
		

	}

}
