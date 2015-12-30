/**
 * 
 */
package com.ibm.katheder.view.color;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import com.ibm.katheder.map.generation.TerrainTypes;

/**
 * <p>
 * Static color mapping to show the map in a landscape mode. The used
 * TerrainTypes are matched by their Id in the {@link Colors} enum.
 * </p>
 * 
 * @author Sterbling
 * @version 1.0
 */
public class LandscapeStaticColorScheme implements ColorScheme {

	private Map<Integer, Color> terrainColors;

	public LandscapeStaticColorScheme() {
		this.terrainColors = new HashMap<>();
		for (Colors color : Colors.values()) {
			terrainColors.put(color.getKey(), color.getColor());
		}
	}

	@Override
	public Color getColorFor(int terrainTypeId) {
		return terrainColors.get(terrainTypeId);
	}

	@Override
	public Color getEleColor() {
		return Color.RED;
	}

	private enum Colors {
		/** Plain. */
		LIGHTGREEN(TerrainTypes.PLAIN.getMappingId(), 82, 255, 111),
		/** River. */
		BLUE(TerrainTypes.WATER.getMappingId(), 95, 191, 247),
		/** Trail. */
		LIGHTBROWN(TerrainTypes.TRAIL.getMappingId(), 255, 230, 170),
		/** Forest. */
		DARKGREEN(TerrainTypes.WOOD.getMappingId(), 14, 133, 42),
		/** Bridge. */
		DARKBROWN(TerrainTypes.BRIDGE.getMappingId(), 158, 142, 106),
		/** Mountain */
		GRAY(TerrainTypes.ROCK.getMappingId(), 53, 66, 66);

		private Colors(final int terrainId, final Integer red,
				final Integer green, final Integer blue) {
			this.terrainId = terrainId;
			this.red = red;
			this.green = green;
			this.blue = blue;
		}

		private final int terrainId;

		private final Integer red, green, blue;

		public int getKey() {
			return terrainId;
		}

		public Color getColor() {
			return new Color(red, green, blue);
		}
	}

}
