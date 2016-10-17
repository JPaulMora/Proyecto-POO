package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import resources.DBinterface;
import resources.ItemsPor;

import javax.swing.border.TitledBorder;
import javax.swing.ScrollPaneConstants;
import java.awt.SystemColor;
import java.sql.SQLException;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EstudiantesPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableVerEstudiante;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtCarnet;
	private JTextField txtBalance;
	private String[] Estudiantes;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public EstudiantesPanel(DBinterface d) throws SQLException {
		setLayout(null);
		
		setBorder(new TitledBorder(null, "Ver Estudiante", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setBounds(10, 11, 688, 403);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 22, 660, 136);
		add(scrollPane);
		
		tableVerEstudiante = new JTable(d.getEstudiantes());
		scrollPane.setViewportView(tableVerEstudiante);
		
		JPanel plAgregar = new JPanel();
		plAgregar.setBorder(new TitledBorder(null, "Agregar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		plAgregar.setBounds(10, 170, 371, 175);
		add(plAgregar);
		plAgregar.setLayout(null);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int a = Integer.parseInt(txtCarnet.getText());
					double b = Double.parseDouble(txtBalance.getText());
					d.addEstudiante(a, txtNombres.getText(), txtApellidos.getText(), b);
					updateData(d);
					
				}
				catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Carnet solo debe contener numeros.","Error!", JOptionPane.ERROR_MESSAGE);
				}
				catch(SQLException e2){
					System.out.println("SQLException: ");
					JOptionPane.showMessageDialog(null, "Ver Consola.","Error SQL", JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
				}
			}
		});
		btnAgregar.setBounds(233, 120, 89, 23);
		plAgregar.add(btnAgregar);
		
		txtNombres = new JTextField();
		txtNombres.setText("Nombres");
		txtNombres.setBounds(19, 69, 130, 26);
		plAgregar.add(txtNombres);
		txtNombres.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setText("Apellidos");
		txtApellidos.setBounds(19, 117, 130, 26);
		plAgregar.add(txtApellidos);
		txtApellidos.setColumns(10);
		
		txtCarnet = new JTextField();
		txtCarnet.setText("Carnet");
		txtCarnet.setBounds(19, 27, 130, 26);
		plAgregar.add(txtCarnet);
		txtCarnet.setColumns(10);
		
		txtBalance = new JTextField();
		txtBalance.setText("Balance");
		txtBalance.setBounds(222, 27, 130, 26);
		plAgregar.add(txtBalance);
		txtBalance.setColumns(10);
		
		JPanel plEliminar = new JPanel();
		plEliminar.setBorder(new TitledBorder(null, "Eliminar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		plEliminar.setBounds(393, 170, 278, 175);
		add(plEliminar);
		plEliminar.setLayout(null);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Esto eliminaria al usuario "+Estudiantes[comboBox.getSelectedIndex()]);
				JOptionPane.showMessageDialog(null, "Esta funcion no existe aun.","¯\\_(ツ)_/¯", JOptionPane.WARNING_MESSAGE);
			}
		});
		btnEliminar.setBounds(95, 122, 89, 23);
		plEliminar.add(btnEliminar);
		
		Estudiantes = new String[tableVerEstudiante.getModel().getRowCount()];
		for (int i=0; i< tableVerEstudiante.getModel().getRowCount(); i++){
			Estudiantes[i] = (String) tableVerEstudiante.getModel().getValueAt(i, 3);
		}
		
		comboBox = new JComboBox(Estudiantes);
		comboBox.setEditable(true);
		comboBox.setBounds(24, 34, 233, 27);
		plEliminar.add(comboBox);
		
		JButton btnVerComprasPor = new JButton("Ver Compras por Estudiante");
		btnVerComprasPor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame Items;
				try {
					Items = new ItemsPor(d,1);
					Items.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnVerComprasPor.setBounds(459, 357, 211, 23);
		add(btnVerComprasPor);

	}
	
	private void updateData(DBinterface d) throws SQLException{
		tableVerEstudiante.setModel(d.getEstudiantes());
		Estudiantes = new String[tableVerEstudiante.getModel().getRowCount()];
		for (int i=0; i< tableVerEstudiante.getModel().getRowCount(); i++){
			Estudiantes[i] = (String) tableVerEstudiante.getModel().getValueAt(i, 3);
		}
		
		comboBox.addItem(Estudiantes[Estudiantes.length-1]);
	}
}
