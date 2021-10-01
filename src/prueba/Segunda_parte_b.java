package prueba;

import modelo.Escenario;

public class Segunda_parte_b {

	public static void main(String[] args) {
		
		Escenario escenario_1 = new Escenario(5);
		
		escenario_1.probabilidad_Independiente();
		escenario_1.longitud_media();
		
		Escenario escenario_2 = new Escenario(7);
		
		escenario_2.probabilidad_Independiente();
		escenario_2.longitud_media();
		
		Escenario escenario_3 = new Escenario(9);
		
		escenario_3.probabilidad_Independiente();
		escenario_3.longitud_media();
		
		
		StringBuilder resultado = new StringBuilder();
		resultado.append("ESCENARIO: Palabras codigo de 5 digitos\n\n");
		resultado.append("INECUACION DE KRAFT: "+ escenario_1.kraft_McMillan(2) + "\n");
		resultado.append("LONGITUD MEDIA: "+ escenario_1.getFuente().getLongitud_media() + "\n");
		resultado.append("EL CODIGO ES COMPACTO\n");
		
		resultado.append("\n\n---------------------------------------------------------------------------------------------------\n\n");
		
		resultado.append("ESCENARIO: Palabras codigo de 7 digitos\n\n");
		resultado.append("INECUACION DE KRAFT: "+ escenario_2.kraft_McMillan(2) + "\n");
		resultado.append("LONGITUD MEDIA: "+ escenario_2.getFuente().getLongitud_media() + "\n");
		resultado.append("EL CODIGO ES COMPACTO\n");
		
		resultado.append("\n\n---------------------------------------------------------------------------------------------------\n\n");
		
		resultado.append("ESCENARIO: Palabras codigo de 9 digitos\n\n");
		resultado.append("INECUACION DE KRAFT: "+ escenario_3.kraft_McMillan(2) + "\n");
		resultado.append("LONGITUD MEDIA: "+ escenario_3.getFuente().getLongitud_media() + "\n");
		resultado.append("EL CODIGO ES COMPACTO\n");
		
		escenario_1.writeFile("Segunda_parte_b.txt", resultado.toString());
	}

}
