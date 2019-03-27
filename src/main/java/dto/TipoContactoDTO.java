package dto;

public class TipoContactoDTO {

	private int idTipo;
	private String nombre;

	public TipoContactoDTO(int idTipo, String nombre)
	{
		this.idTipo = idTipo;
		this.nombre = nombre;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoContactoDTO other = (TipoContactoDTO) obj;
		if (idTipo != other.idTipo)
			return false;
		return true;
	}
	
}
