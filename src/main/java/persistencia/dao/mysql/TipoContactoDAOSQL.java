package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TipoContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoContactoDAO;

public class TipoContactoDAOSQL implements TipoContactoDAO {

	private static final String insert = "INSERT INTO tipo_contacto (idTipo, nombre) VALUES (?, ?)";
	private static final String delete = "DELETE FROM tipo_contacto WHERE idTipo = ?";
	private static final String readall = "SELECT * FROM tipo_contacto";
	private static final String update = "UPDATE tipo_contacto SET nombre=? WHERE idTipo = ?;";
	private static final String browse = "SELECT * FROM tipo_contacto WHERE idTipo = ?;";

	@Override
	public List<TipoContactoDTO> readAll() throws Exception {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<TipoContactoDTO> tipos = new ArrayList<TipoContactoDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				tipos.add(new TipoContactoDTO(resultSet.getInt("idTipo"), resultSet.getString("Nombre")));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return tipos;
	}

	@Override
	public boolean insert(TipoContactoDTO nuevoTipo) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, nuevoTipo.getIdTipo());
			statement.setString(2, nuevoTipo.getNombre());
			
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
	public boolean delete(TipoContactoDTO tipo_a_eliminar) throws Exception {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(tipo_a_eliminar.getIdTipo()));
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0)
				return true;
		} 
		catch (SQLException e) 
		{
			throw e;
		}
		return false;
		
	}

	@Override
	public boolean update(TipoContactoDTO editado) throws Exception {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			
			statement.setString(1, editado.getNombre());
			statement.setInt(2, editado.getIdTipo());
			
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) 
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public TipoContactoDTO browse(int idTipo) throws Exception {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		TipoContactoDTO tipo;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(browse);
			
			statement.setInt(1, idTipo);
			resultSet = statement.executeQuery();
			if (resultSet.next()){
				tipo = new TipoContactoDTO(resultSet.getInt("idTipo"), resultSet.getString("Nombre"));
				return tipo;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

}
