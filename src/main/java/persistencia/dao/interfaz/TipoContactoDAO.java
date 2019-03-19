package persistencia.dao.interfaz;

import java.util.List;

import dto.TipoContactoDTO;

public interface TipoContactoDAO {

	public boolean insert(TipoContactoDTO nuevoTipo) throws Exception;
	
	public boolean delete(TipoContactoDTO tipo_a_eliminar) throws Exception;
	
	public List<TipoContactoDTO> readAll() throws Exception;
	
	public boolean update(TipoContactoDTO editada) throws Exception;

	public TipoContactoDTO browse(int idTipo) throws Exception;
	
}
