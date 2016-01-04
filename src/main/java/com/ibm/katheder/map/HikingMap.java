package com.ibm.katheder.map;

import java.util.Map;

/**
 * Container for the map which is the model for pathfinding and visualisation.
 * Each part of the map is represented by a reference to a {@link TerrainType}.
 * 
 * @author Sventoni
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
