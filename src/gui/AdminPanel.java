package gui;

import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AdminPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public AdminPanel() {
		this.setLayout(null);
		this.setBounds(285, 83, 699, 164);
		
		JButton btnVerEstudiante = new JButton("Administrar Estudiantes");
		btnVerEstudiante.setBounds(35, 33, 175, 23);
		this.add(btnVerEstudiante);
		
		JButton btnGraficasDeEstudiantes = new JButton("Compras de Estudiantes");
		btnGraficasDeEstudiantes.setBounds(35, 171, 175, 23);
		this.add(btnGraficasDeEstudiantes);
		
		JButton btnVer = new JButton("Administrar Productos");
		btnVer.setBounds(248, 33, 181, 23);
		this.add(btnVer);
		
		JButton btnNewButton = new JButton("Demanda de Comida");
		btnNewButton.setBounds(266, 171, 163, 23);
		this.add(btnNewButton);
		
		JButton btnVerEmpleado = new JButton("Administrar Empleados");
		btnVerEmpleado.setBounds(473, 33, 181, 23);
		this.add(btnVerEmpleado);
		
		JButton btnVentasPorEmpleado = new JButton("Ventas por Empleado");
		btnVentasPorEmpleado.setBounds(491, 171, 163, 23);
		this.add(btnVentasPorEmpleado);
	   }

}
