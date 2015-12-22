package com.ibm.katheder.map;

import java.util.List;
import java.util.Map;

import com.ibm.katheder.Hiker;
import com.ibm.katheder.map.generation.TerrainTypes;


/**
 * Container for the map which is the model for pathfinding and visualisation. 
 * Each part of the map is represented by a {@link TerrainTypes}.
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
	
	public TerrainType getFieldType(int x, int y) {
		return terrainTypes.get(map[x][y]);
	}
	
}
