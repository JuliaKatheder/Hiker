/**
 * 
 */
package com.ibm.katheder.map;

import java.util.Comparator;

/**
 * @author Sventoni
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
