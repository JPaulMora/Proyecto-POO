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
		this.setBounds(285, 83, 699, 164); //Como agregamos el panel a un JWindow, esto no importa. Lo ponemos solo para que se vea bien en el Design tab.
		
		JButton btnIngresarEstudiante = new JButton("Ingresar Estudiante");
		btnIngresarEstudiante.setBounds(35, 79, 175, 23);
		this.add(btnIngresarEstudiante);
		
		JButton btnVerEstudiante = new JButton("Ver Estudiante");
		btnVerEstudiante.setBounds(35, 33, 175, 23);
		this.add(btnVerEstudiante);
		
		JButton btnEliminarEstudiante = new JButton("Eliminar Estudiante");
		btnEliminarEstudiante.setBounds(35, 125, 175, 23);
		this.add(btnEliminarEstudiante);
		
		JButton btnGraficasDeEstudiantes = new JButton("Compras de Estudiantes");
		btnGraficasDeEstudiantes.setBounds(35, 171, 175, 23);
		this.add(btnGraficasDeEstudiantes);
		
		JButton btnVer = new JButton("Ver Comida");
		btnVer.setBounds(266, 33, 163, 23);
		this.add(btnVer);
		
		JButton btnIngresarComida = new JButton("Ingresar Comida");
		btnIngresarComida.setBounds(266, 79, 163, 23);
		this.add(btnIngresarComida);
		
		JButton btnEliminarComida = new JButton("Eliminar Comida");
		btnEliminarComida.setBounds(266, 125, 163, 23);
		this.add(btnEliminarComida);
		
		JButton btnNewButton = new JButton("Demanda de Comida");
		btnNewButton.setBounds(266, 171, 163, 23);
		this.add(btnNewButton);
		
		JButton btnVerEmpleado = new JButton("Ver Empleado");
		btnVerEmpleado.setBounds(491, 33, 163, 23);
		this.add(btnVerEmpleado);
		
		JButton btnIngresarEmpleado = new JButton("Ingresar Empleado");
		btnIngresarEmpleado.setBounds(491, 79, 163, 23);
		this.add(btnIngresarEmpleado);
		
		JButton btnEliminarEmpleado = new JButton("Eliminar Empleado");
		btnEliminarEmpleado.setBounds(491, 125, 163, 23);
		this.add(btnEliminarEmpleado);
		
		JButton btnVentasPorEmpleado = new JButton("Ventas por Empleado");
		btnVentasPorEmpleado.setBounds(491, 171, 163, 23);
		this.add(btnVentasPorEmpleado);
	   }

}
