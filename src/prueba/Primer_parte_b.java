package prueba;

import java.util.Iterator;

import modelo.Escenario;
import modelo.Simbolo;

public class Primer_parte_b {

	public static void main(String[] args) {

		Escenario escenario = new Escenario(2);
		
		escenario.probabilidad_Condicional();
		
		StringBuilder resultado = new StringBuilder();
		resultado.append(escenario);
		Iterator<Simbolo> it = escenario.getFuente().getSimbolos().iterator();
		while(it.hasNext()) {
			Simbolo s = (Simbolo) it.next();
			resultado.append("S"+ s.getId() +" -- Codigo: "+ s.getCodigo() +"\n");
		}
		
		resultado.append("\nMATRIZ DE PROBABILIDADES CONDICIONALES: \n\n");
		resultado.append("   S1\t\t   S2\t\t   S3\t\t   S4\n\n");
		for(int j=0; j < 4; j++) {
			for(int i=0; i < escenario.getFuente().getSimbolos().size(); i++) {
				Simbolo s = (Simbolo) escenario.getFuente().getSimbolos().get(i);
				resultado.append((double)Math.round(s.getProb_condicional()[j] * 100000d) / 100000d + "\t\t");
			}
			resultado.append("S"+ (j+1) + "\n");
		}
		
		resultado.append("\nFUENTE DE MEMORIA NULA");
		escenario.writeFile("Primer_parte_b.txt", resultado.toString());
	}

}
