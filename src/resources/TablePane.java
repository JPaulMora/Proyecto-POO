package resources;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class TablePane extends JPanel{
	private JTable table;
	
	public TablePane(DBinterface d,ItemsPor i) {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 518, 298);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
