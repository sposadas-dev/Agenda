package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaEditar;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import dto.PersonaDTO;

public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personas_en_tabla;
		private VentanaPersona ventanaPersona; 
		private VentanaEditar ventanaEditar;
		private Agenda agenda;
		
		public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.ventanaEditar = VentanaEditar.getInstance();
			this.ventanaEditar.getBtnAceptar().addActionListener(w->guardarEdicion(w));
			this.vista.getBtnEditar().addActionListener(e->editarPersona(e));
			this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
			this.ventanaPersona = VentanaPersona.getInstance();
			this.ventanaPersona.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
			this.agenda = agenda;
			this.personas_en_tabla = null;
		}
		
		private void ventanaAgregarPersona(ActionEvent a) {
			this.ventanaPersona.mostrarVentana();
		}

		private void guardarPersona(ActionEvent p) {
			PersonaDTO nuevaPersona = new PersonaDTO(0,this.ventanaPersona.getTxtNombre().getText(), ventanaPersona.getTxtTelefono().getText());
			this.agenda.agregarPersona(nuevaPersona);
			this.llenarTabla();
			this.ventanaPersona.cerrar();
		}

		private void editarPersona(ActionEvent e) {
			int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			
			if(filas_seleccionadas.length>0) {
			this.ventanaEditar.mostrarVentana();
			}
		}
		
		private void guardarEdicion(ActionEvent w) {
			int columnaPersona = this.vista.getTablaPersonas().getSelectedColumn();
			int filaPersona = this.vista.getTablaPersonas().getSelectedRow();
			PersonaDTO personaAEditar = new PersonaDTO((int) this.vista.getTablaPersonas().getValueAt(filaPersona, columnaPersona), this.vista.getTablaPersonas().getValueAt(filaPersona, columnaPersona+1).toString(), this.vista.getTablaPersonas().getValueAt(filaPersona, columnaPersona+2).toString());
			System.out.println(this.vista.getTablaPersonas().getValueAt(filaPersona, columnaPersona)+", "+personaAEditar.getNombre()+", "+personaAEditar.getTelefono());
			System.out.println(ventanaEditar.getTextFieldNombre().getText()+", "+ventanaEditar.getTextFieldTelefono().getText());
				this.agenda.editarPersona((int) this.vista.getTablaPersonas().getValueAt(filaPersona, columnaPersona), ventanaEditar.getTextFieldNombre().getText(), ventanaEditar.getTextFieldTelefono().getText());
				this.llenarTabla();
			this.ventanaEditar.setVisible(false);
			
		}
		
		private void mostrarReporte(ActionEvent r) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();	
		}

		public void borrarPersona(ActionEvent s)
		{
			int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			for (int fila:filas_seleccionadas)
			{
				this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
			}
			
			this.llenarTabla();
		}
		
		public void inicializar()
		{
			this.llenarTabla();
			this.vista.show();
		}
		
		private void llenarTabla()
		{
			this.vista.getModelPersonas().setRowCount(0); //Para vaciar la tabla
			this.vista.getModelPersonas().setColumnCount(0);
			this.vista.getModelPersonas().setColumnIdentifiers(this.vista.getNombreColumnas());
			
			this.personas_en_tabla = agenda.obtenerPersonas();
			for (int i = 0; i < this.personas_en_tabla.size(); i ++)
			{
				Object[] fila = {this.personas_en_tabla.get(i).getIdPersona(),this.personas_en_tabla.get(i).getNombre(), this.personas_en_tabla.get(i).getTelefono()};
				this.vista.getModelPersonas().addRow(fila);
			}			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
		
}
