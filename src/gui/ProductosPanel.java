package gui;

import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JPanel;

import resources.DBinterface;
import resources.ItemsPor;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.sql.SQLException;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductosPanel extends JPanel {
	private JTable table;
	private JTextField tfProducto;
	private JTextField tfDescripcion;
	private JTextField tfPrecio;
	private String[] Productos;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	public ProductosPanel(DBinterface d) throws SQLException{
		this.setBounds(285, 83, 699, 418);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(20, 28, 656, 136);
		add(scrollPane);
		
		table = new JTable(d.getProductos());
		/**
		table.setModel(new DefaultTableModel( // revisar ******************************
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"id", "producto", "descripcion", "precio"
			}
		));                                 // hasta aqui *******************************/
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Ver Productos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 679, 161);
		add(panel);
		panel.setLayout(null);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					double b = Double.parseDouble(tfPrecio.getText());
					d.addProducto(tfProducto.getText(), tfDescripcion.getText(), b);
					updateData(d);
					
				}
				catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Precio solo debe contener numeros.","Error!", JOptionPane.ERROR_MESSAGE);
				}
				catch(SQLException e2){
					System.out.println("SQLException: ");
					JOptionPane.showMessageDialog(null, "Ver Consola.","Error SQL", JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
				}
			}
		});
		btnAgregar.setBounds(233, 120, 89, 23);
		panel.add(btnAgregar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Agregar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 175, 338, 169);
		add(panel_1);
		panel_1.setLayout(null);
		
		tfProducto = new JTextField();
		tfProducto.setBounds(20, 29, 108, 20);
		panel_1.add(tfProducto);
		tfProducto.setColumns(10);
		
		tfDescripcion = new JTextField();
		tfDescripcion.setBounds(20, 70, 108, 20);
		panel_1.add(tfDescripcion);
		tfDescripcion.setColumns(10);
		
		tfPrecio = new JTextField();
		tfPrecio.setBounds(20, 113, 108, 20);
		panel_1.add(tfPrecio);
		tfPrecio.setColumns(10);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.setBounds(197, 112, 89, 23);
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Eliminar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(358, 175, 331, 169);
		add(panel_2);
		panel_2.setLayout(null);
		
		JComboBox comboBoxProductos = new JComboBox();
		comboBoxProductos.setBounds(62, 38, 220, 20);
		panel_2.add(comboBoxProductos);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Esto eliminaria al usuario "+Productos[comboBox.getSelectedIndex()]);
				JOptionPane.showMessageDialog(null, "Esta funcion no existe aun.","¯\\_(ツ)_/¯", JOptionPane.WARNING_MESSAGE);
			}
		});
		btnEliminar.setBounds(118, 109, 89, 23);
		panel_2.add(btnEliminar);
		/**
		Productos = new String[table.getModel().getRowCount()];
		for (int i=0; i< table.getModel().getRowCount(); i++){
			Productos[i] = (String) table.getModel().getValueAt(i, 3);
		}*/
		JButton btnNewButton_1 = new JButton("Unidades Vendidas por Producto");
		btnNewButton_1.setBounds(418, 361, 216, 23);
		add(btnNewButton_1);
	   }
	
	private void updateData(DBinterface d) throws SQLException{
		table.setModel(d.getEstudiantes());
		Productos = new String[table.getModel().getRowCount()];
		for (int i=0; i< table.getModel().getRowCount(); i++){
			Productos[i] = (String) table.getModel().getValueAt(i, 3);
		}
		
		comboBox.addItem(Productos[Productos.length-1]);
	}
}
