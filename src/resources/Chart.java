package resources;
import org.jfree.chart.*; // grafico de lineas
import org.jfree.data.xy.*;
import org.jfree.chart.plot.PlotOrientation;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Chart extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage grafica = null;

	public BufferedImage creaImagen()
    {
        XYSeries series = new XYSeries("Data");
        series.add(1, 4);
        series.add(2, 3);
        series.add(3, 2);
        series.add(4, 3);
        series.add(5, 5);
        series.add(6, 2);
        series.add(7, 8);
        XYDataset Datos= new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart ("Compras de Estudiantes","Estudiantes","Compras",Datos,PlotOrientation.VERTICAL,false,false,true);
        BufferedImage image = chart.createBufferedImage(510, 250);
        return image;
        }
	
	public void paint(java.awt.Graphics g) {
        if(grafica == null){
        	grafica = this.creaImagen();
        }
        g.drawImage(grafica,0,0,null);
    }
	
	
	public Chart(DBinterface d,ItemsPor i) {
		setBounds(0,0,530, 310);
		setLayout(null);
//		XYSeries series = new XYSeries("titulo de la serie");
//        series.add(1, 23);
//        series.add(2, 34);
//        series.add(3, 51);
//        series.add(4, 67);
//        series.add(5, 89);
//        series.add(6, 121);
//        series.add(7, 137);
		}
}