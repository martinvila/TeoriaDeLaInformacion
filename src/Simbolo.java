import java.util.Arrays;

public class Simbolo {
	private String codigo;
	private int frecuencia;
	private double cantidad_informacion;
	private double probabilidad;
	private double[] prob_condicional = {0,0,0,0};
	
	public Simbolo(String codigo) {
		this.codigo = codigo;
		this.frecuencia = 1;
		this.cantidad_informacion = 0;
		this.probabilidad = 0;
	}

	public void setProb_condicional(double[] prob_condicional) {
		this.prob_condicional = prob_condicional;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public double getProbabilidad() {
		return probabilidad;
	}

	public void setProbabilidad(double probabilidad) {
		this.probabilidad = probabilidad;
	}
	
	public void calcula_cantInformacion() {
		this.cantidad_informacion = Math.log(1/this.probabilidad)/Math.log(2);
	}

	public double getCantidad_informacion() {
		return cantidad_informacion;
	}
	
	public double[] getProb_condicional() {
		return prob_condicional;
	}
	
	public int getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}

	@Override
	public String toString() {
		return "Simbolo [codigo=" + codigo + ", frecuencia=" + frecuencia + ", probabilidad=" + probabilidad
				+ ", prob_condicional=" + Arrays.toString(prob_condicional) + "]\n";
	}

	

	
	
}
