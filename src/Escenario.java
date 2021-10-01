import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;


public class Escenario {

	private String datos;
	private Fuente fuente = new Fuente();
	
public static void main(String[] args) {
		
		Escenario escenario_1 = new Escenario();
		escenario_1.readFile();
		
		escenario_1.genera_Fuente(5);
		escenario_1.probabilidad_Independiente(5);
		escenario_1.fuente.calcula_cantInformacion();
		escenario_1.fuente.calcula_entropia();
		//System.out.print(primera_parte.kraft_McMillan(2, primera_parte.fuente));
		escenario_1.longitud_media(escenario_1.fuente);
		/*
		
		primera_parte.genera_Fuente(7);
		primera_parte.probabilidad_Independiente(7);
		primera_parte.fuente.calcula_cantInformacion();
		primera_parte.fuente.calcula_entropia();
		//System.out.print(primera_parte.Kraft_McMillan(2, primera_parte.fuente));
		primera_parte.longitud_media(primera_parte.fuente);
		
		
		primera_parte.genera_Fuente(9);
		primera_parte.probabilidad_Independiente(9);
		primera_parte.fuente.calcula_cantInformacion();
		primera_parte.fuente.calcula_entropia();
		//System.out.print(primera_parte.Kraft_McMillan(2, primera_parte.fuente));
		primera_parte.longitud_media(primera_parte.fuente);
		/*
		primera_parte.genera_Fuente(2);
		primera_parte.probabilidad_Condicional(2);
		
		
		System.out.print(primera_parte.fuente.getSimbolos() + "\n");
		System.out.print(primera_parte.fuente.getEntropia() + "\n");
		System.out.print(primera_parte.fuente.getLongitud_media() + "\n");
		primera_parte.fuente.setRendimiento(primera_parte.fuente.getEntropia()/primera_parte.fuente.getLongitud_media());
		System.out.print(primera_parte.fuente.getRendimiento() + "\n");
		System.out.print("redundancia : " + (1 - primera_parte.fuente.getRendimiento()) + "\n");
		*/
		
		/*
		Simbolo s1 = new Simbolo("00");
		Simbolo s2 = new Simbolo("01");
		Simbolo s3 = new Simbolo("10");
		Simbolo s4 = new Simbolo("11");
		
		s1.setProbabilidad(0.6);
		s2.setProbabilidad(0.2);
		s3.setProbabilidad(0.1);
		s4.setProbabilidad(0.1);
		
		primera_parte.fuente.getSimbolos().add(s1);
		primera_parte.fuente.getSimbolos().add(s2);
		primera_parte.fuente.getSimbolos().add(s3);
		primera_parte.fuente.getSimbolos().add(s4);
		*/
		
		Collections.sort(escenario_1.fuente.getSimbolos());
		System.out.print(escenario_1.fuente.getSimbolos() + "\n");
		Fuente huffman = null;
		try {
			huffman = (Fuente) escenario_1.fuente.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		escenario_1.huffman(huffman);
		System.out.print(huffman.getSimbolos() + "\n");
		escenario_1.genera_Huffman(escenario_1.fuente, huffman, 5);
	}

	public void readFile( ) {
		
		try {
			  
			BufferedReader in = new BufferedReader(new FileReader("anexo1.txt"));
			
	        datos = in.readLine();
	        in.close();
	        
	    }
	    catch (IOException e) {
	        System.out.println("excepcion IO" + e);
	    }
		
	}
	
	public void writeFile(String nombre_archivo, String resultado) {
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(nombre_archivo, true));
			
			bw.write(resultado);
			bw.close();
		}
		catch (IOException e) {
			System.out.println("excepcion IO" + e);
		}
	}
	
	
	public void genera_Fuente(int digitos) {
		int a,b;
		String codigo;
		
		a = 0;
		b = digitos;
		
		codigo = datos.substring(a, b);
		this.fuente.getSimbolos().add(new Simbolo(codigo));
		while ( b < datos.length() ) {
			a += digitos;
			b += digitos;
			codigo = datos.substring(a, b);
			
			Iterator<Simbolo> it = this.fuente.getSimbolos().iterator();
			Simbolo s = (Simbolo) it.next();
			while(it.hasNext() && !s.getCodigo().equals(codigo))
				s = it.next();
			if(s.getCodigo().equals(codigo))
				s.setFrecuencia(s.getFrecuencia()+1);
			else
				this.fuente.getSimbolos().add(new Simbolo(codigo));
		}	
	}
	
	
	public void probabilidad_Independiente(int digitos) {
		Iterator<Simbolo> it = this.fuente.getSimbolos().iterator();
		while(it.hasNext()) {
			Simbolo s = it.next();
			s.setProbabilidad((double)s.getFrecuencia()/(datos.length()/digitos));
		}
	}
	
	public void probabilidad_Condicional(int digitos) {
		int a,b;
		String ant_codigo,codigo;
		
		a = 0;
		b = digitos;
		
		ant_codigo = datos.substring(a, b);
		while ( b < datos.length() ) {
			a += digitos;
			b += digitos;
			codigo = datos.substring(a, b);
			
			Iterator<Simbolo> it = this.fuente.getSimbolos().iterator();
			Simbolo s = (Simbolo) it.next();
			while(it.hasNext() && !s.getCodigo().equals(ant_codigo))
				s = it.next();
			switch(codigo) 
			{
				case "00": 	s.getProb_condicional()[0] += 1;
							break;
				case "01":	s.getProb_condicional()[1] += 1;
							break;
				case "10":	s.getProb_condicional()[2] += 1;
							break;
				case "11":	s.getProb_condicional()[3] += 1;
							break;
			}
			ant_codigo = codigo;
		}
		Iterator<Simbolo> it = this.fuente.getSimbolos().iterator();
		while(it.hasNext()) {
			Simbolo s = it.next();
			s.getProb_condicional()[0] = s.getProb_condicional()[0] / s.getFrecuencia();
			s.getProb_condicional()[1] = s.getProb_condicional()[1] / s.getFrecuencia();
			s.getProb_condicional()[2] = s.getProb_condicional()[2] / s.getFrecuencia();
			s.getProb_condicional()[3] = s.getProb_condicional()[3] / s.getFrecuencia();	
		}
	}
	
	public double kraft_McMillan(int r,Fuente fuente) {
		int longitud;
		double suma;
		
		suma = 0;
		Iterator<Simbolo> it = fuente.getSimbolos().iterator();
		while(it.hasNext()) {
			Simbolo s = it.next();
			longitud = s.getCodigo().length();
			suma += Math.pow(r, -longitud);
		}
		return suma;
	}
	
	public void longitud_media(Fuente fuente) {
		int longitud;
		double suma,probabilidad;
		
		suma = 0;
		Iterator<Simbolo> it = fuente.getSimbolos().iterator();
		while(it.hasNext()) {
			Simbolo s = it.next();
			longitud = s.getCodigo().length();
			probabilidad = s.getProbabilidad();
			suma += probabilidad * longitud;
		}
		fuente.setLongitud_media(suma);
	}
	
	public void huffman(Fuente fuente_Huffman) {
		Simbolo ant_simbolo, ult_simbolo;
		
		Collections.sort(fuente_Huffman.getSimbolos());
		if (fuente_Huffman.getSimbolos().size() == 2 ) {
			fuente_Huffman.getSimbolos().get(0).setCodigo("0");
			fuente_Huffman.getSimbolos().get(1).setCodigo("1");
		}
		else {
			ant_simbolo = fuente_Huffman.getSimbolos().get(fuente_Huffman.getSimbolos().size()-2);
			ult_simbolo = fuente_Huffman.getSimbolos().get(fuente_Huffman.getSimbolos().size()-1);
			ant_simbolo.setProbabilidad(ant_simbolo.getProbabilidad() + ult_simbolo.getProbabilidad());
			fuente_Huffman.getSimbolos().remove(ult_simbolo);
			huffman(fuente_Huffman);
			fuente_Huffman.getSimbolos().add(ult_simbolo);
			ant_simbolo.setProbabilidad(ant_simbolo.getProbabilidad() - ult_simbolo.getProbabilidad());
			Collections.sort(fuente_Huffman.getSimbolos());
			ult_simbolo.setCodigo(ant_simbolo.getCodigo() +"1");
			ant_simbolo.setCodigo(ant_simbolo.getCodigo() +"0");
		}
	}
	
	public void genera_Huffman(Fuente fuente,Fuente huffman,int digitos) {
		int a,b;
		String codigo;
		
		a = 0;
		b = digitos;
		
		while ( b <= datos.length() ) {
			
			codigo = datos.substring(a, b);
			
			Iterator<Simbolo> it = fuente.getSimbolos().iterator();
			Simbolo s = (Simbolo) it.next();
			while(it.hasNext() && !s.getCodigo().equals(codigo))
				s = it.next();
			
			it = huffman.getSimbolos().iterator();
			Simbolo sH = (Simbolo) it.next();
			while(it.hasNext() && s.getId() != sH.getId() )
				sH = it.next();
			
			
			this.writeFile("Huffman_escenario_1",sH.getCodigo());
			
			a += digitos;
			b += digitos;
		}
			
			
	}
} 