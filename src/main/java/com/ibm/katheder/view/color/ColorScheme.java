/**
 * 
 */
package com.ibm.katheder.view.color;

import java.awt.Color;

import com.ibm.katheder.map.TerrainType;
import com.ibm.katheder.view.MapVisualization;

/**
 * <p>Interface for all classes providing a color scheme for the {@link MapVisualization}.</p>
 * 
 * @author Sventoni
 * @version 1.0
 *
 */
public interface ColorScheme {
	
	/**
	 * Current defined ColorSchemes.
	 * 
	 * @author Sventoni
	 *
	 */
	public static enum ColorSchemes {
		RGB, LANDSCAPE;
	}
	
	/**
	 * Getter for the Color of "special" Elements on the Map such as the Hiker and his destination.
	 * 
	 * @return the {@link Color}
	 * 
	 */
	Color getEleColor();

	/**
	 * Method to get the color for a specific {@link TerrainType} based on its id.
	 * 
	 * @param TerrainTypeId
	 * 		the id of a {@link TerrainType}
	 * @return the {@link Color}
	 */
	Color getColorFor(int TerrainTypeId);
	
}
