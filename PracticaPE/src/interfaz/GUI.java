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
import javax.swing.JCheckBox;

/**GUI del problema. Generada con ayuda de código externo, es prácticamente ilegible.
 * Fly, you fools!
 * @author pabs
 *
 */
public class GUI extends JFrame{

	private static final long serialVersionUID = 1L;

	private Controller _controlador;
	
	//Paneles
	JPanel _panelPrincipal, _panelOpciones, _panelGrafica;
	JPanel _panelPrecision, _panelParticipantes, _panelMutacion, _panelCruce, _panelSeleccion, _panelResultados, _panelProblemas, _panelPoblacion, _panelIteraciones, _panelSemilla;
	//Labels
	JLabel _labelPrecision, _realizadoEn, _time,_labelMutacion, _labelCruce, _labelMejorResultado, _labelSeleccion, _labelPoblacion, _labelN, _labelIteraciones, _labelSemilla;
	//ComboBox
	@SuppressWarnings("rawtypes")
	JComboBox _comboBoxSeleccion, _comboBoxProblemas, _tipoSeleccion;
	//Text Fields
	JTextField _textFieldPrecision, _lastSeed, _textFieldParticipantes, _textFieldMutacion, _textFieldCruce, _textFieldPoblacion, _textFieldIteraciones, _textFieldN, _textFieldSemilla;
	//Botones
	JButton _botonComenzar;
	//Plot
	Plot2DPanel _plot;
	//CheckBox
	JCheckBox _elitismo;
	
	public GUI(Controller c) {
		_controlador = c;
		
		inicializarGUI();
	}
	
	/**
	 * Inicialización de la gui.
	 */
	//HERE BE DRAGONS. ABANDON ALL HOPE YE WHO ENTERS THIS PLACE
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
		_labelN.setPreferredSize(new Dimension(10,25));
		_textFieldN = new JTextField("1");
		_textFieldN.setPreferredSize(new Dimension(70,25));
		
