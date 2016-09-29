package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.ScrollPaneConstants;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EstudiantesPanel extends JPanel {
	private JTable tableVerEstudiante;
	private JTable table_1;
	private JTable tableAgregarEstudiante;
	private JTextField tfEliminarEstudiante;

	/**
	 * Create the panel.
	 */
	public EstudiantesPanel() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Ver Estudiante", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 548, 154);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 22, 528, 121);
		panel.add(scrollPane);
		
		tableVerEstudiante = new JTable();
		tableVerEstudiante.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Carnet", "Nombre", "Carrera", "Disponibilidad", "Consumo"
			}
		));
		scrollPane.setViewportView(tableVerEstudiante);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Agregar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 170, 323, 154);
		add(panel_1);
		panel_1.setLayout(null);
		
		table_1 = new JTable();
		table_1.setBorder(new LineBorder(SystemColor.control));
		table_1.setShowHorizontalLines(false);
		table_1.setBackground(SystemColor.control);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"Carnet"},
				{"Nombre"},
				{"Carrera"},
				{"Disponibilidad"},
				{"Consumo"},
			},
			new String[] {
				"New column"
			}
		));
		table_1.setBounds(10, 23, 82, 80);
		panel_1.add(table_1);
		
		tableAgregarEstudiante = new JTable();
		tableAgregarEstudiante.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableAgregarEstudiante.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"New column"
			}
		));
		tableAgregarEstudiante.setBounds(99, 23, 214, 80);
		panel_1.add(tableAgregarEstudiante);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(109, 114, 89, 23);
		panel_1.add(btnAgregar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Eliminar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(341, 170, 217, 154);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblCarnet = new JLabel("Carnet");
		lblCarnet.setBounds(81, 30, 46, 14);
		panel_2.add(lblCarnet);
		
		tfEliminarEstudiante = new JTextField();
		tfEliminarEstudiante.setBounds(61, 55, 86, 20);
		panel_2.add(tfEliminarEstudiante);
		tfEliminarEstudiante.setColumns(10);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(61, 86, 89, 23);
		panel_2.add(btnEliminar);
		
		JButton btnVerComprasPor = new JButton("Ver Compras por Estudiante");
		btnVerComprasPor.setBounds(195, 335, 211, 23);
		add(btnVerComprasPor);

	}
}
