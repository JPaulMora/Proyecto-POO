package resources;

import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class ItemsPor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboBox;
	private TablePane tp;
	private Chart ch;
	
	/**
	 * Esta clase sirve para mostrar los datos del boton "Ventas por Empleado" y "Compras por Estudiante"
	 * @throws SQLException 
	 * 
	 */
	
	public ItemsPor(DBinterface d, int mode) throws SQLException {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		comboBox = new JComboBox(d.getAsArray(mode));
		comboBox.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				try {
					update();
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		
		
		comboBox.setBounds(150, 6, 225, 28);
		contentPane.add(comboBox);
		tp = new TablePane(d,this,mode);
		ch = new Chart(d,this,mode);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Tabla", tp);
		tabbedPane.addTab("Grafica", ch);
		tabbedPane.setBounds(5, 33, 529, 290);
		contentPane.add(tabbedPane);
	}
	
	public String getItemFromBox(){
		return comboBox.getSelectedItem().toString();
	}
	
	private void update() throws NumberFormatException, SQLException{
		tp.update();
		ch.repaint();
		this.repaint();
	}
}
