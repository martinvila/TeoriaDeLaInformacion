import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class Primer_parte {

	private String datos;
	private Fuente fuente = new Fuente();
	
	public static void main(String[] args) {
		
		Primer_parte primera_parte = new Primer_parte();
		primera_parte.readFile();
		/*
		primera_parte.genera_Fuente(5);
		primera_parte.probabilidad_Independiente(5);
		primera_parte.fuente.calcula_cantInformacion();
		primera_parte.fuente.calcula_entropia();
		
		primera_parte.genera_Fuente(7);
		primera_parte.probabilidad_Independiente(7);
		primera_parte.fuente.calcula_cantInformacion();
		primera_parte.fuente.calcula_entropia();
		
		primera_parte.genera_Fuente(9);
		primera_parte.probabilidad_Independiente(9);
		primera_parte.fuente.calcula_cantInformacion();
		primera_parte.fuente.calcula_entropia();
		
		*/
		primera_parte.genera_Fuente(2);
		primera_parte.probabilidad_Condicional(2);
		
		System.out.print(primera_parte.fuente.getSimbolos());
		//System.out.print(primera_parte.fuente.getEntropia());
		
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
} 