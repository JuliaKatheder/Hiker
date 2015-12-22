/**
 * 
 */
package com.ibm.katheder.map;

/**
 * @author Sterbling
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
	
}
