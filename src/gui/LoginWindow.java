package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import resources.DBinterface;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class LoginWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField logintextField;
	private JPasswordField passwordField;
	private JLabel lblWrongloginLabel;
	private JLabel lblNotConnected;
	private DBinterface db;

	
	/**
	 * Create the dialog.
	 * @param d Este parametro debe ser una instancia de DBinterface.
	 * @param m Este parametro debe ser la ventana principal.
	 */
	public LoginWindow(int w, int h,DBinterface d,MainMenu m) {
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
								m.setVisible(true);
								dispose();
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
	//Getters.. talvez no sean necesarios.
	/**
	 * @deprecated
	 * @return Devuelve el nombre usado para inicar sesion.
	 */
	public String getUsername(){
		return logintextField.getText();
	}
	/**
	 * @deprecated
	 * @return Devuelve en password usado para inicar sesion.
	 */
	public String getPassword(){
		return String.valueOf(passwordField.getPassword());
	}
}
