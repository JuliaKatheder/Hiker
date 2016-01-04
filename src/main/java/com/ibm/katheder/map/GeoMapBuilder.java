/**
 * 
 */
package com.ibm.katheder.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sventoni
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
