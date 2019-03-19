package dto;

public class DomicilioDTO {
	private int idDomicilio;
	private String calle;
	private String altura;
	private String piso;
	private int idLocalidad;
	
	public DomicilioDTO(int idDomicilio, String calle, String altura, String piso, int idLocalidad) {
		this.idDomicilio = idDomicilio;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.idLocalidad = idLocalidad;
	}

	public int getIdDomicilio() {
		return idDomicilio;
	}

	public String getCalle() {
		return calle;
	}

	public String getAltura() {
		return altura;
	}

	public String getPiso() {
		return piso;
	}

	public int getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdDomicilio(int idDomicilio) {
		this.idDomicilio = idDomicilio;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	
}
