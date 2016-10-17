package gui;

import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import resources.DBinterface;

public class ItemsPor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboBox;
	
	/**
	 * Esta clase sirve para mostrar los datos del boton "Ventas por Empleado" y "Compras por Estudiante"
	 * @throws SQLException 
	 * 
	 */
	
	public ItemsPor(DBinterface d, int mode) throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		comboBox = new JComboBox(d.getAsArray(mode));
		
		
		comboBox.setBounds(159, 5, 209, 28);
		contentPane.add(comboBox);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 33, 529, 290);
		contentPane.add(tabbedPane);
	}
}
