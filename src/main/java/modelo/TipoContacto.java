package modelo;

import java.util.List;

import dto.TipoContactoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.TipoContactoDAO;

public class TipoContacto {
	
	private TipoContactoDAO tipoContacto;
	
	public TipoContacto(DAOAbstractFactory metodo_persistencia)
	{
		this.tipoContacto = metodo_persistencia.createTipoContactoDAO();
	}
	
	public List<TipoContactoDTO> obtenerTipos() throws Exception
	{
		return this.tipoContacto.readAll();
	}
	public TipoContactoDTO obtenerTipo(int idTipo) throws Exception{
		return this.tipoContacto.browse(idTipo);
	}
	public void agregarTipo(TipoContactoDTO nuevoTipo) throws Exception{
		this.tipoContacto.insert(nuevoTipo);
	}
	
	public void borrarTipo(TipoContactoDTO tipo_a_borrar) throws Exception {
		this.tipoContacto.delete(tipo_a_borrar);
	}
	public void editarTipo(TipoContactoDTO editado) throws Exception{
		this.tipoContacto.update(editado);
	}
}
