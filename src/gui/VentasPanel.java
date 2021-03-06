package gui;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import resources.DBinterface;

import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class VentasPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCarnet;
	private JTable tblProductos;
	private JTable tblCarro;
	private String[][] carro = {};
	private final String[] carroNames = {"producto","precio"};
	private double total = 0;
	private DecimalFormat dinero = new DecimalFormat("Sub-Total: Q####.##");
	JComboBox<String> comboBox;
	private DBinterface d;


	/**
	 * Panel encargado de manejar las transacciones de la aplicacion.
	 * @param d Instancia de DBinterface
	 * @throws SQLException Si no se pueden obtener los productos.
	 */

public VentasPanel(DBinterface d) throws SQLException {	   
		this.d = d;
		this.setBounds(0, 0, 725, 410);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Productos");
		lblNewLabel.setBounds(27, 53, 74, 16);
		this.add(lblNewLabel);
		
		//Tabla de Productos
		
		JScrollPane spProductos = new JScrollPane();
		spProductos.setBounds(37, 81, 643, 106);
		add(spProductos);
		
		tblProductos = new JTable(d.getItems("Productos"));
		tblProductos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
		spProductos.setViewportView(tblProductos);
		
		JLabel lblSubtotal = new JLabel("Sub-Total:");
		lblSubtotal.setBounds(255, 362, 192, 16);
		this.add(lblSubtotal);
		
		
		JButton btnAgregarCargo = new JButton("Agregar Cargo");
		btnAgregarCargo.setBounds(558, 194, 134, 29);
		
		JButton btnRemoverCargo = new JButton("Remover Cargo");
		btnRemoverCargo.setBounds(558, 321, 139, 29);
		btnRemoverCargo.setEnabled(false);
		
		btnAgregarCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carro = addRow(new String[] {(String) tblProductos.getModel().getValueAt(tblProductos.getSelectedRow(), 1), tblProductos.getModel().getValueAt(tblProductos.getSelectedRow(), 3).toString()});
				total += Double.parseDouble(tblProductos.getModel().getValueAt(tblProductos.getSelectedRow(),3).toString());
				lblSubtotal.setText(dinero.format(total));
				btnRemoverCargo.setEnabled(true);
			}
		});
		
		this.add(btnAgregarCargo);
		
		JLabel lblCarrito = new JLabel("Cuenta");
		lblCarrito.setBounds(27, 199, 58, 16);
		this.add(lblCarrito);
		
		//DefaultTableModel model = new DefaultTableModel();
		//model.setDataVector(carro, carroNames);
		
		tblCarro = new JTable();
		
		
		btnRemoverCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(tblCarro.getSelectedRow()+" "+tblCarro.getModel().getRowCount());
				carro = popRow(carro,tblCarro.getSelectedRow());
				total -= Double.parseDouble(tblCarro.getModel().getValueAt(tblCarro.getSelectedRow(),1).toString());
				lblSubtotal.setText(dinero.format(total));
				
				if (total == 0){
					btnRemoverCargo.setEnabled(false);
				}
				tblCarro.setModel(d.setNotEditable(new DefaultTableModel(carro,carroNames)));
				//tblCarro.invalidate();
				//System.out.println(tblCarro.getSelectedRow()+" "+tblCarro.getModel().getRowCount());
			}
		});
		this.add(btnRemoverCargo);
		
		txtCarnet = new JTextField();
		txtCarnet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtCarnet.setText(null);
			}
		});
		txtCarnet.setText("Carnet");
		txtCarnet.setBounds(126, 357, 109, 26);
		this.add(txtCarnet);
		txtCarnet.setColumns(10);
		
		JButton btnFacturar = new JButton("Facturar");
		btnFacturar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int carnet = 0;
				try{
					carnet = Integer.parseInt(txtCarnet.getText());
					double Balance = d.getBalance(carnet);
					if (Balance < total){
						JOptionPane.showMessageDialog(null, "Error! Saldo insuficiente.","Error!", JOptionPane.ERROR_MESSAGE);
					}else{
						d.setBalance(carnet,Balance-total);
						d.regCompra(carnet, total,Double.parseDouble(comboBox.getSelectedItem().toString().split(" ")[comboBox.getSelectedItem().toString().split(" ").length-1]));
						carro = new String[0][2];
						total = 0;
						lblSubtotal.setText(dinero.format(total));
						tblCarro.setModel(new DefaultTableModel(carro,carroNames));
						txtCarnet.setText("Carnet");
					}
					
				}catch(java.lang.NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Error! carnet invalido.");
					txtCarnet.setText("Carnet");
				}catch(SQLException e2){
					JOptionPane.showMessageDialog(null, "Error de SQL");
					e2.printStackTrace();
				}catch(java.lang.IndexOutOfBoundsException e2){
					
				}
				
			}
		});
		btnFacturar.setBounds(459, 357, 95, 29);
		this.add(btnFacturar);
		
		JLabel lblElTotal = new JLabel("");
		lblElTotal.setBounds(465, 341, 89, 16);
		add(lblElTotal);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(701, 317, -685, 12);
		add(separator);
		
		
		JScrollPane spCuentas = new JScrollPane();
		spCuentas.setBounds(37, 227, 643, 89);
		add(spCuentas);
		
		spCuentas.setViewportView(tblCarro);
		
		comboBox = new JComboBox<String>(d.getAsArray(0));
		comboBox.setBounds(6, 6, 270, 27);
		add(comboBox);
		
	   }

		private String[][] addRow(String[] r){
			String[][] a = Arrays.copyOf(carro, carro.length +1);
			a[a.length-1] = r;
			tblCarro.setModel(d.setNotEditable(new DefaultTableModel(a,carroNames)));
			return a;
		}
		
		private String[][] popRow(String[][] es,int pos){
			String[][] nes = new String[es.length-1][2];
			for(int i =0; i < es.length;i++){
				if(i < pos){
					nes[i] = es[i];
				}
				if (i > pos && nes.length >0){
					nes[i-1] = es[i];
				}
			}
			return nes;
		}
}
