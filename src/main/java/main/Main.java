package main;

import modelo.Agenda;
import modelo.Localidad;
import modelo.TipoContacto;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Vista;


public class Main 
{

	public static void main(String[] args) throws Exception 
	{
	        Vista vista = new Vista();
			Agenda agenda = new Agenda(new DAOSQLFactory());
			TipoContacto tipos_de_contacto = new TipoContacto(new DAOSQLFactory());
			Localidad localidad = new Localidad(new DAOSQLFactory());
			Controlador controlador = new Controlador(vista, agenda, tipos_de_contacto, localidad);
			controlador.inicializar();
	}
}
