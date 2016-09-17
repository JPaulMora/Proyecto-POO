package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import resources.DBinterface;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String panelVentas = "Componentes de Ventas";
	private JButton showItButton_1;
	private JButton showItButton_2;
	//private LoginWindow logWin;

	/**
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		DBinterface db = new DBinterface();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					LoginWindow logWin = new LoginWindow(db,frame);
					logWin.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("Test");
		tabbedPane.setBounds(0, 19, 730, 459);
		contentPane.add(tabbedPane);
		
		JPanel frequentPanel = creacionCuadro();
		JPanel frequentPanel2 = creacionCuadro1();
		
		tabbedPane.addTab("Ventas",null,frequentPanel,panelVentas); 				//Tab que servira para "Cobrar".
		tabbedPane.addTab("Administracion",null,frequentPanel2);			//Tab para organizar los productos.
		
	}
	
	private JPanel creacionCuadro() {
		Panel Panel = null;
        Panel = new Panel();
        Panel.setBounds(285, 83, 2, 2);
        return createPane(panelVentas+ ":",Panel);
    }

	private JPanel createPane(String description,Panel showPanel) {		   
		JPanel box = new JPanel();
		box.setBounds(0, 0, 725, 24);
		JLabel label = new JLabel(description);
		box.add(label);
		JPanel pane = new JPanel();
		pane.setLayout(null);
		pane.add(box);
		pane.add(showPanel);
		return pane;
	   }
	
	private JPanel creacionCuadro1() {
		Panel Panel = null;
        Panel = new Panel();
        Panel.setBounds(285, 83, 2, 2);
        return createPane1(panelVentas+ ":",Panel);
    }
	
	private JPanel createPane1(String description,Panel showPanel) {
		JPanel pane = new JPanel();
		pane.setLayout(null);
		pane.add(showPanel);
		
		JButton btnIngresarEstudiante = new JButton("Ingresar Estudiante");
		btnIngresarEstudiante.setBounds(35, 79, 175, 23);
		pane.add(btnIngresarEstudiante);
		
		JButton btnVerEstudiante = new JButton("Ver Estudiante");
		btnVerEstudiante.setBounds(35, 33, 175, 23);
		pane.add(btnVerEstudiante);
		
		JButton btnEliminarEstudiante = new JButton("Eliminar Estudiante");
		btnEliminarEstudiante.setBounds(35, 125, 175, 23);
		pane.add(btnEliminarEstudiante);
		
		JButton btnGraficasDeEstudiantes = new JButton("Compras de Estudiantes");
		btnGraficasDeEstudiantes.setBounds(35, 171, 175, 23);
		pane.add(btnGraficasDeEstudiantes);
		
		JButton btnVer = new JButton("Ver Comida");
		btnVer.setBounds(266, 33, 163, 23);
		pane.add(btnVer);
		
		JButton btnIngresarComida = new JButton("Ingresar Comida");
		btnIngresarComida.setBounds(266, 79, 163, 23);
		pane.add(btnIngresarComida);
		
		JButton btnEliminarComida = new JButton("Eliminar Comida");
		btnEliminarComida.setBounds(266, 125, 163, 23);
		pane.add(btnEliminarComida);
		
		JButton btnNewButton = new JButton("Demanda de Comida");
		btnNewButton.setBounds(266, 171, 163, 23);
		pane.add(btnNewButton);
		
		JButton btnVerEmpleado = new JButton("Ver Empleado");
		btnVerEmpleado.setBounds(491, 33, 163, 23);
		pane.add(btnVerEmpleado);
		
		JButton btnIngresarEmpleado = new JButton("Ingresar Empleado");
		btnIngresarEmpleado.setBounds(491, 79, 163, 23);
		pane.add(btnIngresarEmpleado);
		
		JButton btnEliminarEmpleado = new JButton("Eliminar Empleado");
		btnEliminarEmpleado.setBounds(491, 125, 163, 23);
		pane.add(btnEliminarEmpleado);
		
		JButton btnVentasPorEmpleado = new JButton("Ventas por Empleado");
		btnVentasPorEmpleado.setBounds(491, 171, 163, 23);
		pane.add(btnVentasPorEmpleado);
		return pane;
	   }
}
