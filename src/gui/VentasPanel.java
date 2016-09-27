package gui;

import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import resources.DBinterface;

import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class VentasPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCarnet;
	private JTable tblProductos;
	private JTable table;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */

public VentasPanel(DBinterface d) throws SQLException {	   
		this.setBounds(0, 0, 725, 410);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Productos");
		lblNewLabel.setBounds(27, 35, 74, 16);
		this.add(lblNewLabel);
		
		JButton btnAgregarCargo = new JButton("Agregar Cargo");
		btnAgregarCargo.setBounds(558, 179, 134, 29);
		this.add(btnAgregarCargo);
		
		JLabel lblCarrito = new JLabel("Cuenta");
		lblCarrito.setBounds(27, 192, 58, 16);
		this.add(lblCarrito);
		
		JButton btnNewButton = new JButton("Remover Cargo");
		btnNewButton.setBounds(558, 321, 139, 29);
		this.add(btnNewButton);
		
		txtCarnet = new JTextField();
		txtCarnet.setText("Carnet");
		txtCarnet.setBounds(126, 357, 109, 26);
		this.add(txtCarnet);
		txtCarnet.setColumns(10);
		
		JLabel lblSubtotal = new JLabel("Sub-Total:");
		lblSubtotal.setBounds(255, 362, 84, 16);
		this.add(lblSubtotal);
		
		JButton btnFacturar = new JButton("Facturar");
		btnFacturar.setBounds(459, 357, 95, 29);
		this.add(btnFacturar);
		
		JLabel lblElTotal = new JLabel("");
		lblElTotal.setBounds(465, 341, 89, 16);
		add(lblElTotal);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(701, 317, -685, 12);
		add(separator);
		
		JScrollPane spProductos = new JScrollPane();
		spProductos.setBounds(37, 63, 655, 106);
		add(spProductos);
		
		tblProductos = new JTable(d.getProductos());
		spProductos.setViewportView(tblProductos);
		
		
		JScrollPane spCuentas = new JScrollPane();
		spCuentas.setBounds(37, 220, 655, 96);
		add(spCuentas);
		
		table = new JTable();
		spCuentas.setViewportView(table);
	   }
}
