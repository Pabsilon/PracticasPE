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
	JPanel _panelPrincipal, _panelOpciones, _panelGrafica, _panelMapaOrdenado, _panelMapaDesordenado, _mapaOrdenado,_mapaDesordenado, _panelGraficaOpcional;
	JPanel _panelParticipantes, _panelMutacion, _panelCruce, _panelSeleccion, _panelResultados, _panelResultados2, _panelResultados3, _panelPoblacion, _panelIteraciones, _panelSemilla;
	JTabbedPane _test;
	//Labels
	JLabel _realizadoEn, _time,_labelMutacion, _labelCruce, _labelSeleccion, _labelPoblacion, _labelN, _labelIteraciones, _labelSemilla;

	JTextArea _labelMejorResultado, _labelMejorResultado2, _labelMejorResultado3;
	//ComboBox
	@SuppressWarnings("rawtypes")
	JComboBox _comboBoxSeleccion, _tipoSeleccion ,_comboBoxMutacion, _comboBoxCruce;
	//Text Fields
	JTextField _lastSeed, _textFieldParticipantes, _textFieldMutacion, _textFieldCruce, _textFieldPoblacion, _textFieldIteraciones, _textFieldN, _textFieldSemilla;
	//Botones
	JButton _botonComenzar;
	//Plot
	Plot2DPanel _plot;
	Plot2DPanel _plotOpcional;
	//CheckBox
	JCheckBox _elitismo;
	private JLabel lblCruce;
	private JLabel lblMut;
	private JLabel lblGen;
	private JTextField _mutacionDesde;
	private JTextField _generacionesDesde;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField _cruceHasta;
	private JTextField _mutacionHasta;
	private JTextField _generacionesHasta;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JTextField _cruceIntervalo;
	private JTextField _mutacionIntervalo;
	private JTextField _generacionesIntervalo;
	private JCheckBox _cruceCheck;
	private JCheckBox _mutacionCheck;
	private JCheckBox _generacionCheck;
	private JTextField _cruceDesde;
	
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
		this.setTitle("Practica 2: TSP");
		_panelPrincipal = new JPanel();
		_panelOpciones = new JPanel();
		_panelOpciones.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		_panelGrafica = new JPanel();
		_panelGraficaOpcional = new JPanel();
		_panelMapaOrdenado = new JPanel();
		_panelMapaDesordenado = new JPanel();
		_mapaOrdenado = new JPanel(new BorderLayout());
		_mapaDesordenado = new JPanel(new BorderLayout());
		_test = new JTabbedPane();
		_test.add("Grafica", _panelGrafica);
		_test.add("Grafica Opcional", _panelGraficaOpcional);
		_test.add("Mapa Ordenado", _panelMapaOrdenado);
		_test.add("Mapa Desordenado", _panelMapaDesordenado);
		_panelPrincipal.setLayout(new MigLayout("", "[253px][919px]", "[600px]"));
		_panelPrincipal.add(_panelOpciones, "cell 0 0,alignx center,aligny top");
		_panelPrincipal.add(_test, "cell 1 0,grow");
		
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
		_textFieldParticipantes = new JTextField("3");
		_textFieldParticipantes.setPreferredSize(new Dimension(72,25));
		
		//Metodo seleccion
		_panelSeleccion = new JPanel();
		_panelSeleccion.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		_panelSeleccion.setLayout(new MigLayout("", "[213px]", "[25px][25][25]"));
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
		
		JPanel _panelIntervalos = new JPanel();
		_panelIntervalos.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		_panelIntervalos.setLayout(new MigLayout("", "[][][grow][grow][grow][][grow]", "[][][]"));
		
		lblCruce = new JLabel("Cruce:");
		_panelIntervalos.add(lblCruce, "cell 0 0,alignx trailing");
		
		_cruceCheck = new JCheckBox("");
		_panelIntervalos.add(_cruceCheck, "cell 1 0");
		
		_cruceDesde = new JTextField();
		_cruceDesde.setText("0");
		_panelIntervalos.add(_cruceDesde, "cell 2 0,growx");
		_cruceDesde.setColumns(10);
		
		label = new JLabel("-");
		_panelIntervalos.add(label, "cell 3 0,alignx trailing");
		
		_cruceHasta = new JTextField();
		_cruceHasta.setText("100");
		_panelIntervalos.add(_cruceHasta, "cell 4 0,growx");
		_cruceHasta.setColumns(10);
		
		label_3 = new JLabel(":");
		_panelIntervalos.add(label_3, "cell 5 0,alignx trailing");
		
		_cruceIntervalo = new JTextField();
		_cruceIntervalo.setText("10");
		_panelIntervalos.add(_cruceIntervalo, "cell 6 0,growx");
		_cruceIntervalo.setColumns(10);
		
		lblMut = new JLabel("Mut:");
		_panelIntervalos.add(lblMut, "cell 0 1,alignx trailing");
		
		_mutacionCheck = new JCheckBox("");
		_panelIntervalos.add(_mutacionCheck, "cell 1 1");
		
		_mutacionDesde = new JTextField();
		_mutacionDesde.setText("0");
		_panelIntervalos.add(_mutacionDesde, "cell 2 1,growx");
		_mutacionDesde.setColumns(10);
		
		label_1 = new JLabel("-");
		_panelIntervalos.add(label_1, "cell 3 1,alignx trailing");
		
		_mutacionHasta = new JTextField();
		_mutacionHasta.setText("100");
		_panelIntervalos.add(_mutacionHasta, "cell 4 1,growx");
		_mutacionHasta.setColumns(10);
		
		label_4 = new JLabel(":");
		_panelIntervalos.add(label_4, "cell 5 1,alignx trailing");
		
		_mutacionIntervalo = new JTextField();
		_mutacionIntervalo.setText("10");
		_panelIntervalos.add(_mutacionIntervalo, "cell 6 1,growx");
		_mutacionIntervalo.setColumns(10);
		
		lblGen = new JLabel("Gen:");
		_panelIntervalos.add(lblGen, "cell 0 2,alignx trailing");
		
		_generacionCheck = new JCheckBox("");
		_panelIntervalos.add(_generacionCheck, "cell 1 2");
		
		_generacionesDesde = new JTextField();
		_generacionesDesde.setText("0");
		_panelIntervalos.add(_generacionesDesde, "cell 2 2,growx");
		_generacionesDesde.setColumns(10);
		
		label_2 = new JLabel("-");
		_panelIntervalos.add(label_2, "cell 3 2,alignx trailing");
		
		_generacionesHasta = new JTextField();
		_generacionesHasta.setText("100");
		_panelIntervalos.add(_generacionesHasta, "cell 4 2,growx");
		_generacionesHasta.setColumns(10);
		
		label_5 = new JLabel(":");
		_panelIntervalos.add(label_5, "cell 5 2,alignx trailing");
		
		_generacionesIntervalo = new JTextField();
		_generacionesIntervalo.setText("10");
		_panelIntervalos.add(_generacionesIntervalo, "cell 6 2,growx");
		_generacionesIntervalo.setColumns(10);
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
		_textFieldIteraciones = new JTextField("300");
		_textFieldIteraciones.setPreferredSize(new Dimension(100,25));
		_panelIteraciones.add(_textFieldIteraciones, "cell 1 0,alignx left,aligny top");
		_labelCruce = new JLabel("Cruce:");
		_panelCruce.add(_labelCruce, "cell 0 0,alignx left,aligny center");
		_textFieldCruce = new JTextField("60");
		_textFieldCruce.setPreferredSize(new Dimension(100, 25));
		_panelCruce.add(_textFieldCruce, "cell 1 0,alignx left,aligny top");
		
		_comboBoxCruce = new JComboBox(cruceS);
		_panelCruce.add(_comboBoxCruce, "cell 0 1 2 1,growx");
		_panelOpciones.setLayout(new MigLayout("", "[67px][6px][159px]", "[45px][45px][103px][74px][74px][45px][64px][91px][23px][14px]"));
		_panelOpciones.add(_realizadoEn, "cell 0 9,alignx right,aligny top");
		_panelOpciones.add(_time, "cell 2 9,alignx left,aligny top");
		_panelOpciones.add(_panelPoblacion, "cell 0 0 3 1,grow");
		_panelOpciones.add(_panelIteraciones, "cell 0 1 3 1,growx,aligny top");
		_panelOpciones.add(_panelSeleccion, "cell 0 2 3 1,alignx center,growy");
		_panelOpciones.add(_panelCruce, "cell 0 3 3 1,growx,aligny top");
		_panelOpciones.add(_panelMutacion, "cell 0 4 3 1,growx,aligny top");
		_panelOpciones.add(_panelSemilla, "cell 0 5 3 1,growx,aligny top");
		_panelOpciones.add(_panelMuestraSemilla, "cell 0 6 3 1,grow");
		_panelOpciones.add(_panelIntervalos, "cell 0 7 3 1,growx,aligny top");
		_panelOpciones.add(_botonComenzar, "cell 0 8 3 1,growx,aligny top");
		_botonComenzar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				int[][] parametros = new int[3][3];
				if(_cruceCheck.isSelected())
				{
					parametros[0][0] = Integer.parseInt(_cruceDesde.getText());
					parametros[0][1] = Integer.parseInt(_cruceHasta.getText());
					parametros[0][2] = Integer.parseInt(_cruceIntervalo.getText());
				}
				if(_mutacionCheck.isSelected())
				{
					parametros[1][0] = Integer.parseInt(_mutacionDesde.getText());
					parametros[1][1] = Integer.parseInt(_mutacionHasta.getText());
					parametros[1][2] = Integer.parseInt(_mutacionIntervalo.getText());
				}
				if(_generacionCheck.isSelected())
				{
					parametros[2][0] = Integer.parseInt(_generacionesDesde.getText());
					parametros[2][1] = Integer.parseInt(_generacionesHasta.getText());
					parametros[2][2] = Integer.parseInt(_generacionesIntervalo.getText());
				}
				_controller.startSimulation(_textFieldPoblacion.getText(),_textFieldIteraciones.getText(),(String) _comboBoxSeleccion.getSelectedItem(),_elitismo.isSelected(),_textFieldCruce.getText(), (String)_comboBoxCruce.getSelectedItem(), _textFieldParticipantes.getText(), _textFieldMutacion.getText(), (String)_comboBoxMutacion.getSelectedItem(), _textFieldSemilla.getText(), _cruceCheck.isSelected(), _mutacionCheck.isSelected(), _generacionCheck.isSelected(),parametros);
			}
		});
		_panelGrafica.setLayout(new MigLayout("", "[600px]", "[560px][60px]"));
		_panelGraficaOpcional.setLayout(new MigLayout("", "[925px]", "[560px]"));
		_panelMapaOrdenado.setLayout(new MigLayout("", "[925px]", "[550px][60px]"));
		_panelMapaDesordenado.setLayout(new MigLayout("", "[925px]", "[550px][60px]"));
		
		//Grafica
		_plot = new Plot2DPanel();
		_panelGrafica.add(_plot, "cell 0 0,grow");
		_panelMapaOrdenado.add(_mapaOrdenado, "cell 0 0,grow");
		_panelMapaDesordenado.add(_mapaDesordenado, "cell 0 0,grow");
		
		//Grafica opcional
		_plotOpcional = new Plot2DPanel();
		_panelGraficaOpcional.add(_plotOpcional, "cell 0 0,grow");
		
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
		
		_panelResultados2 = new JPanel();
		_panelResultados2.setLayout(new MigLayout("", "[960px]", "[15px]"));
		_labelMejorResultado2 = new JTextArea(1,80);
		_labelMejorResultado2.setLineWrap(true);
		_labelMejorResultado2.setEditable(false);  
		_labelMejorResultado2.setCursor(null);  
		_labelMejorResultado2.setOpaque(false);  
		_labelMejorResultado2.setFocusable(false);
		_labelMejorResultado2.setWrapStyleWord(true);
		_panelResultados2.add(_labelMejorResultado2, "cell 0 0");
		
		_panelResultados3 = new JPanel();
		_panelResultados3.setLayout(new MigLayout("", "[960px]", "[15px]"));
		_labelMejorResultado3 = new JTextArea(1,80);
		_labelMejorResultado3.setLineWrap(true);
		_labelMejorResultado3.setEditable(false);  
		_labelMejorResultado3.setCursor(null);  
		_labelMejorResultado3.setOpaque(false);  
		_labelMejorResultado3.setFocusable(false);
		_labelMejorResultado3.setWrapStyleWord(true);
		_panelResultados3.add(_labelMejorResultado3, "cell 0 0");
		
		_panelGrafica.add(_panelResultados, "cell 0 1,growx,aligny top");
		_panelMapaOrdenado.add(_panelResultados2, "cell 0 1,growx,aligny top");
		_panelMapaDesordenado.add(_panelResultados3, "cell 0 1,growx,aligny top");
		
		//Frame
		this.setContentPane(_panelPrincipal);
		this.setPreferredSize(new Dimension(1200, 650));
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
		_labelMejorResultado2.setText(resultado);
		_labelMejorResultado3.setText(resultado);
		
		//Remover plot opcional valores
		_plotOpcional.removeAllPlots();
	}
	
	public void fillPlotOpcional(double[] mejorAbsoluto, String tipo) 
	{

		
		//Posicion de la leyenda
		_plotOpcional.addLegend("SOUTH");
		
		double[] x = new double[mejorAbsoluto.length];
		for(int i = 0; i < mejorAbsoluto.length; i++)
		{
			x[i] = i + 1;
		}
		//A�adir las lineas
		_plotOpcional.addLinePlot(tipo, x, mejorAbsoluto);
		
	}

	public void drawCities(int[] resultado)
	{
		crearMapaOrdenado(resultado);
		crearMapaDesordenado(resultado);
	}
	
	private void crearMapaOrdenado(int[] resultado)
	{
		_mapaOrdenado.removeAll();
		
		Graph graph = new SingleGraph("Mapa");
		graph.addAttribute("ui.antialias");
		Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		
		
		for(int i : resultado)
		{
			Node nod = graph.addNode(SpainMap.getCityName(resultado[i]));
			nod.addAttribute("ui.label", SpainMap.getCityName(resultado[i]));
			nod.addAttribute("ui.frozen");
			if(SpainMap.getCityName(resultado[i]) == "Madrid"){
				nod.addAttribute("ui.style", "fill-color: rgb(0,100,255);");
				nod.addAttribute("xy", -3.41, 40.20 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Albacete"){
				nod.addAttribute("xy", -1.52, 39.00 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Alicante"){
				nod.addAttribute("xy", -0.29, 38.20 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Almeria"){
				nod.addAttribute("xy", -2.28, 36.50 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Avila"){
				nod.addAttribute("xy", -4.42, 40.39 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Badajoz"){
				nod.addAttribute("xy", -6.58, 38.53 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Barcelona"){
				nod.addAttribute("xy", 2.11, 41.23 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Bilbao"){
				nod.addAttribute("xy", -2.55, 43.15 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Burgos"){
				nod.addAttribute("xy", -3.42, 42.20 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Caceres"){
				nod.addAttribute("xy", -6.22, 39.28 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Cadiz"){
				nod.addAttribute("xy", -6.18, 36.32 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Castellon"){
				nod.addAttribute("xy", -0.02, 39.59 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Ciudad_Real"){
				nod.addAttribute("xy", -3.55, 38.59);
			}
			if(SpainMap.getCityName(resultado[i]) == "Cordoba"){
				nod.addAttribute("xy", -4.47, 37.53 );
			}
			if(SpainMap.getCityName(resultado[i]) == "A_Coruna"){
				nod.addAttribute("xy", -8.23, 43.22 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Cuenca"){
				nod.addAttribute("xy", -2.08, 40.04 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Gerona"){
				nod.addAttribute("xy", 2.49, 41.59 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Granada"){
				nod.addAttribute("xy", -3.35, 37.11 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Guadalajara"){
				nod.addAttribute("xy", -3.10, 40.38 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Huelva"){
				nod.addAttribute("xy", -6.57, 37.16 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Huesca"){
				nod.addAttribute("xy", -0.24, 42.08 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Jaen"){
				nod.addAttribute("xy", -3.47, 37.46 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Leon"){
				nod.addAttribute("xy", -5.34, 42.36 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Lerida"){
				nod.addAttribute("xy", -0.38, 41.37 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Logrono"){
				nod.addAttribute("xy", -2.27, 42.28 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Lugo"){
				nod.addAttribute("xy", -7.33, 43.01 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Malaga"){
				nod.addAttribute("xy", -4.25, 36.43 );
			}
			if(SpainMap.getCityName(resultado[i]) == "Murcia"){
				nod.addAttribute("xy", -1.07, 37.59 );
			}
			
			
		}
		
		for(int i = 1; i < SpainMap.getNumberOfCities(); i++)
		{
			Edge ed= graph.addEdge(SpainMap.getCityName(resultado[i - 1]) + SpainMap.getCityName(resultado[i]), SpainMap.getCityName(resultado[i - 1]), SpainMap.getCityName(resultado[i]),true);
			ed.addAttribute("ui.label", SpainMap.getDistance(resultado[i - 1], resultado[i]));
		}
		
		Edge ed = graph.addEdge(SpainMap.getCityName(resultado[0]) + SpainMap.getCityName(resultado[resultado.length - 1]), SpainMap.getCityName(resultado[resultado.length - 1]), SpainMap.getCityName(resultado[0]),true);
		ed.addAttribute("ui.label", SpainMap.getDistance(resultado[0], resultado[resultado.length - 1]));
		ed.addAttribute("ui.style", "fill-color: rgb(255,0,0);");
		
		ViewPanel view = viewer.addDefaultView(false);
		view.setBounds(0, 0, _mapaOrdenado.getWidth(), _mapaOrdenado.getHeight());
		_mapaOrdenado.add(view, BorderLayout.CENTER);
		_mapaOrdenado.repaint();
		_mapaOrdenado.validate();		
	}
	
	private void crearMapaDesordenado(int resultado[])
	{
		_mapaDesordenado.removeAll();
		
		Graph graph = new SingleGraph("Mapa2");
		graph.clear();
		Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.enableAutoLayout();
		graph.addAttribute("ui.antialias");
		
		for(int i : resultado)
		{
			Node nod = graph.addNode(SpainMap.getCityName(resultado[i]));
			nod.addAttribute("ui.label", SpainMap.getCityName(resultado[i]));
			if(SpainMap.getCityName(resultado[i]) == "Madrid")
				nod.addAttribute("ui.style", "fill-color: rgb(0,100,255);");
		}
		
		for(int i = 1; i < SpainMap.getNumberOfCities(); i++)
		{
			Edge ed= graph.addEdge(SpainMap.getCityName(resultado[i - 1]) + SpainMap.getCityName(resultado[i]), SpainMap.getCityName(resultado[i - 1]), SpainMap.getCityName(resultado[i]),true);
			ed.addAttribute("ui.label", SpainMap.getDistance(resultado[i - 1], resultado[i]));
		}
		
		Edge ed = graph.addEdge(SpainMap.getCityName(resultado[0]) + SpainMap.getCityName(resultado[resultado.length - 1]), SpainMap.getCityName(resultado[resultado.length - 1]), SpainMap.getCityName(resultado[0]),true);
		ed.addAttribute("ui.label", SpainMap.getDistance(resultado[0], resultado[resultado.length - 1]));
		ed.addAttribute("ui.style", "fill-color: rgb(255,0,0);");
		
		ViewPanel view = viewer.addDefaultView(false);
		view.setBounds(0, 0, _panelMapaDesordenado.getWidth(), _mapaDesordenado.getHeight());
		_mapaDesordenado.add(view, BorderLayout.CENTER);
		_mapaDesordenado.repaint();
		_mapaDesordenado.validate();
	}
}
