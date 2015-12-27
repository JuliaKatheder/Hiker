package com.ibm.katheder.view;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import com.ibm.katheder.map.Hiker;

/** 
 * <p>Visualization root for the graphical user interface. Currently contains the default layout 
 * consisting out of a {@link JMenuBar} and the {@link MapVisulalization}.</p>
 * 
 * @author Sterbling
 */
public class Visualisation extends JFrame {
	
	private static final long serialVersionUID = 751161800519505699L;

	private final JMenuBar menuBar;
		
	private MapVisulalization mapVisualization;
	
	public Visualisation(Hiker hiker){
		this.menuBar = new JMenuBar();
		
		this.mapVisualization = new MapVisulalization(menuBar, hiker);
		this.add(mapVisualization);
		this.setJMenuBar(menuBar);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
	}
		
}
