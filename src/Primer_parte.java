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
		primera_parte.stage(9);
		primera_parte.fuente.calcula_cantInformacion();
		primera_parte.fuente.calcula_entropia();
		System.out.print(primera_parte.fuente.getSimbolos());
		System.out.print(primera_parte.fuente.getEntropia());
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
	
	
	public void stage(int digitos) {
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
				s.setProbabilidad(s.getProbabilidad()+1);
			else
				this.fuente.getSimbolos().add(new Simbolo(codigo));
		}
		
		Iterator<Simbolo> it1 = this.fuente.getSimbolos().iterator();
		while(it1.hasNext()) {
			Simbolo s = it1.next();
			s.setProbabilidad(s.getProbabilidad()/(datos.length()/digitos));
		}	
	}
	
} 