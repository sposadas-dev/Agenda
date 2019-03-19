package dto;

import java.time.LocalDate;

public class PersonaDTO 
{
	private int idPersona;
	private String nombre;
	private String telefono;
	private String calle;
	private String dpto;
	private LocalidadDTO localidad;
	private String email;
	private LocalDate cumpleanios;
	private TipoContactoDTO tipo;
	
	public PersonaDTO(int idPersona, String nombre, String telefono, String calle, String dpto, LocalidadDTO localidad,
			String email, LocalDate cumpleanios, TipoContactoDTO tipo) {
		super();
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.calle = calle;
		this.dpto = dpto;
		this.localidad = localidad;
		this.email = email;
		this.cumpleanios = cumpleanios;
		this.tipo = tipo;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getDpto() {
		return dpto;
	}

	public void setDpto(String dpto) {
		this.dpto = dpto;
	}

	public LocalidadDTO getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getCumpleanios() {
		return cumpleanios;
	}

	public void setCumpleanios(LocalDate cumpleanios) {
		this.cumpleanios = cumpleanios;
	}

	public TipoContactoDTO getTipo() {
		return tipo;
	}

	public void setTipo(TipoContactoDTO tipo) {
		this.tipo = tipo;
	}
	
}
