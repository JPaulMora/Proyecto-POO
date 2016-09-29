package gui;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import resources.DBinterface;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int ScreenWidth = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	private static final int ScreenHeight = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	private JPanel contentPane;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private DBinterface d;

//main() movido a LoginWindow

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public MainMenu(DBinterface d) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(ScreenWidth/2-365, ScreenHeight/2-250, 730, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("Test");
		tabbedPane.setBounds(0, 19, 730, 459);
		contentPane.add(tabbedPane);
		
		JPanel ventas = new VentasPanel(d);         
		JPanel estudiante = new EstudiantesPanel(d);
		JPanel empleados = new EmpleadosPanel(d);
		JPanel productos = new ProductosPanel(d);   
		
		tabbedPane.addTab("Ventas",null,ventas); 				//Tab que servira para "Cobrar".
		tabbedPane.addTab("Estudiantes",null,estudiante);		//Tab para ver estudiantes.
		tabbedPane.addTab("Empleados",null,empleados);          //Tab para ver empleados.
		tabbedPane.addTab("Productos",null,productos);			//Tab para organizar los productos.
		
	}
	

}
