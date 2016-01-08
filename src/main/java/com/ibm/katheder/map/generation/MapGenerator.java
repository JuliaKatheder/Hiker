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
package com.ibm.katheder.map.generation;

import com.ibm.katheder.map.GeoMap;

/**
 * <p>
 * Interface for creation of the map used for finding the shortest path.
 * </p>
 * 
 * @author Julia Katheder
 * @version 1.0
 *
 */
public interface MapGenerator {

	/**
	 * Generates the map from the given inputs.
	 * 
	 * @return {@link GeoMap}
	 */
	GeoMap generateMap();
}
