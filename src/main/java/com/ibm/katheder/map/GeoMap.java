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
package com.ibm.katheder.map;

import java.util.Map;

/**
 * <p>
 * Interface for a map that can be traveled through a {@link Hiker}. It
 * containts an int[][] Array that houlds a reference to an {@link TerrainType}
 * by a simple mapping ID.
 * </p>
 * 
 * @author Julia Katheder
 * @version 1.0
 *
 */
public interface GeoMap {

	/**
	 * Getter for the "map".
	 * 
	 * @return 2D-Array containing the mapping id to a {@link TerrainType}
	 */
	int[][] getMap();

	/**
	 * Getter for the "height" of the array. (Y-Axis)
	 * 
	 * @return the amount of rows
	 */
	int getHeight();

	/**
	 * Getter for the "width" of the array. (X-Axis)
	 * 
	 * @return the amount of columns
	 */
	int getWidth();

	/**
	 * Convenience method to get the costs for a field on the map.
	 * 
	 * @param y
	 *            coordinate for the row (the Y-Axis)
	 * @param x
	 *            coordinate for the column (the X-Axis)
	 * @return the weight/cost of the given map position as integer
	 */
	int getCost(int y, int x);

	/**
	 * Getter for the matching terrain type based on a coordinate on the map.
	 * 
	 * @param y
	 *            coordinate for the row (the Y-Axis)
	 * @param x
	 *            coordinate for the column (the X-Axis)
	 * @return the {@link TerrainType} on this coordinate
	 */
	TerrainType getFieldType(int y, int x);

	/**
	 * Getter for all terrain types contained in/defined for the map.
	 * 
	 * @return a map containing all terrain types. The key is the mapping id of the {@link TerrainType}
	 */
	Map<Integer, TerrainType> getTerrainTypes();

}
