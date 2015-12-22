package com.ibm.katheder.map.generation;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Enumeration containing all possible terrains the hiker might travel trough.
 * Each terrain has a given "cost" of travel.
 * 
 * @author Sterbling
 *
 */
public enum TerrainTypes {
	TRAIL(1), BRIDGE(3), PLAIN(3), WOOD(4), ROCK(8), WATER(Integer.MAX_VALUE);
	
	/** Enum with the lowest costs. */
	private final static TerrainTypes MINCOSTTYPE = Collections.min(Arrays.asList(TerrainTypes.values()), new TerrainTypeComparator());


	/** The weigt of traveling through the terrain. */
	private final int cost;

	private TerrainTypes(final int cost) {
		this.cost = cost;
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
