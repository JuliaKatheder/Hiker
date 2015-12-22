/**
 * 
 */
package com.ibm.katheder.map.generation;

import com.ibm.katheder.map.GeoMap;

/**
 * Interface for creation of the map used for finding the shortest path.
 * 
 * @author Sterbling
 *
 */
public interface MapGenerator {

	/**
	 * Generates the map from the given inputs.
	 * 
	 * @return {@link GeoMap}
	 */
	GeoMap generateMap();
}
