package com.ibm.katheder.map.generation;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Enumeration containing all possible terrains the hiker might travel trough.
 * Each terrain has a given "cost" of travel.
 * 
 * 0;Ebene;3
1;Fluss;-1
2;Weg;1
3;Wald;4
4;Brücke;3
5;Felswand;8
 * 
 * @author Sterbling
 *
 */
public enum TerrainTypes {
	TRAIL(2, 1), BRIDGE(4, 3), PLAIN(0, 3), WOOD(3, 4), ROCK(5, 8), WATER(1, Integer.MAX_VALUE);
	
	/** Enum with the lowest costs. */
	private final static TerrainTypes MINCOSTTYPE = Collections.min(Arrays.asList(TerrainTypes.values()), new TerrainTypeComparator());

	private final int mappingId;

	/** The weigt of traveling through the terrain. */
	private final int cost;

	private TerrainTypes(final int mappingId, final int cost) {
		this.mappingId = mappingId;
		this.cost = cost;
	}

	/**
	 * Get the unique ID of the element.
	 * @return
	 */
	public int getMappingId() {
		return mappingId;
	}

	/**
	 * Getter for the "costs" of traveling over the specific terrain.
	 * 
	 * @return the costs as {@link Integer}
	 */
	public int getCost() {
		return cost;
	}
	
	/**
	 * Returns the minimum cost from all terrain types.
	 * 
	 * @return the lowest cost as {@link Integer}
	 */
	public static int getMinCost() {
		return MINCOSTTYPE.getCost();
	}

	/**
	 * Comparator used to compare for lowest cost.
	 * 
	 * @author Sterbling
	 *
	 */
	private static class TerrainTypeComparator implements Comparator<TerrainTypes> {

		/*
		 * (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(final TerrainTypes type1,final  TerrainTypes type2) { // NOPMD
			return type1.getCost() - type2.cost;
		}
		
	}

}
