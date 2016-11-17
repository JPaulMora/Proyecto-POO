package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import resources.DBinterface;

public class ProductosPanel extends BasePanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfProducto;
	private JTextField tfDescripcion;
	private JTextField tfPrecio;
	
	/**
	 * 
	 * @param d Instancia de DBinterface
	 * @throws SQLException Cuando no hay errores en la super clase.
	 */
	public ProductosPanel(DBinterface d) throws SQLException {
		super(d, new String[]{"Productos"}, 2);
		
		//Sub-panel que agrega productos
		JPanel pAgregar = new JPanel();
		pAgregar.setBorder(new TitledBorder(null, "Agregar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pAgregar.setBounds(10, 170, 371, 175);
		pAgregar.setLayout(null);
		add(pAgregar);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					double b = Double.parseDouble(tfPrecio.getText());
					d.addProducto(tfProducto.getText(),tfDescripcion.getText(), b);
					updateData();
					
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
		
		btnNewButton.setBounds(197, 112, 89, 23);
		pAgregar.add(btnNewButton);
		
		tfProducto = new JTextField();
		tfProducto.setText("Producto");
		tfProducto.setBounds(20, 29, 297, 20);
		pAgregar.add(tfProducto);
		tfProducto.setColumns(10);
		
		tfDescripcion = new JTextField();
		tfDescripcion.setText("Descripcion");
		tfDescripcion.setBounds(20, 70, 297, 20);
		pAgregar.add(tfDescripcion);
		tfDescripcion.setColumns(10);
		
		tfPrecio = new JTextField();
		tfPrecio.setText("Precio");
		tfPrecio.setBounds(20, 113, 108, 20);
		pAgregar.add(tfPrecio);
		tfPrecio.setColumns(10);
	}
	
	/**
	 * Este metodo actualiza los datos que se muestran 
	 * @throws SQLException En caso hay un error en DBinterface.getAsArray() en la super clase.
	 */
	protected void updateData() throws SQLException{
		//Actualizar tabla
		tableVer.setModel(d.getItems("Productos"));
		super.updateData();
	}
	
	@Override
	void deleteItem(DBinterface d, String item) throws NumberFormatException, SQLException {
		d.delProducto(item);
	}
}
