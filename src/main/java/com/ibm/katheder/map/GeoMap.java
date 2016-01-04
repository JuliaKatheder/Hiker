package com.ibm.katheder.map;

import java.util.Map;

public interface GeoMap {

	int[][] getMap();
	
	int getHeight();
	
	int getWidth();
	
	int getCost(int y, int x);
	
	TerrainType getFieldType(int y, int x);

	Map<Integer, TerrainType> getTerrainTypes();
	
}
