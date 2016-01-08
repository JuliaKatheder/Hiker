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
 * Container for the map which is the model for pathfinding and visualisation.
 * Each part of the map is represented by a reference to a {@link TerrainType}.
 * </p>
 * 
 * @author Julia Katheder
 * @version 1.0
 *
 */
public class HikingMap implements GeoMap {

	private Map<Integer, TerrainType> terrainTypes;

	/**
	 * Map containing the terraintypeid. First value corresponds to Y-Axis
	 * second value to X-Axis.
	 */
	private int[][] map;

	public HikingMap(final int[][] map,
			final Map<Integer, TerrainType> terrainTypes) {
		this.map = map;
		this.terrainTypes = terrainTypes;
	}

	public int[][] getMap() {
		return map;
	}

	public int getHeight() {
		return this.map.length;
	}

	public int getWidth() {
		return this.map[0].length;
	}

	public int getCost(int y, int x) {
		return getFieldType(y, x).getWeight();
	}

	public TerrainType getFieldType(int y, int x) {
		return terrainTypes.get(map[y][x]);
	}

	public Map<Integer, TerrainType> getTerrainTypes() {
		return terrainTypes;
	}

}
