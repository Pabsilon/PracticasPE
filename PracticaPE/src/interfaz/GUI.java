package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.math.plot.Plot2DPanel;

public class GUI extends JFrame{
	
	private Controller _controlador;
	
	//Paneles
	JPanel _panelPrincipal, _panelOpciones, _panelGrafica;
	JPanel _panelPrecision, _panelMutacion, _panelCruce, _panelSeleccion, _panelResultados, _panelProblemas, _panelPoblacion, _panelIteraciones;
	//Labels
	JLabel _labelPrecision, _labelMutacion, _labelCruce, _labelMejorResultado, _labelSeleccion, _labelPoblacion, _labelIteraciones;
	//ComboBox
	JComboBox _comboBoxSeleccion, _comboBoxProblemas;
	//Text Fields
	JTextField _textFieldPrecision, _textFieldMutacion, _textFieldCruce, _textFieldPoblacion, _textFieldIteraciones;
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
		
		//Problema
		_panelProblemas = new JPanel();
		String[] problemasS = {"Problema1", "Problema2", "Problema3", "Problema4", "Problema5"};
		_comboBoxProblemas = new JComboBox<String>(problemasS);
		_panelProblemas.add(_comboBoxProblemas);
		_panelOpciones.add(_panelProblemas);
		
		
		//Poblacion
		_panelPoblacion = new JPanel();
		_labelPoblacion = new JLabel("Poblacion:");
		_textFieldPoblacion = new JTextField("20");
		_textFieldPoblacion.setPreferredSize(new Dimension(100,25));
		_panelPoblacion.add(_labelPoblacion);
		_panelPoblacion.add(_textFieldPoblacion);
		_panelOpciones.add(_panelPoblacion);
		
		
		//Numero de Iteraciones
		_panelIteraciones = new JPanel();
		_labelIteraciones = new JLabel("Iteraciones:");
		_textFieldIteraciones = new JTextField("1");
		_textFieldIteraciones.setPreferredSize(new Dimension(100,25));
		_panelIteraciones.add(_labelIteraciones);
		_panelIteraciones.add(_textFieldIteraciones);
		_panelOpciones.add(_panelIteraciones);
		
		//Precision
		_panelPrecision = new JPanel();
		_labelPrecision = new JLabel("Precision:");
		_textFieldPrecision = new JTextField("0.0001");
		_textFieldPrecision.setPreferredSize(new Dimension(100, 25));
		_panelPrecision.add(_labelPrecision);
		_panelPrecision.add(_textFieldPrecision);
		_panelOpciones.add(_panelPrecision);
		
		
		//Metodo seleccion
		_panelSeleccion = new JPanel();
		_labelSeleccion = new JLabel("Metodo Seleccion:");
		_panelSeleccion.add(_labelSeleccion);
		String[] metodosS = {"Ruleta", "Torneo"};
		_comboBoxSeleccion = new JComboBox<String>(metodosS);
		_panelSeleccion.add(_comboBoxSeleccion);
		_panelOpciones.add(_panelSeleccion);
		
		
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
				_controlador.comenzarSimulacion(_textFieldPrecision.getText(), _textFieldCruce.getText(), _textFieldMutacion.getText(), (String)_comboBoxSeleccion.getSelectedItem(), (String)_comboBoxProblemas.getSelectedItem(), _textFieldMutacion.getText(), _textFieldIteraciones.getText());
				
			}
		});
		
		//Gr�fica
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
		//A�adir las lineas
		_plot.addLinePlot("Mejor Absoluto", x, mejorAbsoluto);
		_plot.addLinePlot("Mejor Generaci�n", x, mejorGeneracion);
		_plot.addLinePlot("Media Generaci�n", x, mediaGeneracion);
		
		_labelMejorResultado.setText(resultado);
	}

}
