package implementation;

import acruce.ACruce;
import acruce.ACruce_CiclosCX;
import acruce.ACruce_CodOrdinal;
import acruce.ACruce_OX;
import acruce.ACruce_OX_OP;
import acruce.ACruce_OX_PP;
import acruce.ACruce_PMX;
import acruce.ACruce_Propio;
import acruce.ACruce_RecRutasERX;

/** Fabrica de algoritmos de cruce
 * @author Pablo Mac-Veigh
 *
 */
public class Fabrica_ACruce {
	
	/** La faibrica de cruces
	 * @param algoritmo String con el nombre del algoritmo
	 * @return El objeto inicializado
	 */
	public static ACruce generarAlgoritmoCruce(String algoritmo){
		//{"PMX", "OX", "Variantes OX", "Ciclos (CX)", "Rec.Rutas (ERX)", "Cod. Ordinal", "Propio"};
		switch(algoritmo){
		case "PMX":
			return new ACruce_PMX();
		case "OX":
			return new ACruce_OX();
		case "OX_PP":
			return new ACruce_OX_PP();
		case "OX_OP":
			return new ACruce_OX_OP();
		case "Ciclos (CX)":
			return new ACruce_CiclosCX();
		case "Rec.Rutas (ERX)":
			return new ACruce_RecRutasERX();
		case "Cod. Ordinal":
			return new ACruce_CodOrdinal();
		case "Propio":
			return new ACruce_Propio();
		default: return null;
		}
	}

}
