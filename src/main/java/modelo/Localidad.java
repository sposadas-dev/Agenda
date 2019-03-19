package modelo;

import java.util.List;

import dto.LocalidadDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.LocalidadDAO;

public class Localidad {

	private LocalidadDAO localidad;
	
	public Localidad(DAOAbstractFactory metodo_persistencia) {
		this.localidad = metodo_persistencia.createLocalidadDAO();
	}
	
	public void agregarLocalidad(LocalidadDTO nuevaLocalidad) throws Exception{
		this.localidad.insert(nuevaLocalidad);
	}
	
	public void borrarLocalidad(LocalidadDTO localidad_a_eliminar) throws Exception {
		this.localidad.delete(localidad_a_eliminar);
	}
	
	public List<LocalidadDTO> obtenerLocalidades() throws Exception{
		return this.localidad.readAll();
	}
	
	public LocalidadDTO obtenerLocalidad(int idLocalidad) throws Exception{
		return this.localidad.browse(idLocalidad);
	}
	
	public void editarLocalidad(LocalidadDTO editada) throws Exception {
		this.localidad.update(editada);
	}

}
