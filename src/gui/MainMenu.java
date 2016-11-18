package gui;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import resources.DBinterface;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	


	/**
	 * Clase encargada de contener a los distintos paneles de la aplicacion, adicional a esto, la clase permite cerrar sesion.
	 * @param d Instancia de DBinterface.
	 * @param lw Referencia de LoginWindow que sirve para cuando se cierra sesion, asi se puede hacer visible de nuevo.
	 * @param h Height, sirve para centrar la interfase en la pantalla.
	 * @param w Width, sirve para centrar la interfase en la pantalla.
	 * @throws SQLException En caso hubiese un error en la creacion de los objetos que usan DBinterface.
	 */
	public MainMenu(DBinterface d,LoginWindow lw, int h, int w) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(w/2-365, h/2-250, 715, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("Test");
		tabbedPane.setBounds(0, 18, 715, 459);
		contentPane.add(tabbedPane);
		
		JPanel ventas = new VentasPanel(d);         
		JPanel estudiante = new EstudiantesPanel(d);
		JPanel empleados = new EmpleadosPanel(d);
		JPanel productos = new ProductosPanel(d);   
		
		tabbedPane.addTab("Ventas",null,ventas); 				//Tab que servira para "Cobrar".
		tabbedPane.addTab("Estudiantes",null,estudiante);		//Tab para ver estudiantes.
		tabbedPane.addTab("Empleados",null,empleados);          //Tab para ver empleados.
		tabbedPane.addTab("Productos",null,productos);			//Tab para organizar los productos.
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					logOut(d,lw);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnCerrarSesion.setBounds(513, 473, 164, 29);
		contentPane.add(btnCerrarSesion);
		
	}
	
	private void logOut(DBinterface d, LoginWindow lw) throws SQLException{
		d.closeDB();
		lw.reLogin();
		dispose();
	}
}
