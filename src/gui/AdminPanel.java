package gui;

import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JPanel;

import resources.DBinterface;

public class AdminPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public AdminPanel(DBinterface d) {
		this.setLayout(null);
		this.setBounds(285, 83, 699, 367);
		
		JButton btnVerEstudiante = new JButton("Administrar Estudiantes");
		btnVerEstudiante.setBounds(35, 33, 175, 23);
		this.add(btnVerEstudiante);
		
		JButton btnVer = new JButton("Administrar Productos");
		btnVer.setBounds(248, 33, 181, 23);
		this.add(btnVer);
		
		JButton btnVerEmpleado = new JButton("Administrar Empleados");
		btnVerEmpleado.setBounds(473, 33, 181, 23);
		this.add(btnVerEmpleado);
	   }

}
