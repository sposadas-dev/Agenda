package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaEditar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private static VentanaEditar INSTANCE;
	private JTextField textFieldNombre;
	private JLabel lblNombreNuevo;
	private JLabel lblTelefonoNuevo;
	private JTextField textFieldTelefono;
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	public static VentanaEditar getInstance()
	{
		if(INSTANCE == null)
			return new VentanaEditar();
		else
			return INSTANCE;
	}

	
	private VentanaEditar() 
	{
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 541, 198);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNombreNuevo = new JLabel("Nombre nuevo:");
		lblNombreNuevo.setBounds(12, 12, 139, 15);
		contentPane.add(lblNombreNuevo);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(200, 10, 160, 19);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		lblTelefonoNuevo = new JLabel("Telefono nuevo:");
		lblTelefonoNuevo.setBounds(12, 45, 123, 15);
		contentPane.add(lblTelefonoNuevo);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(200, 43, 160, 19);
		contentPane.add(textFieldTelefono);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(384, 7, 117, 25);
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(384, 40, 117, 25);
		contentPane.add(btnCancelar);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getTextFieldNombre() {
		return textFieldNombre;
	}


	public JLabel getLblNombreNuevo() {
		return lblNombreNuevo;
	}


	public JLabel getLblTelefonoNuevo() {
		return lblTelefonoNuevo;
	}


	public JTextField getTextFieldTelefono() {
		return textFieldTelefono;
	}


	public JButton getBtnAceptar() {
		return btnAceptar;
	}


	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void cerrar()
	{
		this.textFieldNombre.setText(null);
		this.textFieldTelefono.setText(null);
		this.dispose();
	}
}
