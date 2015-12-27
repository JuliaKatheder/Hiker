/**
 * 
 */
package com.ibm.katheder.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import com.ibm.katheder.map.GeoMap;
import com.ibm.katheder.map.Hiker;
import com.ibm.katheder.map.MapPosition;
import com.ibm.katheder.map.generation.RandomMapGenerator;
import com.ibm.katheder.view.action.RandomMapAction;
import com.ibm.katheder.view.color.ColorScheme;
import com.ibm.katheder.view.color.ColorScheme.ColorSchemes;
import com.ibm.katheder.view.color.LandscapeStaticColorScheme;
import com.ibm.katheder.view.color.RGBDynamicColorScheme;

/**
 * <p>Visualization component for a {@link Hiker}. Uses a {@link GeoMap} to draw the landscape and places
 * the Hikers {@link MapPosition} and his destination (as {@link MapPosition}) to the Canvas.</p>
 * <p>Furthermore it adds a Menu Tab to the MenuBar with actions to generate a random map via {@link RandomMapGenerator}
 * and change the color theme to RGB ({@link RGBDynamicColorScheme}) or Landscape ({@link LandscapeStaticColorScheme}).</p>
 * 
 * @author Sterbling
 * @version 1.0
 */
public class MapVisulalization extends JComponent implements MouseListener{
	
	private static final long serialVersionUID = 751161800519505699L;
	/** Size of an element in the canvas in pixels. */
	private static final int unit = 30;

	/** Screen width and height in pixels. */
	private int screenHeight;
	private int screenWidth;

	/** The colorscheme used for painting. */
	private ColorScheme colorScheme;
	
	private Hiker hiker;
	
	/**
	 * Constructor. The default colorScheme is the {@link LandscapeStaticColorScheme}.
	 * 
	 * @param menuBar
	 * 		the menubar which is displayed on top of the frame. 
	 * 		Add here possible menu options for the canvas.
	 * @param hiker
	 * 		see {@link Hiker}. Represents the Model for this view.
	 */
	public MapVisulalization(JMenuBar menuBar, Hiker hiker){
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
		for(int rowIndx=0; rowIndx< screenHeight;rowIndx++){
			for(int colIndx=0; colIndx< screenWidth;colIndx++){
				final int mappingId = hiker.getGeoMap().getFieldType(rowIndx, colIndx).getMappingId();
				g.setColor(colorScheme.getColorFor(mappingId));
				g.fillRect(unit*rowIndx, unit*colIndx, unit, unit);
				
			}
		}
		// Add the hiker and his destination
		g.setColor(colorScheme.getEleColor());
		g.fillOval(hiker.getPosX() * unit, hiker.getPosY() * unit, unit, unit);
		g.fillRect(hiker.getDestX() * unit, hiker.getDestY() * unit, unit, unit);
		
	}
	
	public void setColorScheme(ColorSchemes landscape) {
		final ColorScheme newScheme;
		// TODO this could be in a builder or sth...
		switch (landscape) {
		case RGB:
			newScheme = new RGBDynamicColorScheme(new LinkedList<>(hiker.getGeoMap().getTerrainTypes().values()));
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
	
	private void setScreenDimensions() {
		this.screenHeight = hiker.getGeoMap().getHeight();
		this.screenWidth = hiker.getGeoMap().getWidth();
		this.setPreferredSize(new Dimension(screenWidth * unit, screenHeight * unit));
	}

	private void addMenuTab(JMenuBar menuBar) {
		// Add Options Tab
		final JMenu options = new JMenu("Map");
		options.setMnemonic(KeyEvent.VK_O);
		options.getAccessibleContext().setAccessibleDescription("Map options menu");
		// Add Show/Hide Path Option
		final JMenuItem menuItem = new JMenuItem("RANDOM!",
		                KeyEvent.VK_R);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		KeyEvent.VK_R, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This option switches between the different visualization formats.");
		menuItem.addActionListener( new RandomMapAction(this)); 
		options.add(menuItem);
		options.addSeparator();
		// Add visualization options
		final JMenu visualizationSubMenu = new JMenu("Visualization Options"); 
		final ButtonGroup visualizationGroup = new ButtonGroup();
		JMenuItem rbMenuItem = new JRadioButtonMenuItem("Landscape");
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_R);
		rbMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setColorScheme(ColorSchemes.LANDSCAPE);				
			}
		});
		visualizationGroup.add(rbMenuItem);
		visualizationSubMenu.add(rbMenuItem);
		rbMenuItem = new JRadioButtonMenuItem("RGB Scheme");
		rbMenuItem.setMnemonic(KeyEvent.VK_O);
		rbMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setColorScheme(ColorSchemes.RGB);				
			}
		});
		visualizationGroup.add(rbMenuItem);
		visualizationSubMenu.add(rbMenuItem);
		options.add(visualizationSubMenu);
		
		menuBar.add(options);
	}
	
	private boolean isAlreadyOneClick;
	
	private Timer doubleClickTimer = new Timer("doubleclickTimer", false);

	
	/**
	 * On the click event the destination of the hiker can be changed.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		final MapVisulalization vis = this;
	   final int positionX = e.getX() / unit;
	   final int positionY = e.getY() / unit;
	   // TODO some validation logic to prevent placing hiker or dest on unreachable terrain.
	   if (isAlreadyOneClick) {
          isAlreadyOneClick = false;		
          hiker.setPosition(new MapPosition(positionX, positionY));
          repaint();
	   } else {
		  isAlreadyOneClick = true;
	      doubleClickTimer.schedule(new TimerTask() {
		      @Override
		      public void run() {
		    	  if(isAlreadyOneClick) {
		    		  hiker.setDestination(new MapPosition(positionX, positionY));
		    		  vis.repaint();
		          }
		          isAlreadyOneClick = false;
		      }
	      }, 250);
	    }
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

