package resources;

import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class ItemsPor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private TablePane tp;
	private Chart ch;
	
	/**
	 * Esta clase sirve para mostrar los datos del boton "Ventas por Empleado" y "Compras por Estudiante" maneja internamente dos paneles con esta informacion.
	 * @param d Instancia de DBinterface.
	 * @param mode puede ser 0 o 1, dependiendo si se necesita Empleados o Estudiantes respectivamente.
	 * @throws SQLException En caso hubo un error DBinterface.getAsArray().
	 */
	
	public ItemsPor(DBinterface d, int mode) throws SQLException {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		comboBox = new JComboBox<String>(d.getAsArray(mode));
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
	/**
	 * 
	 * @return Devuelve el objeto seleccionado en un String.
	 */
	public String getItemFromBox(){
		return comboBox.getSelectedItem().toString();
	}
	/**
	 * Metodo se utiliza para recrear el view cada vez que se escoge un nuevo estudiante.
	 * @throws NumberFormatException Utilizado por TablePane.update().
	 * @throws SQLException En caso TablePane.update() no se conecta correctamente a DB.
	 */
	private void update() throws NumberFormatException, SQLException{
		tp.update();
		ch.repaint();
		this.repaint();
	}
}
