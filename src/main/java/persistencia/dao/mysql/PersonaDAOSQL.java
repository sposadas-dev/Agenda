package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class PersonaDAOSQL implements PersonaDAO
{
	private static final String insert = "INSERT INTO personas(idPersona, nombre, telefono, calle, dpto, localidad, email, cumpleanios, tipo) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";
	private static final String update = "UPDATE personas SET nombre=?, telefono=?, calle=?, dpto=?, localidad=?, email=?, cumpleanios=?, tipo=? WHERE idPersona = ?;";
		
	public boolean insert(PersonaDTO persona) throws Exception
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			//XXX: and some other changes over here
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, persona.getIdPersona());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());
			statement.setString(4, persona.getCalle());
			statement.setString(5, persona.getDpto());
			statement.setInt(6, persona.getLocalidad().getIdLocalidad());
			statement.setString(7, persona.getEmail());
			if (persona.getCumpleanios() == null) {
				statement.setString(8, LocalDate.now().toString());
			}
			else {
				statement.setString(8, persona.getCumpleanios().toString());
			}
			statement.setInt(9, persona.getTipo().getIdTipo());
			
			if(statement.executeUpdate() > 0) //Si se ejecutï¿½ devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean delete(PersonaDTO persona_a_eliminar) throws Exception
	{
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecutÃ³ devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public List<PersonaDTO> readAll() throws Exception
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				personas.add(new PersonaDTO(
						resultSet.getInt("idPersona"), 
						resultSet.getString("Nombre"), 
						resultSet.getString("Telefono"),
						resultSet.getString("Calle"),
						resultSet.getString("Dpto"),
						completarLocalidad(resultSet.getInt("Localidad")),
						resultSet.getString("Email"),
						resultSet.getDate("Cumpleanios").toLocalDate(),
						completarTipo(resultSet.getInt("Tipo"))
					));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return personas;
	}
	
	private TipoContactoDTO completarTipo(int idTipo) throws Exception {
		// TODO Auto-generated method stub
		TipoContactoDAO conDAO = new TipoContactoDAOSQL();
		return conDAO.browse(idTipo);
	}

	private LocalidadDTO completarLocalidad(int idLocalidad) throws Exception {
		// TODO Auto-generated method stub
		LocalidadDAO locDAO = new LocalidadDAOSQL();
		return locDAO.browse(idLocalidad);
		
	}

	public boolean update(PersonaDTO persona_a_editar) throws Exception {
		//more magic
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			
			statement.setString(1, persona_a_editar.getNombre());
			statement.setString(2, persona_a_editar.getTelefono());
			statement.setString(3, persona_a_editar.getCalle());
			statement.setString(4, persona_a_editar.getDpto());
			statement.setInt(5, persona_a_editar.getLocalidad().getIdLocalidad());
			statement.setString(6, persona_a_editar.getEmail());
			statement.setString(7, persona_a_editar.getCumpleanios().toString());
			statement.setInt(8, persona_a_editar.getTipo().getIdTipo());
			
			statement.setInt(9, persona_a_editar.getIdPersona());
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecutÃ³ devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
}
