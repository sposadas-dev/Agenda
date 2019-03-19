package persistencia.dao.interfaz;

import java.util.List;

import dto.PersonaDTO;

public interface PersonaDAO 
{
	
	public boolean insert(PersonaDTO persona) throws Exception;

	public boolean delete(PersonaDTO persona_a_eliminar) throws Exception;
	
	public List<PersonaDTO> readAll() throws Exception;
	
	public boolean update(PersonaDTO persona_a_editar) throws Exception;
}
