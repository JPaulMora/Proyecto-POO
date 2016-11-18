package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import resources.DBinterface;
import resources.ItemsPor;

abstract class BasePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JTable tableVer;
	
	protected String[] Items;
	protected String[] UINames = new String[2];
	private JComboBox<String> comboBox;
	protected DBinterface d;
	protected int mode;
	
	/**
	 * Este panel sirve de plantilla para desplegar los datos que se requieren de forma ordenada. Este panel no deberia ser instanciado, solo extendido.
	 * @param d Instancia de DBinterface.
	 * @param UINames Array de Strings que le dan contexto al panel.
	 * @param mode puede ser 0,1 o 2 segun se necesite Empleados, Estudiantes o Productos respectivamente.
	 * @throws SQLException  En caso hubo un error en DBinterface.getItems().
	 */
	public BasePanel(DBinterface d, String[] UINames,int mode) throws SQLException {
		this.d = d;
		this.mode = mode;
		setLayout(null);
		
		setBorder(new TitledBorder(null, "Ver "+UINames[0], TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setBounds(10, 11, 688, 403);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 22, 667, 136);
		add(scrollPane);
		
		tableVer = new JTable(d.getItems(UINames[0]));
		scrollPane.setViewportView(tableVer);

		
		JPanel plEliminar = new JPanel();
		plEliminar.setBorder(new TitledBorder(null, "Eliminar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		plEliminar.setBounds(393, 170, 288, 175);
		add(plEliminar);
		plEliminar.setLayout(null);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deleteItem(d,Items[comboBox.getSelectedIndex()].split(" ")[Items[comboBox.getSelectedIndex()].split(" ").length-1]);
					updateData();
					
					plEliminar.repaint();
				} catch (NumberFormatException | SQLException e1) {
					
					e1.printStackTrace();
				}
				//JOptionPane.showMessageDialog(null, "Esta funcion no existe aun.","¯\\_(ツ)_/¯", JOptionPane.WARNING_MESSAGE);
			}
		});
		btnEliminar.setBounds(95, 122, 89, 23);
		plEliminar.add(btnEliminar);
		
		//Inicializar array con los nombres de los estuidiantes.
		
		Items = d.getAsArray(this.mode);//itemsAsList(d);
		
		//Una vez inicializado el array podemos creal el JComboBox, de lo contrario nos da NullPointerException.
		comboBox = new JComboBox<String>(Items);
		comboBox.setEditable(true);
		comboBox.setBounds(24, 34, 233, 27);
		plEliminar.add(comboBox);
		if(mode <2){
		JButton btnVerComprasPor = new JButton("Ver "+UINames[1]+" por "+UINames[0]);
		btnVerComprasPor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame Items;
				try {
					Items = new ItemsPor(d,mode);
					Items.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnVerComprasPor.setBounds(459, 357, 211, 23);
		add(btnVerComprasPor);
		}

	}
	
	/**
	 * Este metodo actualiza los datos que se muestran cada vez que se agrega o elimina uno.
	 * @throws SQLException En caso hay un error en DBinterface.getAsArray()
	 */
	protected void updateData() throws SQLException{
		//Actualizar array de items dentro del comboBox del menu eliminar
		Items =  d.getAsArray(this.mode);
		//Actualizar el ComboBox
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(Items);
		comboBox.setModel(model);
	}
	
	/**
	 * 
	 * @param d Instancia de DBinterface.
	 * @param item Nombre o palabra con la que se identifica el objeto a eliminar.
	 * @throws NumberFormatException En las clases hijas es necesario convertir "item" a int o double, error en la subclase se refleja aqui.
	 * @throws SQLException En caso no se puede eliminar el item.
	 */
	abstract void deleteItem(DBinterface d,String item) throws NumberFormatException, SQLException;
}
