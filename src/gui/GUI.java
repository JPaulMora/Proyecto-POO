package gui;
import org.jfree.chart.*; // grafico de lineas
import org.jfree.data.xy.*;
import org.jfree.chart.plot.PlotOrientation;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {
	BufferedImage grafica = null;
	private JPanel contentPane;
	
	public static void main(String[] args) {
		GUI miventana = new GUI();
        miventana.setSize(450,450);
        //miventana.show();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BufferedImage creaImagen()
    {
        XYSeries series = new XYSeries("Titulo");
        series.add(1, 4);
        series.add(2, 3);
        series.add(3, 2);
        series.add(4, 3);
        series.add(5, 5);
        series.add(6, 2);
        series.add(7, 8);
        XYDataset Datos= new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart 
        ("Compras de Estudiantes",
        "Estudiantes",
        "Compras",
        Datos,
        PlotOrientation.VERTICAL,
        false,
        false,
        true                // Show legend
        );
        BufferedImage image = chart.createBufferedImage(400,400);
        return image;}
	public void paint(java.awt.Graphics g) {
        if(grafica == null)
        {
        grafica = this.creaImagen();
        }
        g.drawImage(grafica,30,30,null);}
	
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 401);
		contentPane.add(panel);
		XYSeries series = new XYSeries("titulo de la serie");
        series.add(1, 23);
        series.add(2, 34);
        series.add(3, 51);
        series.add(4, 67);
        series.add(5, 89);
        series.add(6, 121);
        series.add(7, 137);
		}
}