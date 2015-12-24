package com.ibm.katheder.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import com.ibm.katheder.map.Hiker;
import com.ibm.katheder.view.color.ColorScheme.ColorSchemes;

public class Visualisation extends JFrame {
	
	private static final long serialVersionUID = 751161800519505699L;

	private final int unit = 30;
	
	private int screenWidth;
	
	private MapVisulalization mapVisualization;
	
	public Visualisation(Hiker hiker){
				
		this.screenWidth = hiker.getGeoMap().getSize()*unit;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setJMenuBar(createMenuBar());
		
		this.mapVisualization = new MapVisulalization(hiker);
		this.add(mapVisualization);
		
		this.pack();
		this.setSize(this.screenWidth, this.screenWidth+50);
	}
		
	private JMenuBar createMenuBar() {
		final JMenuBar menuBar = new JMenuBar();

		// Add Options Tab
		final JMenu options = new JMenu("Optionen");
		options.setMnemonic(KeyEvent.VK_O);
		options.getAccessibleContext().setAccessibleDescription("Option Menu.");
		// Add Show/Hide Path Option
		final JMenuItem menuItem = new JMenuItem("A text-only menu item",
		                KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This option switches between the different visualization formats.");
		// TODO Action
		options.add(menuItem);
		// Add visualization options
		options.addSeparator();
		final JMenu visualizationSubMenu = new JMenu("Visualization Options"); 
		final ButtonGroup visualizationGroup = new ButtonGroup();
		JMenuItem rbMenuItem = new JRadioButtonMenuItem("Landscape");
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_R);
		rbMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mapVisualization.setColorScheme(ColorSchemes.LANDSCAPE);				
			}
		});
		visualizationGroup.add(rbMenuItem);
		visualizationSubMenu.add(rbMenuItem);
		rbMenuItem = new JRadioButtonMenuItem("RGB Scheme");
		rbMenuItem.setMnemonic(KeyEvent.VK_O);
		rbMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mapVisualization.setColorScheme(ColorSchemes.RGB);				
			}
		});
		visualizationGroup.add(rbMenuItem);
		visualizationSubMenu.add(rbMenuItem);
		options.add(visualizationSubMenu);
		
		menuBar.add(options);
		
		return menuBar;
	}
}
