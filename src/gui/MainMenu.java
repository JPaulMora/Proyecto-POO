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

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int ScreenWidth = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	private static final int ScreenHeight = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	private JPanel contentPane;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String panelVentas = "Componentes de Ventas";
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
		JLabel label = new JLabel(description);
		box.add(label);
		JPanel pane = new JPanel();
		pane.setLayout(null);
		pane.add(box);
		pane.add(showPanel);
		return pane;
	   }
	
	
}
