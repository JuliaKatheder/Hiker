/**
 * 
 */
package com.ibm.katheder.map;

import com.beust.jcommander.IStringConverter;

/**
 * <p>
 * Explicit converter for command line arguments based on the JCommander
 * Framework. Parses the given inputstring of format: <br/>
 * {@code x-coord,y-coord} into a {@link MapPosition}.
 * </p>
 * 
 * @author Sterbling
 * @version 1.0
 *
 */
public class GeoPositionConverter implements IStringConverter<MapPosition> {

	@Override
	public MapPosition convert(String value) {
		final String[] coords = value.split(",");
		final int coordX = Integer.valueOf(coords[0]);
		final int coordY = Integer.valueOf(coords[1]);
		return new MapPosition(coordX, coordY);
	}

}
