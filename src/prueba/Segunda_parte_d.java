package prueba;

import modelo.Escenario;
import modelo.Fuente;
import modelo.Simbolo;

public class Segunda_parte_d {

	public static void main(String[] args) {
		
		Escenario escenario_1 = new Escenario(5);
		escenario_1.probabilidad_Independiente();
		
		Simbolo.setSig_id(0);
		Escenario escenario_2 = new Escenario(7);
		escenario_2.probabilidad_Independiente();
		
		Simbolo.setSig_id(0);
		Escenario escenario_3 = new Escenario(9);
		escenario_3.probabilidad_Independiente();
		
		Fuente huffman_1 = null;
		Fuente huffman_2 = null;
		Fuente huffman_3 = null;
		try {
			huffman_1 = (Fuente) escenario_1.getFuente().clone();
			huffman_2 = (Fuente) escenario_2.getFuente().clone();
			huffman_3 = (Fuente) escenario_3.getFuente().clone();
		} 
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		escenario_1.huffman(huffman_1);
		escenario_2.huffman(huffman_2);
		escenario_3.huffman(huffman_3);
	
		escenario_1.rebuild_File("Huffman_Escenario_1.txt", huffman_1);
		escenario_2.rebuild_File("Huffman_Escenario_2.txt", huffman_2);
		escenario_3.rebuild_File("Huffman_Escenario_3.txt", huffman_3);
	}

}
