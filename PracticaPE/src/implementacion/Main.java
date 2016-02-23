package implementacion;
import especificos.*;

public class Main {

	public static void main(String[] args) {
		Cromosoma crom[] = new Cromosoma[10];
		for (int i = 0; i<10; i++){
			crom[i]=new Problema5(0.01f);
			System.out.print(crom[i].getFenotipo()[0]);
			System.out.print(" ");
			System.out.print(crom[i].getFenotipo()[1]);
			System.out.print(" ");
			System.out.println(crom[i].getAptitud());
		}
		
		

	}

}
