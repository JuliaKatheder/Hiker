/**
 * 
 */
package com.ibm.katheder.view;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JComponent;

import com.ibm.katheder.map.Hiker;
import com.ibm.katheder.map.MapPosition;
import com.ibm.katheder.view.color.ColorScheme;
import com.ibm.katheder.view.color.LandscapeStaticColorScheme;
import com.ibm.katheder.view.color.ColorScheme.ColorSchemes;
import com.ibm.katheder.view.color.RGBDynamicColorScheme;

/**
 * @author Sterbling
 *
 */
public class MapVisulalization extends JComponent implements MouseListener{
	
	private static final long serialVersionUID = 751161800519505699L;

	private final int unit = 30;

	private Hiker hiker;
	private ColorScheme colorScheme;
	
	private int screenWidth;
	
	public MapVisulalization(Hiker hiker){
		this.hiker = hiker;
		
		this.colorScheme = new LandscapeStaticColorScheme();
		
		this.screenWidth = hiker.getGeoMap().getSize()*unit;
					
		this.setSize(this.screenWidth, this.screenWidth);
		this.addMouseListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int originX = 0;
		int originY = 0;
		
		System.out.println("Painting the awesome map...");
		
		for(int i=0; i< hiker.getGeoMap().getSize();i++){
			for(int j=0; j< hiker.getGeoMap().getSize();j++){
				
				g.setColor(colorScheme.getColorFor(hiker.getGeoMap().getFieldType(i, j).getMappingId()));
				g.fillRect(originX + this.unit*i, originY + this.unit*j, this.unit, this.unit);
				
			}
		}
		
		g.setColor(colorScheme.getEleColor());
		g.fillOval(originX + hiker.getPositionX()*this.unit, originY + hiker.getPositionY()*this.unit, this.unit, this.unit);
		g.fillRect(originX + hiker.getDestinationX()*this.unit, originY + hiker.getDestinationY()*this.unit, this.unit, this.unit);
		
	}
	
	public void setColorScheme(ColorSchemes landscape) {
		final ColorScheme newScheme;
		
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

	@Override
	public void mouseClicked(MouseEvent e) {
		
		int positionX = e.getX()/this.unit;
		int positionY = (e.getY())/this.unit;
		
		System.out.println(positionX);
		System.out.println(positionY);
		hiker.setDestination(new MapPosition(positionX, positionY));
		
		repaint();
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

