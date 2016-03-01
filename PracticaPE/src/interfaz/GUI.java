package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.math.plot.Plot2DPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;

public class GUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Controller _controlador;
	
	//Paneles
	JPanel _panelPrincipal, _panelOpciones, _panelGrafica;
	JPanel _panelPrecision, _panelParticipantes, _panelMutacion, _panelCruce, _panelSeleccion, _panelResultados, _panelProblemas, _panelPoblacion, _panelIteraciones, _panelSemilla;
	//Labels
	JLabel _labelPrecision, _labelParticipantes, _labelMutacion, _labelCruce, _labelMejorResultado, _labelSeleccion, _labelPoblacion, _labelN, _labelIteraciones, _labelSemilla;
	//ComboBox
	@SuppressWarnings("rawtypes")
	JComboBox _comboBoxSeleccion, _comboBoxProblemas;
	//Text Fields
	JTextField _textFieldPrecision, _textFieldParticipantes, _textFieldMutacion, _textFieldCruce, _textFieldPoblacion, _textFieldIteraciones, _textFieldN, _textFieldSemilla;
	//Botones
	JButton _botonComenzar;
	//Plot
	Plot2DPanel _plot;
	
	public GUI(Controller c) {
		_controlador = c;
		
		inicializarGUI();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void inicializarGUI()
	{
		//Principal
		_panelPrincipal = new JPanel(new BorderLayout());
		_panelOpciones = new JPanel();
		_panelGrafica = new JPanel(new BorderLayout());
		_panelPrincipal.add(_panelOpciones, BorderLayout.LINE_START);
		_panelPrincipal.add(_panelGrafica, BorderLayout.CENTER);
		
		//N
		_labelN = new JLabel("N: ");
		_textFieldN = new JTextField("1");
		_textFieldN.setPreferredSize(new Dimension(72,25));
		
		//Problema
		_panelProblemas = new JPanel();
		_panelProblemas.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		String[] problemasS = {"Problema1", "Problema2", "Problema3", "Problema4", "Problema5"};
		_panelProblemas.setLayout(new MigLayout("", "[103px][27px][78px]", "[24px]"));
		_comboBoxProblemas = new JComboBox(problemasS);
		_comboBoxProblemas.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String s = (String)_comboBoxProblemas.getSelectedItem();
				if (s=="Problema4"){
					_panelProblemas.add(_textFieldN, "cell 2 0,alignx left,aligny center");
					_panelProblemas.add(_labelN, "cell 1 0,alignx left,aligny center");
					_panelProblemas.revalidate();
					_panelProblemas.repaint();
				}else{
					_panelProblemas.remove(_textFieldN);
					_panelProblemas.remove(_labelN);
					_panelProblemas.revalidate();
					_panelProblemas.repaint();
				}
				
			}
			
		});
		_panelProblemas.add(_comboBoxProblemas, "cell 0 0,alignx left,aligny top");
		
		
		//Poblacion
		_panelPoblacion = new JPanel();
		_panelPoblacion.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		_labelPoblacion = new JLabel("Poblacion:");
		_panelPoblacion.setLayout(new MigLayout("", "[130px][78px]", "[25px]"));
		_panelPoblacion.add(_labelPoblacion, "cell 0 0,alignx left,aligny center");
		
		
		//Numero de Iteraciones
		_panelIteraciones = new JPanel();
		_panelIteraciones.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		_labelIteraciones = new JLabel("Generaciones:");
		_panelIteraciones.setLayout(new MigLayout("", "[130px][78px]", "[25px]"));
		_panelIteraciones.add(_labelIteraciones, "cell 0 0,alignx left,aligny center");
		
		//Precision
		_panelPrecision = new JPanel();
		_panelPrecision.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		_labelPrecision = new JLabel("Precision:");
		_panelPrecision.setLayout(new MigLayout("", "[130px][78px]", "[25px]"));
		_panelPrecision.add(_labelPrecision, "cell 0 0,alignx left,aligny center");
		
		//Participantes del torneo
		_labelParticipantes = new JLabel("Participantes: ");
		_textFieldParticipantes = new JTextField("2");
		_textFieldParticipantes.setPreferredSize(new Dimension(72,25));
		
		//Metodo seleccion
		_panelSeleccion = new JPanel();
		_panelSeleccion.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		_panelSeleccion.setLayout(new MigLayout("", "[130px][78px]", "[25px]"));
		_labelSeleccion = new JLabel("Metodo Seleccion:");
		_panelSeleccion.add(_labelSeleccion, "cell 0 0,alignx left,aligny center");
		String[] metodosS = {"Ruleta", "Torneo"};
		_comboBoxSeleccion = new JComboBox(metodosS);
		_comboBoxSeleccion.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String s = (String)_comboBoxSeleccion.getSelectedItem();
				if (s=="Torneo"){
					_panelSeleccion.add(_textFieldParticipantes, "cell 1 1,alignx left,aligny center");
					_panelSeleccion.add(_labelParticipantes, "cell 0 1,alignx left,aligny center");
					_panelOpciones.revalidate();
					_panelOpciones.repaint();
				}else{
					_panelSeleccion.remove(_textFieldParticipantes);
					_panelSeleccion.remove(_labelParticipantes);
					_panelOpciones.revalidate();
					_panelOpciones.repaint();
				}
				
			}
			
		});
		_panelSeleccion.add(_comboBoxSeleccion, "cell 1 0,alignx left,aligny top");
		
		
		//Cruce
		_panelCruce = new JPanel();
		_panelCruce.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		_labelCruce = new JLabel("Cruce:");
		_panelCruce.setLayout(new MigLayout("", "[130px][78px]", "[25px]"));
		_panelCruce.add(_labelCruce, "cell 0 0,alignx left,aligny center");
		
		//Mutacion
		_panelMutacion = new JPanel();
		_panelMutacion.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		_labelMutacion = new JLabel("Mutacion:");
		_panelMutacion.setLayout(new MigLayout("", "[130px][78px]", "[25px]"));
		_panelMutacion.add(_labelMutacion, "cell 0 0,alignx left,aligny center");
		
		//Semilla
		_panelSemilla = new JPanel();
		_panelSemilla.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		_labelSemilla = new JLabel("Semilla:");
		_panelSemilla.setLayout(new MigLayout("", "[130px][78px]", "[25px]"));
		_panelSemilla.add(_labelSemilla, "cell 0 0,alignx left,aligny center");
		
		//Boton
		_botonComenzar = new JButton("Comenzar");
		_botonComenzar.setBackground(UIManager.getColor("Button.darkShadow"));
		
		//Layout
		GroupLayout gl__panelOpciones = new GroupLayout(_panelOpciones);
		gl__panelOpciones.setHorizontalGroup(
			gl__panelOpciones.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl__panelOpciones.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl__panelOpciones.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl__panelOpciones.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(_panelProblemas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(_panelPoblacion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(_panelIteraciones, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
							.addComponent(_panelPrecision, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
							.addComponent(_panelSeleccion, GroupLayout.PREFERRED_SIZE, 223, Short.MAX_VALUE))
						.addComponent(_panelSemilla, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
						.addComponent(_panelMutacion, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
						.addComponent(_panelCruce, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
						.addComponent(_botonComenzar, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl__panelOpciones.setVerticalGroup(
			gl__panelOpciones.createParallelGroup(Alignment.LEADING)
				.addGroup(gl__panelOpciones.createSequentialGroup()
					.addComponent(_panelProblemas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(_panelPoblacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(_panelIteraciones, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(_panelPrecision, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(_panelSeleccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(_panelCruce, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(_panelMutacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(_panelSemilla, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(88)
					.addComponent(_botonComenzar)
					.addGap(272))
		);
		_textFieldSemilla = new JTextField("0");
		_textFieldSemilla.setPreferredSize(new Dimension(100,25));
		_panelSemilla.add(_textFieldSemilla, "cell 1 0,alignx left,aligny top");
		_textFieldMutacion = new JTextField("5");
		_textFieldMutacion.setPreferredSize(new Dimension(100, 25));
		_panelMutacion.add(_textFieldMutacion, "cell 1 0,alignx left");
		_textFieldPoblacion = new JTextField("100");
		_textFieldPoblacion.setPreferredSize(new Dimension(100,25));
		_panelPoblacion.add(_textFieldPoblacion, "cell 1 0,alignx left,aligny top");
		_textFieldIteraciones = new JTextField("100");
		_textFieldIteraciones.setPreferredSize(new Dimension(100,25));
		_panelIteraciones.add(_textFieldIteraciones, "cell 1 0,alignx left,aligny top");
		_textFieldPrecision = new JTextField("0.0001");
		_textFieldPrecision.setPreferredSize(new Dimension(100, 25));
		_panelPrecision.add(_textFieldPrecision, "cell 1 0,alignx left,aligny top");
		_textFieldCruce = new JTextField("60");
		_textFieldCruce.setPreferredSize(new Dimension(100, 25));
		_panelCruce.add(_textFieldCruce, "cell 1 0,alignx left,aligny top");
		_panelOpciones.setLayout(gl__panelOpciones);
		_botonComenzar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				_controlador.comenzarSimulacion(_textFieldPrecision.getText(), _textFieldCruce.getText(), _textFieldMutacion.getText(), (String)_comboBoxSeleccion.getSelectedItem(), (String)_comboBoxProblemas.getSelectedItem(), _textFieldMutacion.getText(), _textFieldIteraciones.getText(), _textFieldSemilla.getText(), _textFieldN.getText(), _textFieldParticipantes.getText());
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
