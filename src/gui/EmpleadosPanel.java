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

public class EmpleadosPanel extends BasePanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtDPI;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	
	/**
	 * 
	 * @param d Instancia de DBinterface
	 * @throws SQLException En caso hay problemas en la super clase.
	 */
	
	public EmpleadosPanel(DBinterface d) throws SQLException {
		super(d, new String[]{"Empleados","Ventas"}, 0);
		
		//Sub-panel que agrega empleados
		JPanel pnAgregar = new JPanel();
		pnAgregar.setBorder(new TitledBorder(null, "Agregar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnAgregar.setBounds(10, 170, 371, 175);
		add(pnAgregar);
		pnAgregar.setLayout(null);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					long b = Long.parseLong(txtDPI.getText());
					d.addEmpleado(b, txtNombres.getText(), txtApellidos.getText());
					updateData();
					
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
	}
	
	/**
	 * Este metodo actualiza los datos que se muestran 
	 * @throws SQLException En caso hay un error en DBinterface.getAsArray() en la super clase.
	 */
	protected void updateData() throws SQLException{
		//Actualizar tabla
		tableVer.setModel(d.getItems("Empleados"));
		super.updateData();
	}
	
	@Override
	void deleteItem(DBinterface d, String DPI) throws NumberFormatException, SQLException {
		d.delEmpleado(Double.parseDouble(DPI));
	}
}
