package dto;

public class TipoDeContactoDTO {
	private int idTipoDeContacto;
	private String nombre;
	
	public TipoDeContactoDTO(int idTipoDeContacto, String nombre) {
		this.idTipoDeContacto = idTipoDeContacto;
		this.nombre = nombre;
	}

	public int getIdTipoDeContacto() {
		return idTipoDeContacto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setIdTipoDeContacto(int idTipoDeContacto) {
		this.idTipoDeContacto = idTipoDeContacto;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
