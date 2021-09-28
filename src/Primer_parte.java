import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Primer_parte {

	private static String datos;
	
	public static void main(String[] args) {
		
		try {
			  
			BufferedReader in = new BufferedReader(new FileReader("anexo1.txt"));
			
            datos = in.readLine();
            
            /*while(datos !=  null) {
            	List<String> tokens =  Arrays.asList(cadena.split("-"));
            	nroDni = tokens.get(0).trim();
            	if(nroDni.equals(dni)) {
            		nombre = tokens.get(1).trim();
            		categoria = tokens.get(2).trim();
            		
            		cliente = ClienteFactory.getInstance().getCliente(dni, nombre, categoria);
            	}
            	
            	datos = in.readLine();
            }*/
            
            in.close();
            
        }
        catch (IOException e) {
            System.out.println("excepcion IO" + e);
        }
		
		System.out.println("Codigo: " + datos);
		
	}
	
		
		

}
