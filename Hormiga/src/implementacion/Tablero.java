package implementacion;

/*
@ # # # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # # # 0 0 0 0 
0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 # 0 0 
0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 # 0 0 
0 0 0 # # # # 0 # # # # # 0 0 0 0 0 0 0 0 # # 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 # 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 # 0 0 0 0 0 # # # 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 # 0 0 # 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 # 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 # 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 
0 0 0 # # 0 0 # # # # # 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 # 0 0 0 0 0 0 # # # # # # # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 # 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 # # # # 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
 */


public class Tablero {
	
	public static final int _sizeT = 32;
	
	private final String[][] _mapa;
	
	public Tablero(){
		_mapa = new String[_sizeT][_sizeT];
		
		for (int i = 0; i<_sizeT; i++){
			for (int j = 0; j<_sizeT; j++){
				_mapa[i][j] = "Vacio";
			}
		}
		
		for (int i = 0; i<4; i++){
			_mapa[i][0] = "Comida";
		}
		
		for (int i = 1; i<5; i++){
			_mapa[3][i] = "Comida";
		}
		
		for (int i = 3; i< 7; i++){
			_mapa[i][5] = "Comida";
		}
		
		for (int i = 8; i<13; i++){
			_mapa[i][5] = "Comida";
		}
		
		for (int i = 6;  i< 11; i++){
			_mapa[12][i] = "Comida";
		}
		
		for (int i = 12; i<16; i++){
			_mapa[12][i] = "Comida";
		}
		
		for (int i = 18; i<24; i++){
			_mapa[12][i] = "Comida";
		}
		
		for (int i = 7; i<12; i++){
			_mapa[i][24] = "Comida";
		}
		
		for (int i = 3; i<5; i++){
			_mapa[i][24] = "Comida";
		}
		
		for (int i = 25; i<29; i++){
			_mapa[1][i] = "Comida";
		}
		
		for (int i = 2; i<6; i++){
			_mapa[i][30] = "Comida";
		}
		
		for (int i = 28; i<30; i++){
			_mapa[7][i] = "Comida";
		}
		
		for (int i = 8; i<15; i++){
			_mapa[i][27] = "Comida";
		}
		
		for (int i = 24; i<27; i++){
			_mapa[16][i]= "Comida";
		}
		
		_mapa[16][21] = "Comida";
		
		for (int i = 17; i<20; i++){
			_mapa[16][i] = "Comida";
		}
		
		_mapa[17][16]= "Comida";
		
		for (int i = 14; i<16; i++){
			_mapa[20][i] = "Comida";
		}
		
		for (int i = 8; i<12; i++){
			_mapa[20][i] = "Comida";
		}
		
		for (int i = 21; i<23; i++){
			_mapa[i][5] = "Comida";
		}
		
		for (int i = 3; i<5; i++){
			_mapa[24][i] = "Comida";
		}
		
		for (int i = 25; i<28; i++){
			_mapa[i][2] = "Comida";
		}
		
		for (int i = 3; i<5; i++){
			_mapa[29][i] = "Comida";
		}
		
		_mapa[29][6] = "Comida";
		_mapa[29][9] = "Comida";
		_mapa[29][12] = "Comida";
		
		for (int i = 26; i< 29; i++){
			_mapa[i][14] = "Comida";
		}
		
		_mapa[23][15] = "Comida";
		_mapa[24][18] = "Comida";
		_mapa[27][19] = "Comida";
		_mapa[26][22] = "Comida";
		_mapa[23][23] = "Comida";
	}
	
	public String getValue(int x, int y){
		if((x < 0) || (x >= _sizeT) || (y < 0) || (y >= _sizeT))
		{
			return "Vacio";
		}
		return _mapa[x][y];
	}

	public void pasoHormiga(int x, int y)
	{
		if((x < 0) || (x >= _sizeT) || (y < 0) || (y >= _sizeT))
		{
			return;
		}
		
		if(_mapa[x][y].equals("Comida"))
		{
			_mapa[x][y] = "Comido";
		}
		else if(_mapa[x][y].equals("Vacio"))
		{
			_mapa[x][y] = "Hormiga";
		}
	}

}