		//Problema
		_panelProblemas = new JPanel();
		_panelProblemas.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		String[] problemasS = {"Problema1", "Problema2", "Problema3", "Problema4","Problema4R", "Problema5"};
		String[] seleccionS = {"Externo", "DiscretoU", "Aritmetico", "SBX"};
		_panelProblemas.setLayout(new MigLayout("", "[113px][][]", "[24px]"));
		_comboBoxProblemas = new JComboBox(problemasS);
		_tipoSeleccion = new JComboBox(seleccionS);
		_tipoSeleccion.setPreferredSize(new Dimension(215,25));
		_comboBoxProblemas.setPreferredSize(new Dimension(213,25));
		_comboBoxProblemas.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String s = (String)_comboBoxProblemas.getSelectedItem();
				if (s=="Problema4"){
					_panelProblemas.add(_textFieldN, "cell 2 0,alignx left,aligny center");
					_panelProblemas.add(_labelN, "cell 1 0,alignx left,aligny center");
					_panelProblemas.revalidate();
					_panelProblemas.repaint();
					_panelSeleccion.remove(_tipoSeleccion);
					_panelSeleccion.revalidate();
					_panelSeleccion.repaint();
				}else if (s=="Problema4R"){
					_panelProblemas.add(_textFieldN, "cell 2 0,alignx left,aligny center");
					_panelProblemas.add(_labelN, "cell 1 0,alignx left,aligny center");
					_panelProblemas.revalidate();
					_panelProblemas.repaint();
					_panelSeleccion.add(_tipoSeleccion, "cell 0 2,growx");
					_panelSeleccion.revalidate();
					_panelSeleccion.repaint();
				}else
				{
					_panelProblemas.remove(_textFieldN);
					_panelProblemas.remove(_labelN);
					_panelSeleccion.remove(_tipoSeleccion);
					_panelProblemas.revalidate();
					_panelSeleccion.revalidate();
					_panelProblemas.repaint();
					_panelSeleccion.repaint();
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
		_textFieldParticipantes = new JTextField("2");
		_textFieldParticipantes.setPreferredSize(new Dimension(72,25));
		
		//Metodo seleccion
		_panelSeleccion = new JPanel();
		_panelSeleccion.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		_panelSeleccion.setLayout(new MigLayout("", "[213px,grow]", "[25px][25][25]"));
		_labelSeleccion = new JLabel("Metodo Seleccion:");
		_panelSeleccion.add(_labelSeleccion, "cell 0 0,alignx left,aligny center");
		String[] metodosS = {"Ruleta", "Ranking", "Torneo", "Torneo_Probabilistico"};
		
		
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
		
		JPanel _panelMuestraSemilla = new JPanel();
		_panelMuestraSemilla.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		_realizadoEn = new JLabel("Generado en:");
		
		_time = new JLabel("");
		
		//Layout
		GroupLayout gl__panelOpciones = new GroupLayout(_panelOpciones);
		gl__panelOpciones.setHorizontalGroup(
			gl__panelOpciones.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl__panelOpciones.createSequentialGroup()
					.addGroup(gl__panelOpciones.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl__panelOpciones.createSequentialGroup()
							.addContainerGap(21, Short.MAX_VALUE)
							.addGroup(gl__panelOpciones.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl__panelOpciones.createParallelGroup(Alignment.LEADING, false)
									.addComponent(_panelSemilla, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(_panelMutacion, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(_panelCruce, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(_panelProblemas, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(_panelPoblacion, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(_panelIteraciones, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
									.addComponent(_panelPrecision, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
									.addComponent(_panelSeleccion, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 223, Short.MAX_VALUE))
								.addComponent(_panelMuestraSemilla, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.LEADING, gl__panelOpciones.createSequentialGroup()
							.addGap(24)
							.addGroup(gl__panelOpciones.createParallelGroup(Alignment.LEADING)
								.addGroup(gl__panelOpciones.createSequentialGroup()
									.addComponent(_realizadoEn)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(_time))
								.addComponent(_botonComenzar, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))))
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(_panelMuestraSemilla, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(_botonComenzar, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl__panelOpciones.createParallelGroup(Alignment.BASELINE)
						.addComponent(_realizadoEn)
						.addComponent(_time))
					.addGap(105))
		);
		_panelMuestraSemilla.setLayout(new MigLayout("", "[213px]", "[25px][25px]"));
		JLabel lblUltimaSemilla = new JLabel("Ultima Semilla Utilizada:");
		_panelMuestraSemilla.add(lblUltimaSemilla, "cell 0 0,alignx left,aligny top");
		_lastSeed = new JTextField("");
		_lastSeed.setEditable(false);
		_lastSeed.setPreferredSize(new Dimension(213, 25));
		_panelMuestraSemilla.add(_lastSeed, "cell 0 1,alignx left,aligny top");
		_comboBoxSeleccion = new JComboBox(metodosS);
		_comboBoxSeleccion.setPreferredSize(new Dimension(215,25));
		_comboBoxSeleccion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String s = (String)_comboBoxSeleccion.getSelectedItem();
				if (s=="Torneo" || s == "Torneo_Probabilistico"){
					_panelSeleccion.add(_textFieldParticipantes, "cell 1 1,alignx left,aligny center");
					_panelOpciones.revalidate();
					_panelOpciones.repaint();
				}else{
					_panelSeleccion.remove(_textFieldParticipantes);
					_panelOpciones.revalidate();
					_panelOpciones.repaint();
				}
				
			}
			
		});
		_panelSeleccion.add(_comboBoxSeleccion, "cell 0 1,alignx left,aligny top");
		
		_elitismo = new JCheckBox("Elitismo");
		_panelSeleccion.add(_elitismo, "cell 0 2");
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
			public void actionPerformed(ActionEvent e) {
				_controlador.comenzarSimulacion(_textFieldPrecision.getText(), _textFieldCruce.getText(), _textFieldMutacion.getText(), (String)_comboBoxSeleccion.getSelectedItem(), _elitismo.isSelected(), (String)_comboBoxProblemas.getSelectedItem(), _textFieldPoblacion.getText(), _textFieldIteraciones.getText(), _textFieldSemilla.getText(), _textFieldN.getText(), _textFieldParticipantes.getText(), (String)_tipoSeleccion.getSelectedItem());
			}
		});
		
		//Grafica
		_plot = new Plot2DPanel();
		_panelGrafica.add(_plot, BorderLayout.CENTER);
		
		//Resultados
		_panelResultados = new JPanel();
		_labelMejorResultado = new JLabel();
		_panelResultados.add(_labelMejorResultado);
		
		_panelGrafica.add(_panelResultados, BorderLayout.PAGE_END);
		
		//Frame
		this.setContentPane(_panelPrincipal);
		this.setPreferredSize(new Dimension(1200, 700));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);		
	}

	/**Setter de la semilla utilizada
	 * @param semilla La semilla.
	 */
	public void setSeed(long semilla){
		_lastSeed.setText(Long.toString(semilla));
	}
	
	/**Setter del tiempo tardado
	 * @param time Tiempo tardado.
	 */
	public void setTime(float time){
		_time.setText(Float.toString(time) + " s");
	}
	
	/**Método que rellena la gráfica con la información generada.
	 * @param mejorAbsoluto El array del mejor absoluto por generación.
	 * @param mejorGeneracion El array de los mejores de cada generación.
	 * @param mediaGeneracion El array de medias de cada generación.
	 * @param numGeneraciones El número de generaciones.
	 * @param resultado El resultado obtenido.
	 */
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
		_plot.addLinePlot("Mejor Generacion", x, mejorGeneracion);
		_plot.addLinePlot("Media Generacion", x, mediaGeneracion);
		
		_labelMejorResultado.setText(resultado);
	}
}
