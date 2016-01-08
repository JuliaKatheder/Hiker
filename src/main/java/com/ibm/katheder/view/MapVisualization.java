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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import com.ibm.katheder.map.GeoMap;
import com.ibm.katheder.map.Hiker;
import com.ibm.katheder.map.MapPosition;
import com.ibm.katheder.map.TerrainType;
import com.ibm.katheder.map.error.InvalidMapPositionException;
import com.ibm.katheder.map.generation.RandomMapGenerator;
import com.ibm.katheder.view.action.ChangeColorSchemeAction;
import com.ibm.katheder.view.action.RandomMapAction;
import com.ibm.katheder.view.color.ColorScheme;
import com.ibm.katheder.view.color.ColorScheme.ColorSchemes;
import com.ibm.katheder.view.color.LandscapeStaticColorScheme;
import com.ibm.katheder.view.color.RGBDynamicColorScheme;

/**
 * <p>
 * Visualization component for a {@link Hiker}. Uses a {@link GeoMap} to draw
 * the landscape and places the Hikers {@link MapPosition} and his destination
 * (as {@link MapPosition}) to the Canvas.
 * </p>
 * <p>
 * Furthermore it adds a Menu Tab to the MenuBar with actions to generate a
 * random map via {@link RandomMapGenerator} and change the color theme to RGB (
 * {@link RGBDynamicColorScheme}) or Landscape (
 * {@link LandscapeStaticColorScheme}).
 * </p>
 * 
 * @author Julia Katheder
 * @version 1.0
 */
public class MapVisualization extends JComponent implements MouseListener {

	private static final long serialVersionUID = 751161800519505699L;

	private static final String TXT_RGB_SCHEME = "RGB Scheme";
	
	private static final String TXT_LANDSCAPE = "Landscape";
	
	private static final String TXT_VISUALIZATION_OPTIONS = "Visualization Options";
	
	private static final String TXT_VISUALIZATION = "This option switches between the different visualization formats.";
	
	private static final String TXT_MAP_MENU = "Map options menu";
	
	private static final String TXT_RANDOM = "RANDOM!";
	
	private static final String TXT_MAP = "Map";

	/** Time between clicks for a doubleclick in ms. */
	private static final int TIME_DOUBLECLICK = 250;

	/** Size of an element in the canvas in pixels. */
	private static final int unit = 30;

	/** Screen width and height in pixels. */
	private int screenHeight;
	private int screenWidth;

	/** Timer for double click action */
	private boolean isAlreadyOneClick;
	private Timer doubleClickTimer = new Timer("doubleclickTimer", false);

	/** The colorscheme used for painting. */
	private ColorScheme colorScheme;

	private Hiker hiker;

	/**
	 * Constructor. The default colorScheme is the
	 * {@link LandscapeStaticColorScheme}.
	 * 
	 * @param menuBar
	 *            the menubar which is displayed on top of the frame. Add here
	 *            possible menu options for the canvas.
	 * @param hiker
	 *            see {@link Hiker}. Represents the Model for this view.
	 */
	public MapVisualization(JMenuBar menuBar, Hiker hiker) {
		super();
		this.hiker = hiker;
		this.colorScheme = new LandscapeStaticColorScheme();

		addMenuTab(menuBar);
		setScreenDimensions();
		this.addMouseListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		System.out.println("Painting the awesome map...");
		// Paint the landscape
		for (int rowIndx = 0; rowIndx < screenHeight; rowIndx++) {
			for (int colIndx = 0; colIndx < screenWidth; colIndx++) {
				final int mappingId = hiker.getGeoMap()
						.getFieldType(rowIndx, colIndx).getMappingId();
				g.setColor(colorScheme.getColorFor(mappingId));
				g.fillRect(unit * colIndx, unit * rowIndx, unit, unit);

			}
		}
		// Add the hiker and his destination
		g.setColor(colorScheme.getEleColor());
		g.fillOval(hiker.getPosX() * unit, hiker.getPosY() * unit, unit, unit);
		g.fillRect(hiker.getDestX() * unit, hiker.getDestY() * unit, unit, unit);

		final List<MapPosition> route = hiker.findPath();

		for (MapPosition pathPoint : route) {
			System.out.println(pathPoint.toString());
			g.fillRect(pathPoint.getX() * unit + unit / 4, pathPoint.getY()
					* unit + unit / 4, unit / 2, unit / 2);
		}

	}

