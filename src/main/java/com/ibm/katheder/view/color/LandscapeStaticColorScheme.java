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
package com.ibm.katheder.view.color;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import com.ibm.katheder.map.generation.StaticTerrainTypes;

/**
 * <p>
 * Static color mapping to show the map in a landscape mode. The used
 * TerrainTypes are matched by their Id in the {@link Colors} enum.
 * </p>
 * 
 * @author Julia Katheder
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
		LIGHTGREEN(StaticTerrainTypes.PLAIN.getMappingId(), 82, 255, 111),
		/** River. */
		BLUE(StaticTerrainTypes.WATER.getMappingId(), 95, 191, 247),
		/** Trail. */
		LIGHTBROWN(StaticTerrainTypes.TRAIL.getMappingId(), 255, 230, 170),
		/** Forest. */
		DARKGREEN(StaticTerrainTypes.WOOD.getMappingId(), 14, 133, 42),
		/** Bridge. */
		DARKBROWN(StaticTerrainTypes.BRIDGE.getMappingId(), 158, 142, 106),
		/** Mountain */
		GRAY(StaticTerrainTypes.ROCK.getMappingId(), 53, 66, 66);

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
