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

public class EstudiantesPanel extends BasePanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtCarnet;
	private JTextField txtBalance;

	/**
	 * 
	 * @param d Instancia de DBinterface
	 * @throws SQLException En caso hay problemas en la super clase.
	 */
	
	public EstudiantesPanel(DBinterface d) throws SQLException {
		super(d,new String[]{"Estudiantes","Compras"},1);
		
		//Sub-panel que agrega estudiantes
		JPanel plAgregar = new JPanel();
		plAgregar.setBorder(new TitledBorder(null, "Agregar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		plAgregar.setBounds(10, 170, 371, 175);
		plAgregar.setLayout(null);
		add(plAgregar);
		
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int a = Integer.parseInt(txtCarnet.getText());
					double b = Double.parseDouble(txtBalance.getText());
					d.addEstudiante(a, txtNombres.getText(), txtApellidos.getText(), b);
					updateData();
					
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
		txtNombres.setText("Nombre");
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
	}

	
	protected void updateData() throws SQLException{
		//Actualizar tabla
		tableVer.setModel(d.getItems("Estudiantes"));
		super.updateData();
	}
	
	@Override
	void deleteItem(DBinterface d,String carnet) throws NumberFormatException, SQLException {
		d.delEstudiante(Integer.parseInt(carnet));
	}
}
