package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentasPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Create the panel.
	 */

public VentasPanel() {	   
		this.setBounds(0, 0, 725, 24);
		JLabel lblRestaurante = new JLabel("Restaurante 1:");
		this.add(lblRestaurante);
		
		JLabel lblNewLabel = new JLabel("ID Producto");
		lblNewLabel.setBounds(45, 35, 85, 14);
		this.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(21, 60, 137, 179);
		this.add(textField);
		textField.setColumns(10);
		
		JLabel lblNombreProducto = new JLabel("Nombre Producto");
		lblNombreProducto.setBounds(302, 35, 115, 14);
		this.add(lblNombreProducto);
		
		JLabel lblPrecioProduco = new JLabel("Precio Producto");
		lblPrecioProduco.setBounds(561, 35, 99, 14);
		this.add(lblPrecioProduco);
		
		textField_1 = new JTextField();
		textField_1.setBounds(208, 60, 277, 179);
		this.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(543, 60, 137, 179);
		this.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblIngreseIdDe = new JLabel("Ingrese ID de Producto");
		lblIngreseIdDe.setBounds(31, 260, 129, 14);
		this.add(lblIngreseIdDe);
		
		textField_3 = new JTextField();
		textField_3.setBounds(208, 257, 86, 20);
		this.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnAgregarCargo = new JButton("Agregar Cargo");
		btnAgregarCargo.setBounds(326, 256, 119, 23);
		this.add(btnAgregarCargo);
		
		JLabel lblIngreseIdQue = new JLabel("Ingrese ID que desea Remover");
		lblIngreseIdQue.setBounds(30, 296, 162, 14);
		this.add(lblIngreseIdQue);
		
		textField_4 = new JTextField();
		textField_4.setBounds(208, 293, 86, 20);
		this.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("Remover Cargo");
		btnNewButton.setBounds(326, 290, 119, 23);
		this.add(btnNewButton);
		
		textField_5 = new JTextField();
		textField_5.setBounds(475, 262, 205, 147);
		this.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblIngrese = new JLabel("Ingrese el Carnet del Usuario");
		lblIngrese.setBounds(31, 338, 161, 14);
		this.add(lblIngrese);
		
		textField_6 = new JTextField();
		textField_6.setBounds(208, 335, 86, 20);
		this.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblSubtotal = new JLabel("Sub-Total");
		lblSubtotal.setBounds(31, 373, 46, 14);
		this.add(lblSubtotal);
		
		textField_7 = new JTextField();
		textField_7.setBounds(208, 370, 86, 20);
		this.add(textField_7);
		textField_7.setColumns(10);
		
		JButton btnFacturar = new JButton("Facturar");
		btnFacturar.setBounds(328, 369, 117, 23);
		this.add(btnFacturar);
	   }

}
