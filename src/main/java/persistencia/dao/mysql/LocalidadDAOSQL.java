package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.LocalidadDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;

public class LocalidadDAOSQL implements LocalidadDAO {

	private static final String insert = "INSERT INTO localidad (idLocalidad, nombre) VALUES (?, ?)";
	private static final String delete = "DELETE FROM localidad WHERE idLocalidad = ?";
	private static final String readall = "SELECT * FROM localidad";
	private static final String update = "UPDATE localidad SET nombre=? WHERE idLocalidad = ?;";
	private static final String browse = "SELECT * FROM localidad WHERE idLocalidad = ?;";

	@Override
	public boolean insert(LocalidadDTO nuevaLocalidad) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, nuevaLocalidad.getIdLocalidad());
			statement.setString(2, nuevaLocalidad.getNombre());
			
			if(statement.executeUpdate() > 0)
				return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;

	}

	@Override
	public boolean delete(LocalidadDTO localidad_a_eliminar) throws Exception {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(localidad_a_eliminar.getIdLocalidad()));
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecutÃ³ devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			throw e;
		}
		return false;

	}

	@Override
	public List<LocalidadDTO> readAll() throws Exception {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<LocalidadDTO> localidades = new ArrayList<LocalidadDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				///XXX: los agrego para que veas puntualmente donde hice cambios
				localidades.add(new LocalidadDTO(resultSet.getInt("idLocalidad"), resultSet.getString("Nombre")));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return localidades;
	}

	@Override
	public boolean update(LocalidadDTO editada) throws Exception {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			
			statement.setString(1, editada.getNombre());
			statement.setInt(2, editada.getIdLocalidad());
			
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

	@Override
	public LocalidadDTO browse(int idLocalidad) throws Exception {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		LocalidadDTO localidad;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(browse);
			
			statement.setInt(1, idLocalidad);
			resultSet = statement.executeQuery();
			if (resultSet.next()){
				localidad = new LocalidadDTO(resultSet.getInt("idLocalidad"), resultSet.getString("Nombre"));
				return localidad;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

}
