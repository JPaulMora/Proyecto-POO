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
	private DBinterface db;

	/**
	 * Create the dialog.
	 */
	public LoginWindow(DBinterface d,MainMenu m) {
																			// Creacion de LoginWindow
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 344, 173);
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
							// si las credenciales son incorrectas desplegamos la advertencia, si es correcto eliminamos el JDialog y desplegamos la interfaz principal
							if (!db.connectDB(logintextField.getText(),String.valueOf(passwordField.getPassword()))){
								lblWrongloginLabel.setVisible(true);
							}else{
								m.setVisible(true);
								dispose();
							}
						} catch (SQLException e1) {
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
				
				
				//agregamos los elementos al buttonpane en el orden que los queremos (de izquierda a derecha)
				buttonPane.add(lblWrongloginLabel);
				buttonPane.add(cancelButton);
				buttonPane.add(loginButton);
				getRootPane().setDefaultButton(loginButton);
				
				
				
				
			}
			
		}
		
		//Objetos extras del constructor
		 this.db = d;
	}
	
	public String getUsername(){
		return logintextField.getText();
	}
	
	public String getPassword(){
		return String.valueOf(passwordField.getPassword());
	}
}
