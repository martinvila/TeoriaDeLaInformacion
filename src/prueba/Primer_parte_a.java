package prueba;

import java.util.Iterator;

import modelo.Escenario;
import modelo.Simbolo;

public class Primer_parte_a {

public static void main(String[] args) {
		
		Escenario escenario_1 = new Escenario(5);
		
		escenario_1.probabilidad_Independiente();
		escenario_1.getFuente().calcula_cantInformacion();
		escenario_1.getFuente().calcula_entropia();
		
		Escenario escenario_2 = new Escenario(7);
		
		escenario_2.probabilidad_Independiente();
		escenario_2.getFuente().calcula_cantInformacion();
		escenario_2.getFuente().calcula_entropia();
		
		Escenario escenario_3 = new Escenario(9);
		
		escenario_3.probabilidad_Independiente();
		escenario_3.getFuente().calcula_cantInformacion();
		escenario_3.getFuente().calcula_entropia();
		
		StringBuilder resultado = new StringBuilder();
		resultado.append(escenario_1);
		Iterator<Simbolo> it = escenario_1.getFuente().getSimbolos().iterator();
		while(it.hasNext()) {
			Simbolo s = (Simbolo) it.next();
			resultado.append("S"+ s.getId() +" -- Codigo: "+ s.getCodigo() +"\t | \t Probabilidad: "+ (double)Math.round(s.getProbabilidad() * 100000d) / 100000d +"\t | \t Cant. de informacion: "+ (double)Math.round(s.getCantidad_informacion() * 100000d) / 100000d  + "\n");
		}
		resultado.append("\nENTROPIA: "+ (double)Math.round(escenario_1.getFuente().getEntropia() * 100000d) / 100000d);
		
		resultado.append("\n\n---------------------------------------------------------------------------------------------------\n\n");
		resultado.append(escenario_2);
		it = escenario_2.getFuente().getSimbolos().iterator();
		while(it.hasNext()) {
			Simbolo s = (Simbolo) it.next();
			resultado.append("S"+ s.getId() +" -- Codigo: "+ s.getCodigo() +"\t | \t Probabilidad: "+ (double)Math.round(s.getProbabilidad() * 100000d) / 100000d +"\t | \t Cant. de informacion: "+ (double)Math.round(s.getCantidad_informacion() * 100000d) / 100000d  + "\n");
		}
		resultado.append("\nENTROPIA: "+ (double)Math.round(escenario_2.getFuente().getEntropia() * 100000d) / 100000d);
		
		resultado.append("\n\n---------------------------------------------------------------------------------------------------\n\n");
		resultado.append(escenario_3);
		it = escenario_3.getFuente().getSimbolos().iterator();
		while(it.hasNext()) {
			Simbolo s = (Simbolo) it.next();
			resultado.append("S"+ s.getId() +" -- Codigo: "+ s.getCodigo() +"\t | \t Probabilidad: "+ (double)Math.round(s.getProbabilidad() * 100000d) / 100000d +"\t | \t Cant. de informacion: "+ (double)Math.round(s.getCantidad_informacion() * 100000d) / 100000d  + "\n");
		}
		resultado.append("\nENTROPIA: "+ (double)Math.round(escenario_3.getFuente().getEntropia() * 100000d) / 100000d);
		
		escenario_1.writeFile("Primer_parte_a.txt", resultado.toString());
	}

}
