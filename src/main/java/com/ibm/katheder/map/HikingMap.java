package com.ibm.katheder.map;

import java.util.Map;


/**
 * Container for the map which is the model for pathfinding and visualisation. 
 * Each part of the map is represented by a reference to a {@link TerrainType}.
 * 
 * @author Sterbling
 *
 */
public class HikingMap implements GeoMap {
	
	private Map<Integer, TerrainType> terrainTypes;
	
	private int[][] map;
		
	public HikingMap(final int[][] map, final Map<Integer, TerrainType> terrainTypes) {
		this.map = map;
		this.terrainTypes = terrainTypes;
	}
		
	public int[][] getMap() {
		return map;
	}
	
	public int getSize(){
		return this.map.length;
	}
	
	public int getCost(int x, int y) {
		return getFieldType(x, y).getWeight();
	}
	
	public TerrainType getFieldType(int x, int y) {
		return terrainTypes.get(map[x][y]);
	}

	public Map<Integer, TerrainType> getTerrainTypes() {
		return terrainTypes;
	}
	
	
	
}
