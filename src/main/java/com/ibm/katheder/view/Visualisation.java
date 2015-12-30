package com.ibm.katheder.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.ibm.katheder.map.Hiker;

/**
 * <p>
 * Visualization root for the graphical user interface. Currently contains the
 * default layout consisting out of a {@link JMenuBar} and the
 * {@link MapVisulalization}.
 * </p>
 * 
 * @author Sterbling
 */
public class Visualisation extends JFrame {

	private static final long serialVersionUID = 751161800519505699L;

	private final JMenuBar menuBar;

	private MapVisulalization mapVisualization;

	public Visualisation(Hiker hiker) {
		this.menuBar = new JMenuBar();

		this.mapVisualization = new MapVisulalization(menuBar, hiker);
		this.add(mapVisualization);
		addMenuTab();
		this.setJMenuBar(menuBar);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
	}

	private void addMenuTab() {
		// Add Options Tab
		final JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.getAccessibleContext().setAccessibleDescription("Help menu");
		// Add Show/Hide Path Option
		final JMenuItem menuItem = new JMenuItem("About", KeyEvent.VK_A);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"About this application.");
		menuItem.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		helpMenu.add(menuItem);

			
		menuBar.add(helpMenu);
	}

}
