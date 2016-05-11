package gui;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import org.math.plot.Plot2DPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GUI extends JFrame{
	
	Plot2DPanel plot;
	JPanel[][] _mapa;
	final int _mapSize = 32;
	
	public void setComida(int x, int y){
		_mapa[x][y].setBackground(Color.GREEN);
	}
	
	public void setHormiga(int x, int y){
		_mapa[x][y].setBackground(Color.BLACK);
	}
	
	public void setComido(int x, int y){
		_mapa[x][y].setBackground(Color.RED);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GUI(Controller c) {
		this.setTitle("Practica 3: Hormiga");
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JTabbedPane tabPanel = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabPanel, "cell 0 0,grow");
		
		JPanel panelProblema = new JPanel();
		tabPanel.addTab("Problema", null, panelProblema, null);
		panelProblema.setLayout(new MigLayout("", "[200][grow]", "[grow]"));
		
		JPanel panelSetUp = new JPanel();
		panelSetUp.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelProblema.add(panelSetUp, "cell 0 0,alignx center,growy");
		
		JPanel PanelMutacion = new JPanel();
		PanelMutacion.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		PanelMutacion.setLayout(new MigLayout("", "[185][]", "[14px][]"));
		
		JLabel lblMutacin = new JLabel("Metodo de Mutacion:");
		PanelMutacion.add(lblMutacin, "cell 0 0,alignx left,aligny top");
		
		String[] mutaciones = {"Arbol","Funcion","Terminal"};
		JComboBox comboMutacion = new JComboBox(mutaciones);
		PanelMutacion.add(comboMutacion, "flowx,cell 0 1,growx");
		
		probMutacion = new JTextField("0.05");
		PanelMutacion.add(probMutacion, "cell 1 1");
		probMutacion.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setLayout(new MigLayout("", "[185,grow][]", "[][]"));
		
		JLabel lblSeleccion = new JLabel("Metodo de Seleccion:");
		panel.add(lblSeleccion, "cell 0 0");
		
		String[] selecciones = {"Ranking","Restos","Ruleta","Torneo","Truncamiento"};
		JComboBox comboSeleccion = new JComboBox(selecciones);
		panel.add(comboSeleccion, "cell 0 1,growx");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setLayout(new MigLayout("", "[grow]", "[][]"));
		
		JLabel lblMetodoDeCruce = new JLabel("Metodo de Cruce:");
		panel_1.add(lblMetodoDeCruce, "cell 0 0");
		
		String[] cruces = {"Intercambio"};
		JComboBox comboCruce = new JComboBox(cruces);
		panel_1.add(comboCruce, "cell 0 1,growx");
		
		JButton botonComenzar = new JButton("Comenzar");
		GroupLayout gl_panelSetUp = new GroupLayout(panelSetUp);
		gl_panelSetUp.setHorizontalGroup(
			gl_panelSetUp.createParallelGroup(Alignment.LEADING)
				.addComponent(PanelMutacion, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
				.addComponent(botonComenzar, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
		);
		gl_panelSetUp.setVerticalGroup(
			gl_panelSetUp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSetUp.createSequentialGroup()
					.addComponent(PanelMutacion, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 354, Short.MAX_VALUE)
					.addComponent(botonComenzar, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
		);
		panelSetUp.setLayout(gl_panelSetUp);
		
		JPanel panelMapa = new JPanel();
		panelMapa.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelProblema.add(panelMapa, "cell 1 0,grow");
		panelMapa.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
		
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
				c.comenzarSimulacion((String) comboCruce.getSelectedItem(), (String) comboMutacion.getSelectedItem(), probMutacion.getText(), (String) comboSeleccion.getSelectedItem());
			}
		});
		
		plot = new Plot2DPanel();
		panelGrafica.add(plot);
		
		//this.setPreferredSize(new Dimension(1200, 650));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);	
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField probMutacion;
}
