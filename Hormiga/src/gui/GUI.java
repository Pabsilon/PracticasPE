package gui;

import implementacion.Hormiga;
import implementacion.Tablero;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import org.math.plot.Plot2DPanel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;

public class GUI extends JFrame{
	
	Plot2DPanel _plot;
	JPanel[][] _mapa;
	JLabel _generadoEn;
	JTextField _semillaAnt;
	JEditorPane _textoResultado;
	final int _mapSize = 32;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GUI(Controller c) {
		this.setTitle("Practica 3: Hormiga");
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JTabbedPane tabPanel = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabPanel, "cell 0 0,grow");
		
		JPanel panelProblema = new JPanel();
		tabPanel.addTab("Problema", null, panelProblema, null);
		panelProblema.setLayout(new MigLayout("", "[200][grow][grow]", "[]"));
		
		JPanel panelSetUp = new JPanel();
		panelSetUp.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelProblema.add(panelSetUp, "cell 0 0,alignx center,growy");
		
		JPanel PanelMutacion = new JPanel();
		PanelMutacion.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		PanelMutacion.setLayout(new MigLayout("", "[185]", "[14px][]"));
		
		JLabel lblMutacin = new JLabel("Mutacion:");
		PanelMutacion.add(lblMutacin, "flowx,cell 0 0,alignx left,aligny top");
		
		String[] mutaciones = {"Arbol","Funcion","Terminal"};
		JComboBox comboMutacion = new JComboBox(mutaciones);
		PanelMutacion.add(comboMutacion, "flowx,cell 0 1,growx");
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setLayout(new MigLayout("", "[185,grow]", "[][][]"));
		
		JLabel lblSeleccion = new JLabel("Metodo de Seleccion:");
		panel.add(lblSeleccion, "cell 0 0");
		
		JTextField textFieldParticipantes;
		textFieldParticipantes = new JTextField("3");
		textFieldParticipantes.setPreferredSize(new Dimension(72,25));
		
