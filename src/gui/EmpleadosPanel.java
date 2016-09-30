package gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import resources.DBinterface;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class EmpleadosPanel extends JPanel {
	private JTable tableVerEmpleados;
	private String[] Empleados;
	private JComboBox comboBox;
	private JTextField txtDPI;
	private JTextField txtNombres;
	private JTextField txtApellidos;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public EmpleadosPanel(DBinterface d) throws SQLException {
		setLayout(null);
		
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ver Empleados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setBounds(10, 11, 688, 403);

		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 27, 661, 131);
		add(scrollPane);
		
		tableVerEmpleados = new JTable();
		tableVerEmpleados.setModel(d.getEmpleados());
		scrollPane.setViewportView(tableVerEmpleados);
		
		JPanel pnAgregar = new JPanel();
		pnAgregar.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Agregar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnAgregar.setBounds(10, 170, 371, 175);
		add(pnAgregar);
		pnAgregar.setLayout(null);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					long b = Long.parseLong(txtDPI.getText());
					d.addEmpleado(b, txtNombres.getText(), txtApellidos.getText());
					updateData(d);
					
				}
				catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "DPI solo debe contener numeros.","Error!", JOptionPane.ERROR_MESSAGE);
				}
				catch(SQLException e2){
					System.out.println("SQLException: ");
					JOptionPane.showMessageDialog(null, "Ver Consola.","Error SQL", JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
				}
			}
		});
		btnAgregar.setBounds(233, 120, 89, 23);
		pnAgregar.add(btnAgregar);
		
		txtDPI = new JTextField();
		txtDPI.setText("DPI");
		txtDPI.setBounds(19, 27, 130, 26);
		pnAgregar.add(txtDPI);
		txtDPI.setColumns(10);
		
		txtNombres = new JTextField();
		txtNombres.setText("Nombres");
		txtNombres.setColumns(10);
		txtNombres.setBounds(19, 69, 130, 26);
		pnAgregar.add(txtNombres);
		
		txtApellidos = new JTextField();
		txtApellidos.setText("Apellidos");
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(19, 117, 130, 26);
		pnAgregar.add(txtApellidos);
		
		JPanel pnEliminar = new JPanel();
		pnEliminar.setBorder(new TitledBorder(null, "Eliminar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnEliminar.setBounds(393, 170, 278, 175);
		add(pnEliminar);
		pnEliminar.setLayout(null);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Esto eliminaria al empleado "+Empleados[comboBox.getSelectedIndex()]);
				JOptionPane.showMessageDialog(null, "Esta funcion no existe aun.","¯\\_(ツ)_/¯", JOptionPane.WARNING_MESSAGE);
			}
		});
		btnEliminar.setBounds(95, 122, 89, 23);
		pnEliminar.add(btnEliminar);
		
		Empleados = new String[tableVerEmpleados.getModel().getRowCount()];
		for (int i=0; i< tableVerEmpleados.getModel().getRowCount(); i++){
			Empleados[i] = (String) tableVerEmpleados.getModel().getValueAt(i, 1);
		}
		
		comboBox = new JComboBox(Empleados);
		comboBox.setEditable(true);
		comboBox.setBounds(24, 33, 233, 27);
		pnEliminar.add(comboBox);
		
		JButton btnVerVentasPor = new JButton("Ver Ventas por Empleado");
		btnVerVentasPor.setBounds(459, 358, 212, 23);
		add(btnVerVentasPor);

	}
	
	private void updateData(DBinterface d) throws SQLException{
		tableVerEmpleados.setModel(d.getEmpleados());
		Empleados = new String[tableVerEmpleados.getModel().getRowCount()];
		for (int i=0; i< tableVerEmpleados.getModel().getRowCount(); i++){
			Empleados[i] = (String) tableVerEmpleados.getModel().getValueAt(i, 1);
		}
		
		comboBox.addItem(Empleados[Empleados.length-1]);
	}
}
