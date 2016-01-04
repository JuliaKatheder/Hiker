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
 * <p>
 * Dynamic RGB Color Scheme Generator for terrainTypes. Has a color range from
 * green over yellow to red. The lowest "cost" {@link TerrainType} is marked in
 * green the highest "cost" {@link TerrainType} is marked in red.
 * </p>
 * <p>
 * NOTE: There is some additional logic build in for the case of "unreachable"
 * {@link TerrainType} which are indicated by the value of Integer.MAX_VALUE.
 * </p>
 * 
 * @author Sventoni
 * @version 1.0
 *
 */
public class RGBDynamicColorScheme implements ColorScheme {

	/** Color Green. */
	private final Color from_color = new Color(0, 255, 0);

	/** Color Red. */
	private final Color to_color = new Color(255, 0, 0);

	private Map<Integer, Color> terrainColors;

	public RGBDynamicColorScheme(final List<TerrainType> terrainTypes) {
		this.terrainColors = new HashMap<>();
		Collections.sort(terrainTypes, TerrainType.getWeightComparator());
		mapColors(terrainTypes);
	}

	private void mapColors(final List<TerrainType> terrainTypes) {
		final int amountTypes = terrainTypes.size();
		final int minWeight = terrainTypes.get(0).getWeight();
		final int maxWeight = sanitizeMaxWeight(terrainTypes);
		final int maxDiff = maxWeight - minWeight;
		for (int indx = 0; indx < amountTypes; indx++) {
			final int terrainTypeId = terrainTypes.get(indx).getMappingId();
			final int terrainTypeWeight = terrainTypes.get(indx).getWeight();
			if (terrainTypeWeight == Integer.MAX_VALUE) {
				terrainColors.put(terrainTypeId, to_color);
				continue;
			}
			final float relativeWeight = (float) (terrainTypeWeight - minWeight)
					/ maxDiff;
			final int red;
			final int green;
			// if we are below 0.5 turn up the red value...
			if (relativeWeight <= 0.5) {
				red = (int) (255 * relativeWeight * 2);
				green = from_color.getGreen();
			} else {
			// Otherwise the green value
				red = to_color.getRed();
				green = (int) (255 * (1 - relativeWeight) * 2);
			}
			terrainColors.put(terrainTypeId, new Color(red, green, 0));
		}
	}

	/**
	 * Method that returns the maximum value which is below Integer.MAX_VALUE.
	 *  
	 * @param terrainTypes
	 * 			the list of all TerrainTypes
	 * @return the max weight of the TerrainTypes
	 */
	private int sanitizeMaxWeight(List<TerrainType> terrainTypes) {
		TerrainType type = terrainTypes.get(terrainTypes.size() - 1);
		if (type.getWeight() == Integer.MAX_VALUE) {
			for (int indx = terrainTypes.size() - 1; indx >= 0; indx--) {
				type = terrainTypes.get(indx);
				if (type.getWeight() != Integer.MAX_VALUE) {
					return (int) (type.getWeight() + Math.ceil((double) type
							.getWeight() * 0.1));
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
