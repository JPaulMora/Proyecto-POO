package gui;

import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import resources.DBinterface;
import javax.swing.JTextField;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int ScreenWidth = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	private static final int ScreenHeight = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	private JPanel contentPane;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String panelVentas = "Componentes de Ventas";
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
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
					LoginWindow logWin = new LoginWindow(ScreenWidth, ScreenHeight,db,frame);
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
		setBounds(ScreenWidth/2-365, ScreenHeight/2-250, 730, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("Test");
		tabbedPane.setBounds(0, 19, 730, 459);
		contentPane.add(tabbedPane);
		
		JPanel frequentPanel = creacionCuadro();
		JPanel frequentPanel2 = new AdminPanel(panelVentas+":");             //Cambiamos metodo por constructor.
		
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
		JLabel lblRestaurante = new JLabel("Restaurante 1:");
		box.add(lblRestaurante);
		JPanel pane = new JPanel();
		pane.setLayout(null);
		pane.add(box);
		pane.add(showPanel);
		
		JLabel lblNewLabel = new JLabel("ID Producto");
		lblNewLabel.setBounds(45, 35, 85, 14);
		pane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(21, 60, 137, 179);
		pane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNombreProducto = new JLabel("Nombre Producto");
		lblNombreProducto.setBounds(302, 35, 115, 14);
		pane.add(lblNombreProducto);
		
		JLabel lblPrecioProduco = new JLabel("Precio Producto");
		lblPrecioProduco.setBounds(561, 35, 99, 14);
		pane.add(lblPrecioProduco);
		
		textField_1 = new JTextField();
		textField_1.setBounds(208, 60, 277, 179);
		pane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(543, 60, 137, 179);
		pane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblIngreseIdDe = new JLabel("Ingrese ID de Producto");
		lblIngreseIdDe.setBounds(31, 260, 129, 14);
		pane.add(lblIngreseIdDe);
		
		textField_3 = new JTextField();
		textField_3.setBounds(208, 257, 86, 20);
		pane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnAgregarCargo = new JButton("Agregar Cargo");
		btnAgregarCargo.setBounds(326, 256, 119, 23);
		pane.add(btnAgregarCargo);
		
		JLabel lblIngreseIdQue = new JLabel("Ingrese ID que desea Remover");
		lblIngreseIdQue.setBounds(30, 296, 162, 14);
		pane.add(lblIngreseIdQue);
		
		textField_4 = new JTextField();
		textField_4.setBounds(208, 293, 86, 20);
		pane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("Remover Cargo");
		btnNewButton.setBounds(326, 290, 119, 23);
		pane.add(btnNewButton);
		
		textField_5 = new JTextField();
		textField_5.setBounds(475, 262, 205, 147);
		pane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblIngrese = new JLabel("Ingrese el Carnet del Usuario");
		lblIngrese.setBounds(31, 338, 161, 14);
		pane.add(lblIngrese);
		
		textField_6 = new JTextField();
		textField_6.setBounds(208, 335, 86, 20);
		pane.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblSubtotal = new JLabel("Sub-Total");
		lblSubtotal.setBounds(31, 373, 46, 14);
		pane.add(lblSubtotal);
		
		textField_7 = new JTextField();
		textField_7.setBounds(208, 370, 86, 20);
		pane.add(textField_7);
		textField_7.setColumns(10);
		
		JButton btnFacturar = new JButton("Facturar");
		btnFacturar.setBounds(328, 369, 117, 23);
		pane.add(btnFacturar);
		return pane;
	   }
}
