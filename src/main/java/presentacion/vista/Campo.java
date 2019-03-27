package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;

public class Campo extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JButton btnGuardar;
	private int idTipo;
	private JPanel contentPane;
	protected JPanel panel;
	public enum Tipo { Edicion, NoEdicion};
	private Campo.Tipo tipo;
	private JTextField entrada;

	
	
	public Campo(){
		super();
	}
	public Campo( Controlador controlador, Campo.Tipo tipo){
		super();
		
		this.tipo = tipo;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.panel = new JPanel();
		panel.setBounds(10, 11, 307, 400);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnGuardar = new JButton("Aceptar");
		btnGuardar.addActionListener(controlador);
		btnGuardar.setBounds(90, 40, 80 , 20);
		panel.add(btnGuardar);
			
		entrada = new JTextField();
		entrada.setBounds(50, 90, 164, 20);
		panel.add(entrada);
		entrada.setColumns(10);

		this.setVisible(true);
	}
	public JButton getBtnGuardar() 
	{
		return btnGuardar;
	}
	
	public int getIdT()
	{
		return idTipo;
	}
	
	public void setId(int id)
	{
		this.idTipo = id;
	}
	
	public JTextField getEntrada() {
		return entrada;
	}
	public Campo.Tipo getTipo(){
		return tipo;
	}
}