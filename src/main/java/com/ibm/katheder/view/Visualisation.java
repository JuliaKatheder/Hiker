/**
 * 	Hiker Application. For educational purposes only.
 * 
 *  Copyright (C) 2016  Julia Katheder
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
 * {@link MapVisualization}.
 * </p>
 * 
 * @author Julia Katheder
 * @version 1.0
 * 
 */
public class Visualisation extends JFrame {

	private static final long serialVersionUID = 751161800519505699L;
	
	private static final String TXT_ABOUT_DETAILS = "About this application.";

	private static final String TXT_ABOUT = "About";

	private static final String TXT_HELP_MENU = "Help menu";

	private static final String TXT_HELP = "Help";

	private final JMenuBar menuBar;

	private MapVisualization mapVisualization;

	public Visualisation(Hiker hiker) {
		this.menuBar = new JMenuBar();

		this.mapVisualization = new MapVisualization(menuBar, hiker);
		this.add(mapVisualization);
		addMenuTab();
		this.setJMenuBar(menuBar);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
	}

	private void addMenuTab() {
		// Add Options Tab
		final JMenu helpMenu = new JMenu(TXT_HELP);
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.getAccessibleContext().setAccessibleDescription(TXT_HELP_MENU);
		// Add Show/Hide Path Option
		final JMenuItem menuItem = new JMenuItem(TXT_ABOUT, KeyEvent.VK_A);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				TXT_ABOUT_DETAILS);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		helpMenu.add(menuItem);

		menuBar.add(helpMenu);
	}

}
