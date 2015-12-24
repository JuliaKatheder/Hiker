/**
 * 
 */
package com.ibm.katheder.view.color;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.katheder.map.TerrainType;

/**
 * Dynamic Color Scheme Generator for terrainTypes
 * 
 * @author Sterbling
 *
 */
public class RGBDynamicColorScheme implements ColorScheme {
	
	private final Color from_color = new Color(0, 255, 0);
	
	private final Color to_color = new Color(255, 0, 0);

	private Map<Integer, Color> terrainColors;
	
	public RGBDynamicColorScheme(final List<TerrainType> terrainTypes) {
		this.terrainColors = new HashMap<>();
		mapColors(terrainTypes);
	}
	
	private void mapColors(final List<TerrainType> terrainTypes) {
		final int amountTypes = terrainTypes.size();
		Collections.sort(terrainTypes, TerrainType.getWeightComparator());
		final int minWeight = terrainTypes.get(0).getWeight();
		final int maxWeight = sanitizeMaxWeight(terrainTypes);
		for(int indx = 0; indx < amountTypes; indx++) {
			final int terrainTypeId = terrainTypes.get(indx).getMappingId();
			final int terrainTypeWeight = terrainTypes.get(indx).getWeight();
			if(terrainTypeWeight == Integer.MAX_VALUE) {
				terrainColors.put(terrainTypeId, to_color);
				continue;
			}
			final float relativeWeight = (float) (terrainTypeWeight - minWeight) / (maxWeight - minWeight);
			final int red;
			final int green;
			if(relativeWeight <= 0.5) {
				red = (int) (255 * relativeWeight * 2);
				green = from_color.getGreen();
			} else {
				red = to_color.getRed();
				green = (int) (255 * (1 - relativeWeight) * 2);
			}
			terrainColors.put(terrainTypeId, new Color(red, green, 0));
		}
	}
	
	private int sanitizeMaxWeight(List<TerrainType> terrainTypes) {
		TerrainType type = terrainTypes.get(terrainTypes.size()-1);
		if(type.getWeight() == Integer.MAX_VALUE) {
			for(int indx = terrainTypes.size() - 1; indx >= 0; indx--) {
				type = terrainTypes.get(indx);
				if(type.getWeight() != Integer.MAX_VALUE) {
					return (int) (type.getWeight() + Math.ceil((double)type.getWeight() * 0.1));
				}
			}
		}
		return type.getWeight();		
	}
	
	@Override
	public Color getColorFor(int TerrainTypeId) {
		return terrainColors.get(TerrainTypeId);
	}
	
	@Override
	public Color getEleColor() {
		return Color.BLUE;
	}

}
