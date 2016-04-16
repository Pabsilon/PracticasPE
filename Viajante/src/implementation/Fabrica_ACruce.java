package implementation;

import acruce.ACruce;
import acruce.ACruce_CiclosCX;
import acruce.ACruce_CodOrdinal;
import acruce.ACruce_OX;
import acruce.ACruce_PMX;
import acruce.ACruce_Propio;
import acruce.ACruce_RecRutasERX;
import acruce.ACruce_VariantesOX;

public class Fabrica_ACruce {
	
	public static ACruce generarAlgoritmoCruce(String algoritmo){
		//{"PMX", "OX", "Variantes OX", "Ciclos (CX)", "Rec.Rutas (ERX)", "Cod. Ordinal", "Propio"};
		switch(algoritmo){
		case "PMX":
			return new ACruce_PMX();
		case "OX":
			return new ACruce_OX();
		case "VariantesOX":
			return new ACruce_VariantesOX();
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
