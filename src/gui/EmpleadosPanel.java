package gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EmpleadosPanel extends JPanel {
	private JTable tableVerEmpleados;
	private JTable table_1;
	private JTable tableAgregarEmpleado;
	private JTextField tfIngresarDpi;

	/**
	 * Create the panel.
	 */
	public EmpleadosPanel() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ver Empleados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 546, 169);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 27, 526, 131);
		panel.add(scrollPane);
		
		tableVerEmpleados = new JTable();
		tableVerEmpleados.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, "", null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"DPI", "Nombre", "Tipo de Autorizacion"
			}
		));
		scrollPane.setViewportView(tableVerEmpleados);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Agregar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 190, 324, 128);
		add(panel_1);
		panel_1.setLayout(null);
		
		table_1 = new JTable();
		table_1.setBorder(new LineBorder(SystemColor.control));
		table_1.setBackground(SystemColor.control);
		table_1.setShowHorizontalLines(false);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"DPI"},
				{"Nombre"},
				{"Tipo de Autorizacion"},
			},
			new String[] {
				"New column"
			}
		));
		table_1.setBounds(10, 25, 112, 48);
		panel_1.add(table_1);
		
		tableAgregarEmpleado = new JTable();
		tableAgregarEmpleado.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableAgregarEmpleado.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
			},
			new String[] {
				"New column"
			}
		));
		tableAgregarEmpleado.setBounds(122, 23, 178, 48);
		panel_1.add(tableAgregarEmpleado);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(122, 84, 89, 23);
		panel_1.add(btnAgregar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Eliminar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(344, 191, 212, 128);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblDpi = new JLabel("DPI");
		lblDpi.setBounds(92, 23, 36, 14);
		panel_2.add(lblDpi);
		
		tfIngresarDpi = new JTextField();
		tfIngresarDpi.setBounds(43, 48, 123, 20);
		panel_2.add(tfIngresarDpi);
		tfIngresarDpi.setColumns(10);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(64, 81, 89, 23);
		panel_2.add(btnEliminar);
		
		JButton btnVerVentasPor = new JButton("Ver Ventas por Empleado");
		btnVerVentasPor.setBounds(204, 340, 212, 23);
		add(btnVerVentasPor);

	}

}
