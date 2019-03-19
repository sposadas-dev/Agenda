package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import presentacion.controlador.Controlador;

public class VentanaCambios extends JFrame{
	private static final long serialVersionUID = 1L;
	protected JPanel panel;
	protected Controlador controlador;
	private JPanel contentPane;
	private JTable tabla;	
	private DefaultTableModel modelcambios;
	private  String[] nombreColumnas;
	public enum Tipo {Localidades , TiposContacto};
	private VentanaCambios.Tipo tipo;
	
	
	//buttons
	private JButton agregar;
	private JButton Borrar;
	private JButton aceptar;
	private JButton editar;
	
	
	public VentanaCambios(){
		super();
	}
	public VentanaCambios( Controlador controlador, VentanaCambios.Tipo tipo){
		super();
		this.controlador = controlador;
		this.tipo = tipo;
		crearColumna(tipo);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 345, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.panel = new JPanel();
		panel.setBounds(10, 11, 307, 400);
		contentPane.add(panel);
		panel.setLayout(null);
		
		this.setVisible(true);
		
		
		JScrollPane spLocalidades = new JScrollPane();
		spLocalidades.setBounds(10, 11, 200, 390);
		panel.add(spLocalidades);
		
		modelcambios = new DefaultTableModel(null,nombreColumnas);
		tabla = new JTable(modelcambios);
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(103);
		tabla.getColumnModel().getColumn(0).setResizable(false);
		spLocalidades.setViewportView(tabla);
		
		agregar = new JButton("Agregar");
		agregar.addActionListener(this.controlador);
		agregar.setBounds(220, 11, 80 , 20);
		panel.add(agregar);
		
		Borrar = new JButton("Borrar");
		Borrar.addActionListener(this.controlador);
		Borrar.setBounds(220, 41, 80 , 20);
		panel.add(Borrar);
		
		aceptar = new JButton( "Aceptar");
		aceptar.addActionListener(this.controlador);
		aceptar.setBounds(220, 71 , 80 , 20 );
		panel.add(aceptar);
		
		editar = new JButton( "Editar");
		editar.addActionListener(this.controlador);
		editar.setBounds(220, 101 , 80 , 20 );
		panel.add(editar);
	
	}
	private void crearColumna(Tipo tipo2) {
		if (tipo == VentanaCambios.Tipo.Localidades)
		{
			String [] c ={"Localidades"};			
			this.nombreColumnas = c;
		}
		if (tipo == VentanaCambios.Tipo.TiposContacto)
		{
			String []c = {"Tipos de contacto"};
			this.nombreColumnas = c;
		}
		
	}
	public DefaultTableModel getModelcambios() {
		return modelcambios;
	}
	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
	public JTable getTabla() {
		return tabla;
	}
	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}
	public JButton getAgregar() {
		return agregar;
	}
	public JButton getBorrar() {
		return Borrar;
	}
	public VentanaCambios.Tipo getTipo(){
		return this.tipo;
	}
	public JButton getAceptar(){
		return aceptar;
	}
	public JButton getEditar()
	{
		return editar;
	}
	

}

