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
package com.ibm.katheder.map.generation;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * <p>
 * Enumeration containing all possible terrains the hiker might travel trough.
 * Each terrain has a given "cost" of travel.
 * </p>
 * 
 * @author Julia Katheder
 * @version 1.0
 *
 */
public enum StaticTerrainTypes {
	TRAIL(2, 1), BRIDGE(4, 3), PLAIN(0, 3), WOOD(3, 4), ROCK(5, 8), WATER(1,
			Integer.MAX_VALUE);

	/** Enum with the lowest costs. */
	private final static StaticTerrainTypes MINCOSTTYPE = Collections.min(
			Arrays.asList(StaticTerrainTypes.values()),
			new TerrainTypeComparator());

	private final int mappingId;

	/** The weigt of traveling through the terrain. */
	private final int cost;

	private StaticTerrainTypes(final int mappingId, final int cost) {
		this.mappingId = mappingId;
		this.cost = cost;
	}

	/**
	 * Get the unique ID of the element.
	 * 
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
	 * @author Julia Katheder
	 *
	 */
	private static class TerrainTypeComparator implements
			Comparator<StaticTerrainTypes> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(final StaticTerrainTypes type1,
				final StaticTerrainTypes type2) { // NOPMD
			return type1.getCost() - type2.cost;
		}

	}

}
