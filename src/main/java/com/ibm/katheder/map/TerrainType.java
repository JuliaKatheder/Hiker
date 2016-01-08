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

import java.util.Comparator;

/**
 * @author Julia Katheder
 * @version 1.0
 *
 */
public class TerrainType {

	private int mappingId;

	private String name;

	private int weight;

	public TerrainType(int index, String name, int weight) {
		this.mappingId = index;
		this.name = name;
		this.weight = weight;
	}

	public int getMappingId() {
		return mappingId;
	}

	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}
	
	public static TerrainTypeWeightComparator getWeightComparator() {
		return new TerrainTypeWeightComparator();
	}
	
	private static class TerrainTypeWeightComparator implements Comparator<TerrainType>{

		@Override
		public int compare(TerrainType terrain1, TerrainType terrain2) {
			return terrain1.getWeight() - terrain2.getWeight();
		}
		
	}
	
}
