package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import modelo.Agenda;
import modelo.Localidad;
import modelo.TipoContacto;
import persistencia.conexion.Conexion;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.Campo;
import presentacion.vista.VentanaCambios;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class Controlador implements ActionListener
{
	private Vista vista;
	private VentanaPersona ventanaPersona;
	private VentanaCambios ventanaCambios;
	
	private List<PersonaDTO> personas_en_tabla;
	private List<TipoContactoDTO> tipos_de_contacto;
	private List<LocalidadDTO> localidades;
	private Campo campo;
	private Agenda agenda_model;
	private TipoContacto tipo_model;
	private Localidad localidad_model;
	
	public Controlador(Vista vista, Agenda agenda, TipoContacto tipo, Localidad localidad)
	{
		this.vista = vista;
		this.ventanaPersona = new VentanaPersona();
		this.ventanaCambios = new VentanaCambios();
		this.campo = new Campo();
		
		crearListeners();
		
		this.agenda_model = agenda;
		this.tipo_model = tipo;
		this.localidad_model = localidad;
		
		this.personas_en_tabla = null;
		this.tipos_de_contacto = null;
		this.localidades = null;
	}
	
	public void inicializar() throws Exception
	{
		this.llenarTabla();
		this.vista.show();
	}
	
	private void crearListeners() {
		this.vista.getBtnAgregar().addActionListener(this);
		this.vista.getBtnBorrar().addActionListener(this);
		this.vista.getBtnReporte().addActionListener(this);
		this.vista.getBtnEditar().addActionListener(this);
	}
	
	private void llenarTabla() throws Exception
	{
		this.vista.getModelPersonas().setRowCount(0); //Para vaciar la tabla
		this.vista.getModelPersonas().setColumnCount(0);
		this.vista.getModelPersonas().setColumnIdentifiers(this.vista.getNombreColumnas());
		
		this.personas_en_tabla = agenda_model.obtenerPersonas();
		for (int i = 0; i < this.personas_en_tabla.size(); i ++)
		{
			Object[] fila = {
					this.personas_en_tabla.get(i).getNombre(), 
					this.personas_en_tabla.get(i).getTelefono(),
					this.personas_en_tabla.get(i).getCalle(),
					this.personas_en_tabla.get(i).getDpto(),
					this.getLocalidad(this.personas_en_tabla.get(i).getLocalidad().getIdLocalidad()),
					this.personas_en_tabla.get(i).getEmail(),
					this.personas_en_tabla.get(i).getCumpleanios(),
					this.getTipo(this.personas_en_tabla.get(i).getTipo().getIdTipo()),
					};
			this.vista.getModelPersonas().addRow(fila);
		}			
	}
	
	private Object getTipo(int idTipo) throws Exception {
		TipoContactoDTO tipo = tipo_model.obtenerTipo(idTipo);
		
		return tipo.getNombre();
	}

	private String getLocalidad(int idLocalidad) throws Exception {
		LocalidadDTO localidad = localidad_model.obtenerLocalidad(idLocalidad);
		
		return localidad.getNombre();
	}

	private void llenarTablaCambios() throws Exception{
		this.ventanaCambios.getModelcambios().setRowCount(0);
		this.ventanaCambios.getModelcambios().setColumnCount(0);
		this.ventanaCambios.getModelcambios().setColumnIdentifiers(this.ventanaCambios.getNombreColumnas());
		llenar();
	}

	private void llenar() throws Exception {
		if ( this.ventanaCambios.getTipo() == VentanaCambios.Tipo.Localidades )
		{
			this.localidades = localidad_model.obtenerLocalidades();				
			for (int i = 0; i < this.localidades.size(); i ++)
			{
				Object[] fila = {this.localidades.get(i).getNombre()};
				this.ventanaCambios.getModelcambios().addRow(fila);
			}	
		}
		else if ( this.ventanaCambios.getTipo() == VentanaCambios.Tipo.TiposContacto )
		{
			this.tipos_de_contacto = tipo_model.obtenerTipos();
			for (int i = 0; i < this.tipos_de_contacto.size(); i ++)
			{
				Object[] fila = {this.tipos_de_contacto.get(i).getNombre()};
				this.ventanaCambios.getModelcambios().addRow(fila);
			}	
		}
	}
	
	private void llenar_combo_localidades(VentanaPersona ventana) throws Exception {
		this.localidades = localidad_model.obtenerLocalidades();
		for (LocalidadDTO localidad : this.localidades)
		{
			ventana.getLocalidad().addItem(localidad);
		}

	}
	
	private void llenar_combo_tipos(VentanaPersona ventana) throws Exception {
		this.tipos_de_contacto = tipo_model.obtenerTipos();
		
		for (TipoContactoDTO tipo : this.tipos_de_contacto)
		{
			ventana.getTipoContacto().addItem(tipo);
		}
	}
	
	public void fillPersona(VentanaPersona ventanaPersona2, PersonaDTO seleccionada){
		ventanaPersona2.getTxtNombre().setText(seleccionada.getNombre());
		ventanaPersona2.getTxtTelefono().setText(seleccionada.getTelefono());
		ventanaPersona2.getTxtCalle().setText(seleccionada.getCalle());
		ventanaPersona2.getTxtDpto().setText(seleccionada.getDpto());
		ventanaPersona2.getTxtEmail().setText(seleccionada.getEmail());
		ventanaPersona2.getCumpleanios().setDate(seleccionada.getCumpleanios());
		ventanaPersona2.setIdPersona(seleccionada.getIdPersona());
		ventanaPersona2.getLocalidad().setSelectedItem(seleccionada.getLocalidad());
		ventanaPersona2.getTipoContacto().setSelectedItem(seleccionada.getTipo());
	}
	public void actionPerformed(ActionEvent e)
	{
		try {
			
		//MENUS
		//BOTON QUE ABRE VENTANA AGREGAR
		if(e.getSource() == this.vista.getBtnAgregar())
		{
			this.ventanaPersona = new VentanaPersona(this, VentanaPersona.TipoVentana.NoEditable);
			this.llenar_combo_tipos(this.ventanaPersona);
			this.llenar_combo_localidades(this.ventanaPersona);
		}
		//BOTON QUE ABRE VENTANA DE EDITAR
		else if (e.getSource() == this.vista.getBtnEditar())
		{
			if (this.vista.getTablaPersonas().getSelectedRow() >= 0){
				
				this.ventanaPersona = new VentanaPersona(this , VentanaPersona.TipoVentana.Editable);
				PersonaDTO seleccionada = this.personas_en_tabla.get(this.vista.getTablaPersonas().getSelectedRow());
				this.llenar_combo_tipos(this.ventanaPersona);
				this.llenar_combo_localidades(this.ventanaPersona);
				this.fillPersona(this.ventanaPersona, seleccionada);
			}
		}
		//BOTON QUE BORRA
		else if(e.getSource() == this.vista.getBtnBorrar())
		{
			int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			for (int fila:filas_seleccionadas)
			{
				this.agenda_model.borrarPersona(this.personas_en_tabla.get(fila));
			}
			
			this.llenarTabla();
		}
		else if ( e.getSource() == this.ventanaPersona.getCambiarLocalidades())
		{
			this.ventanaCambios = new VentanaCambios(this, VentanaCambios.Tipo.Localidades);
			llenarTablaCambios();
		}
		else if ( e.getSource() == this.ventanaPersona.getCambiarTipos() )
		{
			this.ventanaCambios = new VentanaCambios (this, VentanaCambios.Tipo.TiposContacto);
			llenarTablaCambios();
		}

		//BOTONES DE ACCION DE MENUS
		// BOTON DE GUARDAR CAMBIOS AL EDITAR
		else if(e.getSource() == this.ventanaPersona.getAceptar())
		{
			PersonaDTO persona = personaNueva(this.ventanaPersona);
			insertarPersona(persona);	
		}
		else if ( e.getSource() == this.ventanaCambios.getAgregar() )
		{
			campo = new Campo(this, Campo.Tipo.NoEdicion);
		}
		else if (e.getSource() == this.campo.getBtnGuardar())
		{
			agregarNuevo();
			llenarTablaCambios();
			actualizarCambios();
		}
		else if ( e.getSource() == this.ventanaCambios.getBorrar() )
		{
		
			int[] filas_seleccionadas = this.ventanaCambios.getTabla().getSelectedRows();
			for (int fila:filas_seleccionadas)
			try
			{
				borrarSeleccionado(fila);
			}
			catch(Exception error)
			{
				  JOptionPane.showOptionDialog(
						  	null, "No es posible borrar un valor que esta en uso.", 
				             "Confirmacion", JOptionPane.CANCEL_OPTION,
				             JOptionPane.QUESTION_MESSAGE, null, null, null);

			}
			llenarTablaCambios();
			actualizarCambios();
		}
		else if (e.getSource() == this.ventanaCambios.getEditar() )
		{
			if (this.ventanaCambios.getTabla().getSelectedRow() >= 0)
			{
				this.campo = new Campo(this, Campo.Tipo.Edicion);
				if ( this.ventanaCambios.getTipo() == VentanaCambios.Tipo.Localidades )
				{
					this.campo.getEntrada().setText(this.localidades.get(ventanaCambios.getTabla().getSelectedRow()).getNombre() );

				}
				else if ( this.ventanaCambios.getTipo() == VentanaCambios.Tipo.TiposContacto )
				{
					this.campo.getEntrada().setText(this.tipos_de_contacto.get(ventanaCambios.getTabla().getSelectedRow()).getNombre() );
				}
			}
		}
		else if (e.getSource() == this.ventanaCambios.getAceptar() )
		{
			this.ventanaCambios.dispose();
		}
		}
		catch (Exception error)
		{
			Logger log = Logger.getLogger(Conexion.class);
			log.error("Conexion fallida", error);
			JOptionPane.showOptionDialog(
				  	null, "Se ha producido un error.", 
		             "Confirmacion", JOptionPane.CANCEL_OPTION,
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		}
	}

	private void agregarNuevo() throws Exception {
		if ( this.ventanaCambios.getTipo() == VentanaCambios.Tipo.Localidades)
		{
			LocalidadDTO localidadNueva = new LocalidadDTO (0,campo.getEntrada().getText());
			if (this.campo.getTipo() == Campo.Tipo.Edicion){
				localidadNueva.setIdLocalidad(this.localidades.get(this.ventanaCambios.getTabla().getSelectedRow()).getIdLocalidad());	
				this.localidad_model.editarLocalidad(localidadNueva);
			}
			else
			{
				this.localidad_model.agregarLocalidad(localidadNueva);
				
			}
			this.campo.dispose();	
		}
		else if (this.ventanaCambios.getTipo() == VentanaCambios.Tipo.TiposContacto){
			
			TipoContactoDTO tipoNuevo = new TipoContactoDTO (0, campo.getEntrada().getText());
			if (this.campo.getTipo() == Campo.Tipo.Edicion)
			{
				tipoNuevo.setIdTipo(this.tipos_de_contacto.get(this.ventanaCambios.getTabla().getSelectedRow()).getIdTipo());
				this.tipo_model.editarTipo(tipoNuevo);
			}else
			{					
				this.tipo_model.agregarTipo(tipoNuevo);;
			}
			this.campo.dispose();
		}
	}

	private void borrarSeleccionado(int fila) throws Exception {
		if ( this.ventanaCambios.getTipo() == VentanaCambios.Tipo.Localidades)
		{
			
			this.localidad_model.borrarLocalidad(this.localidades.get(fila));
		}
		else if (this.ventanaCambios.getTipo() == VentanaCambios.Tipo.TiposContacto)
		{
			this.tipo_model.borrarTipo(this.tipos_de_contacto.get(fila));
		}
	}

	private void insertarPersona(PersonaDTO persona) throws Exception {
		if ( this.ventanaPersona.getTipo() == VentanaPersona.TipoVentana.Editable)
		{
			persona.setIdPersona(this.ventanaPersona.getIdPersona());
			this.agenda_model.editarPersona(persona);
			this.llenarTabla();
			this.ventanaPersona.dispose();	
		}
		else if (this.ventanaPersona.getTipo() == VentanaPersona.TipoVentana.NoEditable)
		{
			this.agenda_model.agregarPersona(persona);
			this.llenarTabla();
			this.ventanaPersona.dispose();
		}
	}

	private void actualizarCambios() throws Exception 
	{
		
		if ( this.ventanaCambios.getTipo() == VentanaCambios.Tipo.Localidades )
		{
			this.ventanaPersona.getLocalidad().removeAllItems();
			llenar_combo_localidades(this.ventanaPersona);
		}
		else if ( this.ventanaCambios.getTipo() == VentanaCambios.Tipo.TiposContacto ){
			this.ventanaPersona.getTipoContacto().removeAllItems();
			llenar_combo_tipos(this.ventanaPersona);
		}
	}

	private PersonaDTO personaNueva(VentanaPersona ventana) {
		PersonaDTO nueva= new PersonaDTO(
				0,
				ventana.getTxtNombre().getText(),
				ventana.getTxtTelefono().getText(),
				ventana.getTxtCalle().getText(),
				ventana.getTxtDpto().getText(),
				(LocalidadDTO)ventana.getLocalidad().getSelectedItem(),
				ventana.getTxtEmail().getText(),
				ventana.getCumpleanios().getDate(), 
				(TipoContactoDTO)ventana.getTipoContacto().getSelectedItem()
				);
		return nueva;
	}
	
}
