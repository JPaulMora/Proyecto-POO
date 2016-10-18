package resources;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class NotEditableModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TableModel m;
	public NotEditableModel(TableModel m){
		this.m = m;
	}

	@Override
	public int getRowCount() {
		return m.getRowCount();
	}

	@Override
	public int getColumnCount() {
		return m.getColumnCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return m.getValueAt(rowIndex, columnIndex);
	}
	
	@Override
    public boolean isCellEditable(int row, int column) {
       return false;
    }	
	
	public String getColumnName(int col) {
		return m.getColumnName(col);
	}
}