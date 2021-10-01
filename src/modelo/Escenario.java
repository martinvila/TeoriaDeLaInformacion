package modelo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

public class Escenario {
	
	private String datos;
	private Fuente fuente;
	private int digitos;
	
	
	public Escenario(int digitos) {
		this.fuente = new Fuente();
		this.digitos = digitos;
		this.readFile();
		this.genera_Fuente();
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
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(nombre_archivo, false));
			
			bw.write(resultado);
			bw.close();
		}
		catch (IOException e) {
			System.out.println("excepcion IO" + e);
		}
	}
	
	
	public void genera_Fuente() {
		int a,b;
		String codigo;
		
		a = 0;
		b = this.digitos;
		
		codigo = datos.substring(a, b);
		this.fuente.getSimbolos().add(new Simbolo(codigo));
		while ( b < datos.length() ) {
			a += this.digitos;
			b += this.digitos;
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
	
	
	public Fuente getFuente() {
		return fuente;
	}


	public void probabilidad_Independiente() {
		
		Iterator<Simbolo> it = this.fuente.getSimbolos().iterator();
		while(it.hasNext()) {
			Simbolo s = it.next();
			s.setProbabilidad((double)s.getFrecuencia()/(datos.length()/this.digitos));
		}
	}
	
	
	public void probabilidad_Condicional() {
		int a,b;
		String ant_codigo,codigo;
		
		a = 0;
		b = this.digitos;
		
		ant_codigo = datos.substring(a, b);
		while ( b < datos.length() ) {
			a += this.digitos;
			b += this.digitos;
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
	
	
	public double kraft_McMillan(int r) {
		int longitud;
		double suma;
		
		suma = 0;
		Iterator<Simbolo> it = this.fuente.getSimbolos().iterator();
		while(it.hasNext()) {
			Simbolo s = it.next();
			longitud = s.getCodigo().length();
			suma += Math.pow(r, -longitud);
		}
		return suma;
	}
	
	
	public void longitud_media() {
		int longitud;
		double suma,probabilidad;
		
		suma = 0;
		Iterator<Simbolo> it = this.fuente.getSimbolos().iterator();
		while(it.hasNext()) {
			Simbolo s = it.next();
			longitud = s.getCodigo().length();
			probabilidad = s.getProbabilidad();
			suma += probabilidad * longitud;
		}
		this.fuente.setLongitud_media(suma);
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
	
	
	public void rebuild_File(String nombre,Fuente huffman) {
		int a,b;
		String codigo;
		
		a = 0;
		b = this.digitos;
		
		StringBuilder codificacion = new StringBuilder();
		while ( b <= datos.length() ) {
			
			codigo = datos.substring(a, b);
			
			Iterator<Simbolo> it = this.fuente.getSimbolos().iterator();
			Simbolo s = (Simbolo) it.next();
			while(it.hasNext() && !s.getCodigo().equals(codigo))
				s = it.next();
			
			it = huffman.getSimbolos().iterator();
			Simbolo sH = (Simbolo) it.next();
			while(it.hasNext() && s.getId() != sH.getId() )
				sH = it.next();
			
			
			codificacion.append(sH.getCodigo());
			
			//this.writeFile(nombre,sH.getCodigo());
			
			a += this.digitos;
			b += this.digitos;
		}
		this.writeFile(nombre,codificacion.toString());
	}


	@Override
	public String toString() {
		return "ESCENARIO: Palabras codigo de "+ digitos +" digitos\n"+"\nFUENTE:\n";
	}
	
	
}