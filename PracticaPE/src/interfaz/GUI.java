package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.math.plot.Plot2DPanel;

public class GUI extends JFrame{
	
	private Controller _controlador;
	
	//Paneles
	JPanel _panelPrincipal, _panelOpciones, _panelGrafica;
	JPanel _panelPrecision, _panelMutacion, _panelCruce, _panelResultados;
	//Labels
	JLabel _labelPrecision, _labelMutacion, _labelCruce, _labelMejorResultado;
	//Text Fields
	JTextField _textFieldPrecision, _textFieldMutacion, _textFieldCruce;
	//Botones
	JButton _botonComenzar;
	//Plot
	Plot2DPanel _plot;
	
	public GUI(Controller c) {
		_controlador = c;
		
		inicializarGUI();
	}
	
	private void inicializarGUI()
	{
		//Principal
		_panelPrincipal = new JPanel(new BorderLayout());
		_panelOpciones = new JPanel();
		_panelOpciones.setLayout(new BoxLayout(_panelOpciones, BoxLayout.PAGE_AXIS));
		_panelGrafica = new JPanel(new BorderLayout());
		_panelPrincipal.add(_panelOpciones, BorderLayout.LINE_START);
		_panelPrincipal.add(_panelGrafica, BorderLayout.CENTER);
		
		//Precision
		_panelPrecision = new JPanel();
		_labelPrecision = new JLabel("Precision:");
		_textFieldPrecision = new JTextField("0.0001");
		_textFieldPrecision.setPreferredSize(new Dimension(100, 25));
		_panelPrecision.add(_labelPrecision);
		_panelPrecision.add(_textFieldPrecision);
		_panelOpciones.add(_panelPrecision);
		
		//Cruce
		_panelCruce = new JPanel();
		_labelCruce = new JLabel("Cruce:");
		_textFieldCruce = new JTextField("60");
		_textFieldCruce.setPreferredSize(new Dimension(100, 25));
		_panelCruce.add(_labelCruce);
		_panelCruce.add(_textFieldCruce);
		_panelOpciones.add(_panelCruce);
		
		//Mutacion
		_panelMutacion = new JPanel();
		_labelMutacion = new JLabel("Mutacion:");
		_textFieldMutacion = new JTextField("5");
		_textFieldMutacion.setPreferredSize(new Dimension(100, 25));
		_panelMutacion.add(_labelMutacion);
		_panelMutacion.add(_textFieldMutacion);
		_panelOpciones.add(_panelMutacion);
		
		//Boton
		_botonComenzar = new JButton("Comenzar");
		_panelOpciones.add(_botonComenzar);
		_botonComenzar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				_controlador.comenzarSimulacion(_textFieldPrecision.getText(), _textFieldCruce.getText(), _textFieldMutacion.getText());
				
			}
		});
		
		//Gráfica
		_plot = new Plot2DPanel();
		_panelGrafica.add(_plot, BorderLayout.CENTER);
		
		//Resultados
		_panelResultados = new JPanel();
		_labelMejorResultado = new JLabel();
		_panelResultados.add(_labelMejorResultado);
		
		_panelGrafica.add(_panelResultados, BorderLayout.PAGE_END);
		
		//Frame
		this.setContentPane(_panelPrincipal);
		this.setPreferredSize(new Dimension(750, 800));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);		
	}

	public void fillPlot(double[] mejorAbsoluto, double[] mejorGeneracion, double[] mediaGeneracion, int numGeneraciones, String resultado)
	{
		_plot.removeAllPlots();
		
		//Posicion de la leyenda
		_plot.addLegend("SOUTH");
		
		double[] x = new double[numGeneraciones];
		for(int i = 0; i < numGeneraciones; i++)
		{
			x[i] = i + 1;
		}
		//Añadir las lineas
		_plot.addLinePlot("Mejor Absoluto", x, mejorAbsoluto);
		_plot.addLinePlot("Mejor Generación", x, mejorGeneracion);
		_plot.addLinePlot("Media Generación", x, mediaGeneracion);
		
		_labelMejorResultado.setText(resultado);
	}

}
