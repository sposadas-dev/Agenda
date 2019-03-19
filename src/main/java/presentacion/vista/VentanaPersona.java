package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.LocalidadDTO;
import dto.TipoDeContactoDTO;

import javax.swing.JComboBox;

public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JButton btnAgregarPersona;
	private JButton btnAgregarMasTipos;
	private JButton btnAgregarMasLocalidades;
	private static VentanaPersona INSTANCE;
	private JTextField textField;
	private JLabel lblAltura;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblPiso;
	
	public static VentanaPersona getInstance()
	{
		if(INSTANCE == null)
			return new VentanaPersona();
		else
			return INSTANCE;
	}

	private VentanaPersona() 
	{
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 552, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 0, 528, 320);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(22, 28, 136, 14);
		panel.add(lblNombreYApellido);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(164, 26, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(164, 50, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		btnAgregarPersona = new JButton("Agregar contacto");

		btnAgregarPersona.setBounds(348, 7, 157, 23);
		panel.add(btnAgregarPersona);
		
		JLabel label = new JLabel("Telefono");
		label.setBounds(22, 52, 113, 14);
		panel.add(label);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(22, 79, 70, 15);
		panel.add(lblCalle);
		
		textField = new JTextField();
		textField.setBounds(164, 77, 164, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		lblAltura = new JLabel("Altura");
		lblAltura.setBounds(22, 106, 70, 15);
		panel.add(lblAltura);
		
		textField_1 = new JTextField();
		textField_1.setBounds(164, 100, 164, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(164, 122, 164, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		lblPiso = new JLabel("Piso");
		lblPiso.setBounds(22, 124, 70, 15);
		panel.add(lblPiso);
		
		JComboBox<TipoDeContactoDTO> comboBox = new JComboBox<TipoDeContactoDTO>();
		comboBox.setBounds(164, 153, 164, 24);
		panel.add(comboBox);
		
		JLabel lblTipoDeContacto = new JLabel("Tipo de contacto");
		lblTipoDeContacto.setBounds(22, 158, 136, 15);
		panel.add(lblTipoDeContacto);
		
		btnAgregarMasTipos = new JButton("Agregar mas tipos de contacto");
		btnAgregarMasTipos.setBounds(22, 245, 306, 20);
		panel.add(btnAgregarMasTipos);
		
		JComboBox<LocalidadDTO> comboBox_1 = new JComboBox<LocalidadDTO>();
		comboBox_1.setBounds(164, 189, 164, 24);
		panel.add(comboBox_1);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(22, 194, 87, 15);
		panel.add(lblLocalidad);
		
		btnAgregarMasLocalidades = new JButton("Agregar mas localidades");
		btnAgregarMasLocalidades.setBounds(22, 282, 306, 20);
		panel.add(btnAgregarMasLocalidades);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}

	public JButton getBtnAgregarPersona() 
	{
		return btnAgregarPersona;
	}
	
	public JButton getBtnAgregarMasTipos() {
		return btnAgregarMasTipos;
	}
	
	public JButton getBtnAgregarMasLocal() {
		return btnAgregarMasLocalidades;
	}

	public void cerrar()
	{
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.dispose();
	}
}


