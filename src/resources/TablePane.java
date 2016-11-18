package resources;

import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class TablePane extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private ItemsPor i;
	private DBinterface d;
	private int mode;
	/**
	 * 
	 * @param d Instancia de DBinterface 
	 * @param i Instancia de ItemsPor, que maneja este panel.
	 * @param mode 1 obtiene Compras por estudiante, 2 obtiene ventas por empleado.
	 * @throws NumberFormatException Si las compras o ventas de getComprasPorCliente o getVentasPorEmp no son numericas.
	 * @throws SQLException Si los metodos anteriores no sirven.
	 */
	public TablePane(DBinterface d,ItemsPor i,int mode) throws NumberFormatException, SQLException {
		this.d = d;
		this.i = i;
		this.mode = mode;
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 518, 298);
		add(scrollPane);
		
		if (mode == 1){
			table = new JTable(d.getComprasPorCliente(Integer.parseInt(i.getItemFromBox().split(" ")[i.getItemFromBox().split(" ").length-1])));
		}else{
			table = new JTable(d.getVentasPorEmp(Double.parseDouble(i.getItemFromBox().split(" ")[i.getItemFromBox().split(" ").length-1])));
		}
		scrollPane.setViewportView(table);
	}
	
	public void update() throws NumberFormatException, SQLException{
		if (mode == 1){
			table.setModel(d.getComprasPorCliente(Integer.parseInt(i.getItemFromBox().split(" ")[i.getItemFromBox().split(" ").length-1])));
		}else{
			table.setModel(d.getVentasPorEmp(Double.parseDouble(i.getItemFromBox().split(" ")[i.getItemFromBox().split(" ").length-1])));
		}
		this.repaint();
	}
}
