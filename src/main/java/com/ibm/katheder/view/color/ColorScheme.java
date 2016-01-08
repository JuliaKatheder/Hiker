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
package com.ibm.katheder.view.color;

import java.awt.Color;

import com.ibm.katheder.map.TerrainType;
import com.ibm.katheder.view.MapVisualization;

/**
 * <p>
 * Interface for all classes providing a color scheme for the
 * {@link MapVisualization}.
 * </p>
 * 
 * @author Julia Katheder
 * @version 1.0
 *
 */
public interface ColorScheme {

	/**
	 * Current defined ColorSchemes.
	 * 
	 * @author Julia Katheder
	 *
	 */
	public static enum ColorSchemes {
		RGB, LANDSCAPE;
	}

	/**
	 * Getter for the Color of "special" Elements on the Map such as the Hiker
	 * and his destination.
	 * 
	 * @return the {@link Color}
	 * 
	 */
	Color getEleColor();

	/**
	 * Method to get the color for a specific {@link TerrainType} based on its
	 * id.
	 * 
	 * @param TerrainTypeId
	 *            the id of a {@link TerrainType}
	 * @return the {@link Color}
	 */
	Color getColorFor(int TerrainTypeId);

}
