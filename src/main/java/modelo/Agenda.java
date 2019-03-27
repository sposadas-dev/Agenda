package modelo;

import java.util.List;
import dto.PersonaDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.PersonaDAO;


public class Agenda 
{
	private PersonaDAO persona;	
	
	public Agenda(DAOAbstractFactory metodo_persistencia)
	{
		this.persona = metodo_persistencia.createPersonaDAO();
	}
	
	public void agregarPersona(PersonaDTO nuevaPersona) throws Exception
	{
		this.persona.insert(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) throws Exception 
	{
		this.persona.delete(persona_a_eliminar);
	}
	
	public List<PersonaDTO> obtenerPersonas() throws Exception
	{
		return this.persona.readAll();		
	}
	
	public void editarPersona(PersonaDTO editada) throws Exception
	{
		this.persona.update(editada);
	}
	
}
