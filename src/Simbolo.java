
public class Simbolo {
	private String codigo;
	private double cantidad_informacion;
	private double probabilidad;
	
	public Simbolo(String codigo) {
		this.codigo = codigo;
		this.cantidad_informacion = 0;
		this.probabilidad = 1;
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

	@Override
	public String toString() {
		return "Simbolo [codigo=" + codigo + ", cantidad de informacion=" + cantidad_informacion + ", probabilidad="
				+ probabilidad + "]\n";
	}
	
	
}
