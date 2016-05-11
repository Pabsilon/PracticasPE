package gui;

import implementacion.Tablero;

public class Controller {
	
	private GUI g;
	
	public Controller(){
		g = new GUI(this);
		Tablero t = new Tablero();
		actualizaMapa(t);
	}
	
	public void comenzarSimulacion (String cruce, String mutacion, String probMutacion, String seleccion){
		
	}
	
	public void actualizaMapa(Tablero t){
		for (int i=0; i<32; i++){
			for (int j = 0; j<32; j++){
				if (t.getValue(i, j).equalsIgnoreCase("Comida")){
					g.setComida(i, j);
				}
			}
		}
	}

}
