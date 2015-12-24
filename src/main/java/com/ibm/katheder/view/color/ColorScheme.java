/**
 * 
 */
package com.ibm.katheder.view.color;

import java.awt.Color;

/**
 * @author Sterbling
 *
 */
public interface ColorScheme {
	
	public static enum ColorSchemes {
		RGB, LANDSCAPE;
	}
	
	Color getEleColor();

	Color getColorFor(int TerrainTypeId);
	
}
