package resources;
import org.jfree.chart.*; // grafico de lineas
import org.jfree.data.xy.*;
import org.jfree.chart.plot.PlotOrientation;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

public class Chart extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage grafica = null;
	private DBinterface d;
	private int mode;
	private ItemsPor i;
	private TableModel data;

	public Chart(DBinterface d,ItemsPor i,int mode) {
		setBounds(0,0,530, 310);
		setLayout(null);
		this.i = i;
		this.mode = mode;
		this.d = d;
		}
	
	public BufferedImage creaImagen() throws NumberFormatException, SQLException
    {
		String cname;
		if (mode == 1){
			cname = "Compras";
			data = d.getComprasPorCliente(Integer.parseInt(i.getItemFromBox().split(" ")[i.getItemFromBox().split(" ").length-1]));
		}else{
			cname = "Ventas";
			data = d.getVentasPorEmp(Double.parseDouble(i.getItemFromBox().split(" ")[i.getItemFromBox().split(" ").length-1]));
		}
        XYSeries series = new XYSeries("Data");
       for (int i=0; i < data.getRowCount(); i++){
    	   series.add(Double.parseDouble(data.getValueAt(i, 0).toString()),Double.parseDouble(data.getValueAt(i, 1).toString()));
        }
        
        XYDataset Datos= new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart (i.getItemFromBox(),"Transaccion",cname,Datos,PlotOrientation.VERTICAL,false,false,true);
        BufferedImage image = chart.createBufferedImage(510, 250);
        return image;
        }
	
	public void paint(java.awt.Graphics g) {
        try {
			grafica = this.creaImagen();
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
        g.drawImage(grafica,0,0,null);
    }	
}