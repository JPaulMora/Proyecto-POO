package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import resources.DBinterface;
import javax.swing.JTabbedPane;

public class MainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
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
					
					System.out.println(logWin.getPassword());
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
		
		//tabbedPane.addTab("Test", null, contentPane, "This is a test.");
		tabbedPane.addTab("Ventas",null);
		tabbedPane.addTab("Transacciones",null);
		tabbedPane.addTab("Productos",null);
		
	}
}