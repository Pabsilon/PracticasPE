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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.math.plot.Plot2DPanel;

import implementation.SpainMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.JCheckBox;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.*;
import org.graphstream.ui.view.Viewer;

/**GUI del problema. Generada con ayuda de codigo externo, es practicamente ilegible.
 * Fly, you fools!
 * @author Pablo Mac-Veigh
 *
 */
public class GUI extends JFrame{

	private static final long serialVersionUID = 1L;

	private Controller _controller;
	
	//Paneles
	JPanel _panelPrincipal, _panelOpciones, _panelGrafica, _panelMapa;
	JPanel _panelParticipantes, _panelMutacion, _panelCruce, _panelSeleccion, _panelResultados, _panelPoblacion, _panelIteraciones, _panelSemilla;
	JTabbedPane _test;
	//Labels
	JLabel _realizadoEn, _time,_labelMutacion, _labelCruce, _labelSeleccion, _labelPoblacion, _labelN, _labelIteraciones, _labelSemilla;

	JTextArea _labelMejorResultado;
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
	 * Inicializacion de la gui.
	 */
	//HERE BE DRAGONS. ABANDON ALL HOPE YE WHO ENTERS THIS PLACE
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void inicializarGUI()
	{
		//Principal
		_panelPrincipal = new JPanel(new BorderLayout());
		_panelOpciones = new JPanel();
		_panelGrafica = new JPanel();
		_panelMapa = new JPanel(new BorderLayout());
		_test = new JTabbedPane();
		_test.add("Grafica", _panelGrafica);
		_test.add("Mapa", _panelMapa);
		_panelPrincipal.add(_panelOpciones, BorderLayout.LINE_START);
		_panelPrincipal.add(_test, BorderLayout.CENTER);
		
		//N
		_labelN = new JLabel("N: ");
		_labelN.setPreferredSize(new Dimension(10,25));
		_textFieldN = new JTextField("1");
		_textFieldN.setPreferredSize(new Dimension(70,25));
		String[] seleccionS = {"Ruleta", "Torneo", "Ranking", "Restos", "Truncamiento"};
		String[] cruceS = {"PMX", "OX", "OX_PP", "OX_OP", "Ciclos (CX)", "Rec.Rutas (ERX)", "Cod. Ordinal", "Propio"};
		String[] mutacionS = {"Insercion", "Intercambio", "Inversion", "Heuristica", "Propio"};
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
		_comboBoxSeleccion = new JComboBox(seleccionS);
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
				_controller.startSimulation(_textFieldPoblacion.getText(),_textFieldIteraciones.getText(),(String) _comboBoxSeleccion.getSelectedItem(),_elitismo.isSelected(),_textFieldCruce.getText(), (String)_comboBoxCruce.getSelectedItem(), _textFieldParticipantes.getText(), _textFieldMutacion.getText(), (String)_comboBoxMutacion.getSelectedItem(), _textFieldSemilla.getText());
			}
		});
		_panelGrafica.setLayout(new MigLayout("", "[925px]", "[660px][60px]"));
		
		//Grafica
		_plot = new Plot2DPanel();
		_panelGrafica.add(_plot, "cell 0 0,grow");
		
		//Resultados
		_panelResultados = new JPanel();
		_panelResultados.setLayout(new MigLayout("", "[960px]", "[15px]"));
		_labelMejorResultado = new JTextArea(1,80);
		_labelMejorResultado.setLineWrap(true);
		_labelMejorResultado.setEditable(false);  
		_labelMejorResultado.setCursor(null);  
		_labelMejorResultado.setOpaque(false);  
		_labelMejorResultado.setFocusable(false);
		_labelMejorResultado.setWrapStyleWord(true);
		_panelResultados.add(_labelMejorResultado, "cell 0 0");
		
		_panelGrafica.add(_panelResultados, "cell 0 1,growx,aligny top");
		
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
	
	/**Metodo que rellena la grafica con la informacion generada.
	 * @param mejorAbsoluto El array del mejor absoluto por generacion.
	 * @param mejorGeneracion El array de los mejores de cada generacion.
	 * @param mediaGeneracion El array de medias de cada generacion.
	 * @param numGeneraciones El numero de generaciones.
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
	
	public void drawCities(int[] resultado)
	{
		_panelMapa.removeAll();
		
		Graph graph = new SingleGraph("Mapa");
		graph.addAttribute("ui.antialias");
		Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		//viewer.enableAutoLayout();
		
		
		for(int i : resultado)
		{
			Node nod = graph.addNode(SpainMap.getCityName(i));
			nod.addAttribute("ui.label", SpainMap.getCityName(i));
			nod.addAttribute("ui.frozen");
			if(SpainMap.getCityName(i) == "Madrid"){
				nod.addAttribute("ui.style", "fill-color: rgb(0,100,255);");
				nod.addAttribute("xy", -3.41, 40.20 );
			}
			if(SpainMap.getCityName(i) == "Albacete"){
				nod.addAttribute("xy", -1.52, 39.00 );
			}
			if(SpainMap.getCityName(i) == "Alicante"){
				nod.addAttribute("xy", -0.29, 38.20 );
			}
			if(SpainMap.getCityName(i) == "Almeria"){
				nod.addAttribute("xy", -2.28, 36.50 );
			}
			if(SpainMap.getCityName(i) == "Avila"){
				nod.addAttribute("xy", -4.42, 40.39 );
			}
			if(SpainMap.getCityName(i) == "Badajoz"){
				nod.addAttribute("xy", -6.58, 38.53 );
			}
			if(SpainMap.getCityName(i) == "Barcelona"){
				nod.addAttribute("xy", 2.11, 41.23 );
			}
			if(SpainMap.getCityName(i) == "Bilbao"){
				nod.addAttribute("xy", -2.55, 43.15 );
			}
			if(SpainMap.getCityName(i) == "Burgos"){
				nod.addAttribute("xy", -3.42, 42.20 );
			}
			if(SpainMap.getCityName(i) == "Caceres"){
				nod.addAttribute("xy", -6.22, 39.28 );
			}
			if(SpainMap.getCityName(i) == "Cadiz"){
				nod.addAttribute("xy", -6.18, 36.32 );
			}
			if(SpainMap.getCityName(i) == "Castellon"){
				nod.addAttribute("xy", -0.02, 39.59 );
			}
			if(SpainMap.getCityName(i) == "Ciudad_Real"){
				nod.addAttribute("xy", -3.55, 38.59);
			}
			if(SpainMap.getCityName(i) == "Cordoba"){
				nod.addAttribute("xy", -4.47, 37.53 );
			}
			if(SpainMap.getCityName(i) == "A_Coruna"){
				nod.addAttribute("xy", -8.23, 43.22 );
			}
			if(SpainMap.getCityName(i) == "Cuenca"){
				nod.addAttribute("xy", -2.08, 40.04 );
			}
			if(SpainMap.getCityName(i) == "Gerona"){
				nod.addAttribute("xy", 2.49, 41.59 );
			}
			if(SpainMap.getCityName(i) == "Granada"){
				nod.addAttribute("xy", -3.35, 37.11 );
			}
			if(SpainMap.getCityName(i) == "Guadalajara"){
				nod.addAttribute("xy", -3.10, 40.38 );
			}
			if(SpainMap.getCityName(i) == "Huelva"){
				nod.addAttribute("xy", -6.57, 37.16 );
			}
			if(SpainMap.getCityName(i) == "Huesca"){
				nod.addAttribute("xy", -0.24, 42.08 );
			}
			if(SpainMap.getCityName(i) == "Jaen"){
				nod.addAttribute("xy", -3.47, 37.46 );
			}
			if(SpainMap.getCityName(i) == "Leon"){
				nod.addAttribute("xy", -5.34, 42.36 );
			}
			if(SpainMap.getCityName(i) == "Lerida"){
				nod.addAttribute("xy", -0.38, 41.37 );
			}
			if(SpainMap.getCityName(i) == "Logrono"){
				nod.addAttribute("xy", -2.27, 42.28 );
			}
			if(SpainMap.getCityName(i) == "Lugo"){
				nod.addAttribute("xy", -7.33, 43.01 );
			}
			if(SpainMap.getCityName(i) == "Malaga"){
				nod.addAttribute("xy", -4.25, 36.43 );
			}
			if(SpainMap.getCityName(i) == "Murcia"){
				nod.addAttribute("xy", -1.07, 37.59 );
			}
			
			
		}
		
		for(int i = 1; i < resultado.length; i++)
		{
			Edge ed= graph.addEdge(SpainMap.getCityName(resultado[i - 1]) + SpainMap.getCityName(resultado[i]), SpainMap.getCityName(resultado[i - 1]), SpainMap.getCityName(resultado[i]));
			ed.addAttribute("ui.label", SpainMap.getDistance(i-1, i));
		}
		
		//Edge ed = graph.addEdge(SpainMap.getCityName(0) + SpainMap.getCityName(resultado.length - 1), SpainMap.getCityName(0), SpainMap.getCityName(resultado.length - 1));
		//ed.addAttribute("ui.label", SpainMap.getDistance(0, resultado.length - 1));
		
		ViewPanel view = viewer.addDefaultView(false);
		view.setBounds(0, 0, _panelMapa.getWidth(), _panelMapa.getHeight());
		_panelMapa.add(view, BorderLayout.CENTER);
		_panelMapa.repaint();
		_panelMapa.validate();
	}
}
