package persistencia.dao.interfaz;

import java.sql.SQLException;
import java.util.List;

import dto.LocalidadDTO;

public interface LocalidadDAO {

	public boolean insert(LocalidadDTO nuevaLocalidad) throws Exception;

	public boolean delete(LocalidadDTO localidad_a_eliminar) throws SQLException, Exception;

	public List<LocalidadDTO> readAll() throws Exception;

	public boolean update(LocalidadDTO editada) throws Exception;

	public LocalidadDTO browse(int idLocalidad) throws Exception;

}
