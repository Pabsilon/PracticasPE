package gui;

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

	private Controller _controller;
	
	//Paneles
	JPanel _panelPrincipal, _panelOpciones, _panelGrafica;
	JPanel _panelParticipantes, _panelMutacion, _panelCruce, _panelSeleccion, _panelResultados, _panelPoblacion, _panelIteraciones, _panelSemilla;
	//Labels
	JLabel _realizadoEn, _time,_labelMutacion, _labelCruce, _labelMejorResultado, _labelSeleccion, _labelPoblacion, _labelN, _labelIteraciones, _labelSemilla;
	//ComboBox
	@SuppressWarnings("rawtypes")
	JComboBox _comboBoxSeleccion, _tipoSeleccion ,_comboBoxMutacion, _comboBoxCruce;
	//Text Fields
	JTextField _lastSeed, _textFieldParticipantes, _textFieldMutacion, _textFieldCruce, _textFieldPoblacion, _textFieldIteraciones, _textFieldN, _textFieldSemilla;
	//Botones
	JButton _botonComenzar;
	//Plot
	Plot2DPanel _plot;
	//CheckBox
	JCheckBox _elitismo;
	
	public GUI(Controller c) {
		_controller = c;
		
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
		String[] seleccionS = {"Ruleta", "Torneo", "Ranking", "Restos", "Truncamiento"};
		String[] cruceS = {"PMX", "OX", "Variantes OX", "Ciclos (CX)", "Rec.Rutas ERX", "Cod. Ordinal", "Propio"};
		String[] mutacionS = {"Inserción", "Intercambio", "Inversión", "Heurística", "Propia"};
		_tipoSeleccion = new JComboBox(seleccionS);
		_tipoSeleccion.setPreferredSize(new Dimension(215,25));
		
		
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
		_panelCruce.setLayout(new MigLayout("", "[130px,grow][78px]", "[25px][25px]"));
		
		//Mutacion
		_panelMutacion = new JPanel();
		_panelMutacion.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		_panelMutacion.setLayout(new MigLayout("", "[130px,grow][78px]", "[25px][25px]"));
		
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
					.addGroup(gl__panelOpciones.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl__panelOpciones.createSequentialGroup()
							.addGap(24)
							.addGroup(gl__panelOpciones.createParallelGroup(Alignment.LEADING)
								.addGroup(gl__panelOpciones.createSequentialGroup()
									.addComponent(_realizadoEn)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(_time))
								.addComponent(_botonComenzar, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, gl__panelOpciones.createSequentialGroup()
							.addGap(21)
							.addComponent(_panelPoblacion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl__panelOpciones.createSequentialGroup()
							.addGap(21)
							.addComponent(_panelIteraciones, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl__panelOpciones.createSequentialGroup()
							.addGap(21)
							.addComponent(_panelSeleccion, GroupLayout.PREFERRED_SIZE, 232, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl__panelOpciones.createSequentialGroup()
							.addGap(21)
							.addComponent(_panelCruce, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl__panelOpciones.createSequentialGroup()
							.addGap(21)
							.addComponent(_panelMutacion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl__panelOpciones.createSequentialGroup()
							.addContainerGap(21, Short.MAX_VALUE)
							.addComponent(_panelSemilla, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl__panelOpciones.createSequentialGroup()
							.addContainerGap(22, Short.MAX_VALUE)
							.addComponent(_panelMuestraSemilla, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl__panelOpciones.setVerticalGroup(
			gl__panelOpciones.createParallelGroup(Alignment.LEADING)
				.addGroup(gl__panelOpciones.createSequentialGroup()
					.addContainerGap()
					.addComponent(_panelPoblacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(_panelIteraciones, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
					.addGap(91)
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
		_labelMutacion = new JLabel("Mutacion:");
		_panelMutacion.add(_labelMutacion, "cell 0 0,alignx left,aligny center");
		_textFieldMutacion = new JTextField("5");
		_textFieldMutacion.setPreferredSize(new Dimension(100, 25));
		_panelMutacion.add(_textFieldMutacion, "cell 1 0,alignx left");
		
		_comboBoxMutacion = new JComboBox(mutacionS);
		_panelMutacion.add(_comboBoxMutacion, "cell 0 1 2 1,growx");
		_textFieldPoblacion = new JTextField("100");
		_textFieldPoblacion.setPreferredSize(new Dimension(100,25));
		_panelPoblacion.add(_textFieldPoblacion, "cell 1 0,alignx left,aligny top");
		_textFieldIteraciones = new JTextField("100");
		_textFieldIteraciones.setPreferredSize(new Dimension(100,25));
		_panelIteraciones.add(_textFieldIteraciones, "cell 1 0,alignx left,aligny top");
		_labelCruce = new JLabel("Cruce:");
		_panelCruce.add(_labelCruce, "cell 0 0,alignx left,aligny center");
		_textFieldCruce = new JTextField("60");
		_textFieldCruce.setPreferredSize(new Dimension(100, 25));
		_panelCruce.add(_textFieldCruce, "cell 1 0,alignx left,aligny top");
		
		_comboBoxCruce = new JComboBox(cruceS);
		_panelCruce.add(_comboBoxCruce, "cell 0 1 2 1,growx");
		_panelOpciones.setLayout(gl__panelOpciones);
		_botonComenzar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				_controller.startSimulation(_textFieldPoblacion.getText(),_textFieldIteraciones.getText(),(String) _comboBoxSeleccion.getSelectedItem(),_elitismo.isSelected(),_textFieldCruce.getText(), (String)_comboBoxCruce.getSelectedItem(), _textFieldMutacion.getText(), (String)_comboBoxMutacion.getSelectedItem(), _textFieldSemilla.getText());
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
