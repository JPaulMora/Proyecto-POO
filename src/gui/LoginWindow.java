package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import resources.DBinterface;

public class LoginWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField logintextField;
	private JPasswordField passwordField;
	private JLabel lblWrongloginLabel;
	private JLabel lblNotConnected;
	private DBinterface db;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final int ScreenWidth = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	private static final int ScreenHeight = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	private LoginWindow lw = this;
	
	/**
	 * Create the dialog.
	 * @param d Este parametro debe ser una instancia de DBinterface.
	 * @param m Este parametro debe ser la ventana principal.
	 */
	
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		DBinterface db = new DBinterface();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow logWin = new LoginWindow(ScreenWidth, ScreenHeight,db);
					logWin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LoginWindow(int w, int h,DBinterface d) {
																			// Creacion de LoginWindow
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(w/2-172, h/2-86, 344, 172);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
																			//Creacion de JLabels
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(16, 23, 63, 16);
		contentPanel.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(16, 62, 73, 16);
		contentPanel.add(lblPassword);
																			//Creacion de  Textfield y passwordfield.
		logintextField = new JTextField();
		logintextField.setBounds(91, 18, 241, 26);
		contentPanel.add(logintextField);
		logintextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(91, 57, 241, 26);
		contentPanel.add(passwordField);
		
		
																			//Creacion de botones Iniciar y Cancelar. (button pane)
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton loginButton = new JButton("Iniciar");
				loginButton.setActionCommand("Iniciar");
				
																			//Action Listener de Login
				loginButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						try {
							// si las credenciales son incorrectas desplegamos la advertencia,
							// si en cambio son correctas eliminamos el JDialog (Esta ventana) y desplegamos la interfaz principal (Main Menu).
							
							if (!db.connectDB(logintextField.getText(),String.valueOf(passwordField.getPassword()))){ 
								//Si llegamos a este punto, quiere decir que hubo un error de login..
								
								if(!db.getConnected()){ //revisamos si fue un error de conexion o de Login y activamos el mensaje respectivo.
									lblNotConnected.setVisible(true);
								}else{
									lblWrongloginLabel.setVisible(true);
								}
							}else{ 
								//Login fue correcto.. activamos el MainMenu (m) y eliminamos esta ventana (LoginWindow).
								setVisible(false);
								
								MainMenu frame = new MainMenu(db,lw,ScreenHeight,ScreenWidth);
								frame.setVisible(true);
							}
						} catch (SQLException e1) {
							//Si hubiera un error de SQL, el programa lo imprime en la consola.
							e1.printStackTrace();
						}
						
					}
				}
						);
				
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancelar");
				
				//Cancelar el login cierra el programa
				cancelButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						System.exit(0);
					}
				});
				
				// Label inicialmente oculto que aparece cuando las credenciales son incorrectas.
				lblWrongloginLabel = new JLabel("Login Incorrecto");
				lblWrongloginLabel.setVisible(false);
				
				//Label inicialmente oculta que aparece cuando no se puede conectar con la DB.
				lblNotConnected = new JLabel("No se pudo conectar.");
				lblNotConnected.setVisible(false);
				
				
				//agregamos los elementos al buttonpane en el orden que los queremos (de izquierda a derecha).
				buttonPane.add(lblWrongloginLabel);
				buttonPane.add(lblNotConnected);
				buttonPane.add(cancelButton);
				buttonPane.add(loginButton);
				getRootPane().setDefaultButton(loginButton); //Al hacer Login el default, presionar ENTER inicia sesion.
				
				
				
				
			}
			
		}
		
		//Objetos extras del constructor
		 this.db = d;
	}
	
	public void reLogin(){
		setVisible(true);
		passwordField.setText("");
	}
}
