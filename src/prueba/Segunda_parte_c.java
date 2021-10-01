package prueba;

import modelo.Escenario;

public class Segunda_parte_c {

	public static void main(String[] args) {

		Escenario escenario_1 = new Escenario(5);
		
		escenario_1.probabilidad_Independiente();
		escenario_1.getFuente().calcula_cantInformacion();
		escenario_1.getFuente().calcula_entropia();
		escenario_1.longitud_media();
		escenario_1.getFuente().setRendimiento(escenario_1.getFuente().getEntropia() / escenario_1.getFuente().getLongitud_media());
		
		Escenario escenario_2 = new Escenario(7);
		
		escenario_2.probabilidad_Independiente();
		escenario_2.getFuente().calcula_cantInformacion();
		escenario_2.getFuente().calcula_entropia();
		escenario_2.longitud_media();
		escenario_2.getFuente().setRendimiento(escenario_2.getFuente().getEntropia() / escenario_2.getFuente().getLongitud_media());
		
		Escenario escenario_3 = new Escenario(9);
		
		escenario_3.probabilidad_Independiente();
		escenario_3.getFuente().calcula_cantInformacion();
		escenario_3.getFuente().calcula_entropia();
		escenario_3.longitud_media();
		escenario_3.getFuente().setRendimiento(escenario_3.getFuente().getEntropia() / escenario_3.getFuente().getLongitud_media());
		
		StringBuilder resultado = new StringBuilder();
		resultado.append("ESCENARIO: Palabras codigo de 5 digitos\n\n");
		resultado.append("RENDIMIENTO: "+ (double)Math.round((escenario_1.getFuente().getRendimiento()*100) * 100000d) / 100000d  + " %\n");
		resultado.append("REDUNDANCIA: "+ (double)Math.round(((1 - escenario_1.getFuente().getRendimiento())*100) * 100000d) / 100000d + " %\n");
		
		resultado.append("\n\n---------------------------------------------------------------------------------------------------\n\n");
		
		resultado.append("ESCENARIO: Palabras codigo de 7 digitos\n\n");
		resultado.append("RENDIMIENTO: "+ (double)Math.round((escenario_2.getFuente().getRendimiento()*100) * 100000d) / 100000d  + " %\n");
		resultado.append("REDUNDANCIA: "+ (double)Math.round(((1 - escenario_2.getFuente().getRendimiento())*100) * 100000d) / 100000d + " %\n");
		
		resultado.append("\n\n---------------------------------------------------------------------------------------------------\n\n");
		
		resultado.append("ESCENARIO: Palabras codigo de 9 digitos\n\n");
		resultado.append("RENDIMIENTO: "+ (double)Math.round((escenario_3.getFuente().getRendimiento()*100) * 100000d) / 100000d  + " %\n");
		resultado.append("REDUNDANCIA: "+ (double)Math.round(((1 - escenario_3.getFuente().getRendimiento())*100) * 100000d) / 100000d + " %\n");
		
		escenario_1.writeFile("Segunda_parte_c.txt", resultado.toString());

	}

}