		String[] selecciones = {"Ranking","Restos","Ruleta","Torneo","Truncamiento"};
		JComboBox comboSeleccion = new JComboBox(selecciones);
		panel.add(comboSeleccion, "cell 0 1,growx");
		comboSeleccion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String s = (String)comboSeleccion.getSelectedItem();
				if (s=="Torneo"){
					panel.add(textFieldParticipantes, "cell 0 2,alignx left,aligny center");
					panelSetUp.revalidate();
					panelSetUp.repaint();
				}else{
					panel.remove(textFieldParticipantes);
					panelSetUp.revalidate();
					panelSetUp.repaint();
				}
				
			}
			
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setLayout(new MigLayout("", "[grow]", "[][][]"));
		
		JLabel lblMetodoDeCruce = new JLabel("Metodo de Cruce:");
		panel_1.add(lblMetodoDeCruce, "cell 0 0");
		
		String[] cruces = {"Intercambio"};
		JComboBox comboCruce = new JComboBox(cruces);
		panel_1.add(comboCruce, "cell 0 1,growx");
		
		JButton botonComenzar = new JButton("Comenzar");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		GroupLayout gl_panelSetUp = new GroupLayout(panelSetUp);
		gl_panelSetUp.setHorizontalGroup(
			gl_panelSetUp.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelSetUp.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSetUp.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_7, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
						.addComponent(botonComenzar, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
						.addComponent(panel_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
						.addComponent(panel_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
						.addComponent(PanelMutacion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelSetUp.setVerticalGroup(
			gl_panelSetUp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSetUp.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PanelMutacion, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
					.addComponent(botonComenzar, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_7.setLayout(new MigLayout("", "[][grow]", "[]"));
		
		JLabel lblProfundidad = new JLabel("Profundidad:");
		panel_7.add(lblProfundidad, "cell 0 0,alignx trailing");
		
		profundidad = new JTextField();
		profundidad.setHorizontalAlignment(SwingConstants.TRAILING);
		profundidad.setText("10");
		panel_7.add(profundidad, "cell 1 0,growx");
		profundidad.setColumns(10);
		panel_6.setLayout(new MigLayout("", "[grow]", "[][]"));
		
		JCheckBox checkBloating = new JCheckBox("Bloating");
		panel_6.add(checkBloating, "cell 0 0");
		
		String[] bloatings = {"Tarpeian","Penalizacion"};
		JComboBox comboBloating = new JComboBox(bloatings);
		panel_6.add(comboBloating, "cell 0 1,growx");
		panel_5.setLayout(new MigLayout("", "[][]", "[]"));
		
		JLabel lblGeneradoEn = new JLabel("Generado en:");
		panel_5.add(lblGeneradoEn, "cell 0 0");
		
		_generadoEn = new JLabel("");
		panel_5.add(_generadoEn, "cell 1 0");
		panel_4.setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		JLabel lblSemilla = new JLabel("Semilla:");
		panel_4.add(lblSemilla, "cell 0 0,alignx trailing");
		
		semilla = new JTextField();
		semilla.setHorizontalAlignment(SwingConstants.TRAILING);
		semilla.setText("0");
		panel_4.add(semilla, "cell 1 0,growx");
		semilla.setColumns(10);
		
		JLabel lblAnterior = new JLabel("Anterior:");
		panel_4.add(lblAnterior, "cell 0 1,alignx trailing");
		
		_semillaAnt = new JTextField();
		_semillaAnt.setHorizontalAlignment(SwingConstants.TRAILING);
		_semillaAnt.setEditable(false);
		_semillaAnt.setText("0");
		panel_4.add(_semillaAnt, "cell 1 1,growx");
		_semillaAnt.setColumns(10);
		
		probMutacion = new JTextField("5");
		probMutacion.setHorizontalAlignment(SwingConstants.TRAILING);
		PanelMutacion.add(probMutacion, "cell 0 0");
		probMutacion.setColumns(10);
		
		JCheckBox elitismo = new JCheckBox("Elitismo");
		panel.add(elitismo, "cell 0 2");
		panel_3.setLayout(new MigLayout("", "[][grow]", "[]"));
		
		JLabel lblGeneraciones = new JLabel("Generaciones:");
		panel_3.add(lblGeneraciones, "cell 0 0,alignx trailing");
		
		generaciones = new JTextField();
		generaciones.setHorizontalAlignment(SwingConstants.TRAILING);
		generaciones.setText("300");
		panel_3.add(generaciones, "cell 1 0,growx");
		generaciones.setColumns(10);
		panel_2.setLayout(new MigLayout("", "[][grow]", "[]"));
		
		JLabel lblPoblacion = new JLabel("Poblacion:");
		panel_2.add(lblPoblacion, "cell 0 0,alignx trailing");
		
		poblacion = new JTextField();
		poblacion.setHorizontalAlignment(SwingConstants.TRAILING);
		poblacion.setText("100");
		panel_2.add(poblacion, "cell 1 0,growx");
		poblacion.setColumns(10);
		
		JLabel labelProbCruce = new JLabel("Probabilidad:");
		panel_1.add(labelProbCruce, "flowx,cell 0 2");
		
		probabilidadCruce = new JTextField();
		probabilidadCruce.setHorizontalAlignment(SwingConstants.TRAILING);
		probabilidadCruce.setText("60");
		panel_1.add(probabilidadCruce, "cell 0 2");
		probabilidadCruce.setColumns(10);
		panelSetUp.setLayout(gl_panelSetUp);
		
		JPanel panelMapa = new JPanel();
		panelMapa.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelProblema.add(panelMapa, "cell 1 0,grow");
		panelMapa.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
		
		JPanel panelRespuesta = new JPanel();
		panelRespuesta.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelProblema.add(panelRespuesta, "cell 2 0,grow");
		panelRespuesta.setLayout(new MigLayout("", "[grow]", "[]"));
		
		_textoResultado = new JEditorPane();
		_textoResultado.setPreferredSize(new Dimension(200,700));
		_textoResultado.setEnabled(false);
		_textoResultado.setEditable(false);
		JScrollPane scroll = new JScrollPane(_textoResultado);
		panelRespuesta.add(scroll, "cell 0 0,grow");
		
		_mapa = new JPanel[_mapSize][_mapSize];
		for (int i = 0; i<_mapSize; i++){
			for (int j = 0; j<_mapSize; j++){
				_mapa[i][j] = new JPanel();
				String test = "cell "+i+ " "+ j +",grow";
				_mapa[i][j].setBorder((new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null)));
				panelMapa.add(_mapa[i][j], test);
			}
		}
		
		JPanel panelGrafica = new JPanel();
		tabPanel.addTab("Grafica", null, panelGrafica, null);
		panelGrafica.setLayout(new BorderLayout(0, 0));
		
		botonComenzar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				actualizaMapa(new Tablero());
				c.comenzarSimulacion(poblacion.getText(), generaciones.getText(), textFieldParticipantes.getText(), (String) comboCruce.getSelectedItem(), probabilidadCruce.getText(), (String) comboMutacion.getSelectedItem(), probMutacion.getText(), (String) comboSeleccion.getSelectedItem(), elitismo.isSelected(), semilla.getText(), checkBloating.isSelected(), (String) comboBloating.getSelectedItem(), profundidad.getText());
			}
		});
		
		_plot = new Plot2DPanel();
		panelGrafica.add(_plot);
		
		actualizaMapa(new Tablero());
		
	//	this.setPreferredSize(new Dimension(1400, 900));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);	
		
	}
	
	public void setTime(float time){
		_generadoEn.setText(Float.toString(time) + " s");
	}
	
	public void setSeed(long semilla){
		_semillaAnt.setText(Long.toString(semilla));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField probMutacion;
	private JTextField probabilidadCruce;
	private JTextField poblacion;
	private JTextField generaciones;
	private JTextField semilla;
	private JTextField profundidad;

	public void fillPlot(double[] mejorAbsoluto, double[] mejorGeneracion, double[] mediaGeneracion, String fenotipo)
	{
		_plot.removeAllPlots();
		
		//Posicion de la leyenda
		_plot.addLegend("SOUTH");
		
		double[] x = new double[mediaGeneracion.length];
		for(int i = 0; i < mediaGeneracion.length; i++)
		{
			x[i] = i + 1;
		}
		//A�adir las lineas
		_plot.addLinePlot("Mejor Absoluto", x, mejorAbsoluto);
		_plot.addLinePlot("Mejor Generacion", x, mejorGeneracion);
		_plot.addLinePlot("Media Generacion", x, mediaGeneracion);
		
		_textoResultado.setText(fenotipo);
				
		
	}
	
	public void actualizaMapa(Tablero t){
		for (int i=0; i<32; i++){
			for (int j = 0; j<32; j++){
				if (t.getValue(i, j).equalsIgnoreCase("Comida")){
					setComida(i, j);
				}else if (t.getValue(i, j).equalsIgnoreCase("Comido")){
					setComido(i, j);
				}else if (t.getValue(i, j).equalsIgnoreCase("Hormiga")){
					setHormiga(i, j);
				}
				else
				{
					setVacio(i, j);
				}
			}
		}
	}
	
	private void setVacio(int x, int y) {
		_mapa[x][y].setBackground(Color.lightGray);		
	}

	public void setComida(int x, int y){
		_mapa[x][y].setBackground(Color.GREEN);
	}
	
	public void setHormiga(int x, int y){
		_mapa[x][y].setBackground(Color.BLACK);
	}
	
	public void setComido(int x, int y){
		_mapa[x][y].setBackground(Color.RED);
	}
}
