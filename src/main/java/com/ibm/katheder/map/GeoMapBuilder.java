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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Julia Katheder
 * @version 1.0
 *
 */
public class GeoMapBuilder {
	
	private List<Integer> square;

	private List<List<Integer>> maprows;
	
	private Map<Integer, TerrainType> terrainTypes;
	
	public GeoMapBuilder() {
		this.square = new ArrayList<>();
		this.maprows = new ArrayList<>();
		this.terrainTypes = new HashMap<>();
	}
	
	public void addGridSquare(int value) {
		if(value < 0) {
			square.add(Integer.MAX_VALUE);
		} else {
			square.add(value);
		}		
	}

	public void newLine() {
		maprows.add(square);
		square = new ArrayList<Integer>();
	}

	public void addNewTerrainType(int index, String name, int weight) {
		if(weight < 0) {
			weight = Integer.MAX_VALUE;
		}
		terrainTypes.put(index, new TerrainType(index, name, weight));
		
	}

	public GeoMap build() {
		
		final int[][] map = new int[maprows.get(0).size()][maprows.size()];
		
		for(int rowindx = 0; rowindx < maprows.size(); rowindx++) {
			final List<Integer> columns = maprows.get(rowindx);
			for(int colindx = 0; colindx < columns.size(); colindx++) {
				map[rowindx][colindx] = columns.get(colindx);
			}
		}
		
		return new HikingMap(map, terrainTypes);
	}

}