	public void setColorScheme(ColorSchemes landscape) {
		final ColorScheme newScheme;
		// This could be in a builder or sth...
		switch (landscape) {
		case RGB:
			final List<TerrainType> terrainTypes = new LinkedList<>(hiker
					.getGeoMap().getTerrainTypes().values());
			newScheme = new RGBDynamicColorScheme(terrainTypes);
			break;
		default:
			newScheme = new LandscapeStaticColorScheme();
			break;
		}

		this.colorScheme = newScheme;
		repaint();
	}

	public void setNewMap(GeoMap generateMap) {
		hiker.setGeoMap(generateMap);
		repaint();
		setScreenDimensions();
		// Resize the window after new map was set
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		topFrame.pack();
	}

	/**
	 * On the click event the destination of the hiker can be changed.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		final MapVisualization vis = this;
		final int positionX = e.getX() / unit;
		final int positionY = e.getY() / unit;

		if (isAlreadyOneClick) {
			isAlreadyOneClick = false;
			try {
				hiker.setPosition(new MapPosition(positionY, positionX));
				repaint();
			} catch (InvalidMapPositionException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(),
						e1.getTitle(), JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
		} else {
			isAlreadyOneClick = true;
			doubleClickTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					if (isAlreadyOneClick) {
						try {
							hiker.setDestination(new MapPosition(positionY,
									positionX));
						} catch (InvalidMapPositionException e) {
							JOptionPane.showMessageDialog(vis, e.getMessage(),
									e.getTitle(), JOptionPane.WARNING_MESSAGE);
							e.printStackTrace();
						}
						vis.repaint();
					}
					isAlreadyOneClick = false;
				}
			}, TIME_DOUBLECLICK);
		}
	}

	private void setScreenDimensions() {
		this.screenHeight = hiker.getGeoMap().getHeight();
		this.screenWidth = hiker.getGeoMap().getWidth();
		this.setPreferredSize(new Dimension(screenWidth * unit, screenHeight
				* unit));
	}

	private void addMenuTab(JMenuBar menuBar) {
		// Add Options Tab
		final JMenu options = new JMenu(TXT_MAP);
		options.setMnemonic(KeyEvent.VK_O);
		options.getAccessibleContext().setAccessibleDescription(TXT_MAP_MENU);
		// Add Show/Hide Path Option
		final JMenuItem menuItem = new JMenuItem(TXT_RANDOM, KeyEvent.VK_R);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				TXT_VISUALIZATION);
		menuItem.addActionListener(new RandomMapAction(this));
		options.add(menuItem);
		options.addSeparator();
		// Add visualization options
		final JMenu visualizationSubMenu = new JMenu(TXT_VISUALIZATION_OPTIONS);
		final ButtonGroup visualizationGroup = new ButtonGroup();
		JMenuItem rbMenuItem = new JRadioButtonMenuItem(TXT_LANDSCAPE);
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_R);
		rbMenuItem.addActionListener(new ChangeColorSchemeAction(this,
				ColorSchemes.LANDSCAPE));
		visualizationGroup.add(rbMenuItem);
		visualizationSubMenu.add(rbMenuItem);
		rbMenuItem = new JRadioButtonMenuItem(TXT_RGB_SCHEME);
		rbMenuItem.setMnemonic(KeyEvent.VK_O);
		rbMenuItem.addActionListener(new ChangeColorSchemeAction(this,
				ColorSchemes.RGB));
		visualizationGroup.add(rbMenuItem);
		visualizationSubMenu.add(rbMenuItem);
		options.add(visualizationSubMenu);

		menuBar.add(options);
	}

	// UNIMPLEMENTED INTERFACE METHODS

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// Nothing to do here...
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// Nothing to do here...
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// Nothing to do here...
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Nothing to do here...
	}

}
