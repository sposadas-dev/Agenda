package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.LocalidadDTO;
import dto.TipoContactoDTO;
import presentacion.controlador.Controlador;
import com.github.lgooddatepicker.components.DatePicker;
public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JTextField txtNombre;
	protected JTextField txtTelefono;
	private JButton cambiarTipos;
	private JButton cambiarLocalidades;
	public enum TipoVentana { Editable , NoEditable};
	private JButton btnAceptar;
	protected JTextField txtCalle;
	protected JTextField txtDpto;
	protected JTextField txtEmail;
	protected DatePicker cumpleanios;
	protected JComboBox<LocalidadDTO> combo_localidades;
	protected JComboBox<TipoContactoDTO> combo_tipo_contacto;
	private int idPersona;
	protected JPanel panel;
	protected Controlador controlador;
	VentanaPersona.TipoVentana  Tipo;
	
	public VentanaPersona() {
		
	}
	
	public VentanaPersona(Controlador controlador, VentanaPersona.TipoVentana tipo) 
	{
		super();
		this.controlador = controlador;
		this.Tipo = tipo;
		
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
		
		generarBoton(tipo);
		
		//ABM de los tipos y localidad.
		cambiarTipos = new JButton("...");
		cambiarTipos.addActionListener(this.controlador);
		cambiarTipos.setBounds(285, 295, 20, 20);
		panel.add(cambiarTipos);
		
		cambiarLocalidades = new JButton("...");
		cambiarLocalidades.addActionListener(this.controlador);
		cambiarLocalidades.setBounds(285, 172, 20, 20);
		panel.add(cambiarLocalidades);
		
		JLabel lblNombreApellido = new JLabel("Nombre Apellido");
		lblNombreApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreApellido);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);
		
		JLabel lblcalle = new JLabel("Calle");
		lblcalle.setBounds(10, 93, 113, 14);
		panel.add(lblcalle);
		
		JLabel lbldepartamento = new JLabel("Dpto");
		lbldepartamento.setBounds(10, 134, 113, 14);
		panel.add(lbldepartamento);
		
		JLabel lbllocalidad = new JLabel("Localidad");
		lbllocalidad.setBounds(10, 175, 113, 14);
		panel.add(lbllocalidad);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setBounds(10, 216, 113, 14);
		panel.add(lblemail);
		
		JLabel lblcumpleanios = new JLabel("Cumpleanios");
		lblcumpleanios.setBounds(10, 257, 113, 14);
		panel.add(lblcumpleanios);
		
		JLabel lbletiqueta = new JLabel("Tipo de contacto");
		lbletiqueta.setBounds(10, 298, 113, 14);
		panel.add(lbletiqueta);
		
		btnAceptar = new JButton( "Aceptar");
		btnAceptar.addActionListener(this.controlador);
		btnAceptar.setBounds(110, 345 , 180 , 40);
		panel.add(btnAceptar);
		
		//JtextFields
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtCalle = new JTextField();
		txtCalle.setBounds(133, 90, 164, 20);
		panel.add(txtCalle);
		txtCalle.setColumns(10);
		
		txtDpto = new JTextField();
		txtDpto.setBounds(133, 131, 164, 20);
		panel.add(txtDpto);
		txtDpto.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(133, 213, 164, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		cumpleanios = new DatePicker();
		cumpleanios.setBounds(105, 254, 200, 20);
		cumpleanios.getComponentDateTextField().setEditable(false);
		panel.add(cumpleanios);
		
		//COMBOBOX
		combo_localidades = new JComboBox<LocalidadDTO>();
		combo_localidades.setBounds(110, 172, 164, 20);
		panel.add(combo_localidades);

		combo_tipo_contacto = new JComboBox<TipoContactoDTO>();
		combo_tipo_contacto.setBounds(110, 295, 164, 20);
		panel.add(combo_tipo_contacto);
		
		this.setVisible(true);	
		

	}
	
	private void generarBoton(VentanaPersona.TipoVentana tipo )
	{
		
	}
	
	public JButton getAceptar()
	{
		return btnAceptar;
	}

	public JButton getCambiarTipos() {
		return cambiarTipos;
	}
	public JButton getCambiarLocalidades() {
		return cambiarLocalidades;
	}
	public JTextField getTxtCalle() {
		return txtCalle;
	}

	public JTextField getTxtDpto() {
		return txtDpto;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public DatePicker getCumpleanios() {
		return cumpleanios;
	}

	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}

	public JComboBox<LocalidadDTO> getLocalidad() {
		return combo_localidades;
	}
	
	public JComboBox<TipoContactoDTO> getTipoContacto() {
		return combo_tipo_contacto;
	}
	
	public int getIdPersona()
	{
		return idPersona;
	}
	
	public void setIdPersona(int id)
	{
		this.idPersona = id;
	}
	public VentanaPersona.TipoVentana getTipo()
	{
		return this.Tipo;
	}
}

