import java.util.ArrayList;
import java.util.Iterator;

public class Fuente {
	private ArrayList<Simbolo> simbolos = new ArrayList<Simbolo>();
	private double entropia = 0;
	private double longitud_media = 0;
	private double rendimiento = 0;
	private double cantidad_informacion;
	private boolean memoria_nula;
	
	public ArrayList<Simbolo> getSimbolos() {
		return simbolos;
	}
	
	public void setSimbolos(ArrayList<Simbolo> simbolos) {
		this.simbolos = simbolos;
	}
	
	public double getEntropia() {
		return entropia;
	}
	
	public double getCantidad_informacion() {
		return cantidad_informacion;
	}
	
	public boolean isMemoria_nula() {
		return memoria_nula;
	}
	
	public void setMemoria_nula(boolean memoria_nula) {
		this.memoria_nula = memoria_nula;
	}
	
	public double getLongitud_media() {
		return longitud_media;
	}

	public void setLongitud_media(double longitud_media) {
		this.longitud_media = longitud_media;
	}

	public double getRendimiento() {
		return rendimiento;
	}

	public void setRendimiento(double rendimiento) {
		this.rendimiento = rendimiento;
	}

	public void calcula_cantInformacion() {
		Iterator<Simbolo> it = this.simbolos.iterator();
		while(it.hasNext()) {
			Simbolo s = it.next();
			s.calcula_cantInformacion();
		}
	}
	
	public void calcula_entropia() {
		Iterator<Simbolo> it = this.simbolos.iterator();
		while(it.hasNext()) {
			Simbolo s = it.next();
			this.entropia += s.getCantidad_informacion()*s.getProbabilidad();
		}
	}

}
