package com.ibm.katheder.map;

import java.util.Map;

public interface GeoMap {

	int[][] getMap();
	
	int getSize();
	
	int getCost(int x, int y);
	
	TerrainType getFieldType(int x, int y);

	Map<Integer, TerrainType> getTerrainTypes();
	
}
