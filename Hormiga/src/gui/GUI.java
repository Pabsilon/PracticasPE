package gui;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;

import javax.swing.JTabbedPane;

import org.math.plot.Plot2DPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

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
		panelProblema.add(panelSetUp, "cell 0 0,grow");
		panelSetUp.setLayout(new MigLayout("", "[]", "[]"));
		
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

}
