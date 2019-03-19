package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;

public class VentanaAgregarLocalidades extends JFrame {

	/**
	 * 
	 */
	private JFrame frame;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaAgregarLocalidades INSTANCE;
	private  String[] nombreColumnas = {"id","Nombre"};
	private DefaultTableModel modelLocalidades;
	private JTable tablaLocalidades;


	
	public static VentanaAgregarLocalidades getInstance()
	{
		if(INSTANCE == null)
			return new VentanaAgregarLocalidades();
		else
			return INSTANCE;
	}



	public VentanaAgregarLocalidades() {
		super();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 578, 364);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		setBounds(100, 100, 648, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		this.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 578, 336);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spLocalidades = new JScrollPane();
		spLocalidades.setBounds(10, 11, 556, 217);
		panel.add(spLocalidades);
		
		modelLocalidades = new DefaultTableModel(null,nombreColumnas);
		tablaLocalidades = new JTable(modelLocalidades);
		
		tablaLocalidades.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaLocalidades.getColumnModel().getColumn(0).setResizable(false);
		tablaLocalidades.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaLocalidades.getColumnModel().getColumn(1).setResizable(false);
		
		spLocalidades.setViewportView(tablaLocalidades);


	}
	
	public void mostrarVentana()
	{
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "Estas seguro que quieres salir de la Agenda!?", 
		             "Confirmacion", JOptionPane.YES_NO_OPTION,
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		        	Conexion.getConexion().cerrarConexion();
		           System.exit(0);
		        }
		    }
		});
		this.setVisible(true);
	}

}
