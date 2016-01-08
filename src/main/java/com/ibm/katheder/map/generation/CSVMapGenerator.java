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

import java.util.List;

import com.ibm.katheder.map.GeoMap;
import com.ibm.katheder.map.GeoMapBuilder;
import com.ibm.katheder.utils.FileInputUtils;

/**
 * <p>
 * </p>
 * 
 * @author Julia Katheder
 * @version 1.0
 *
 */
public class CSVMapGenerator implements MapGenerator {

	private final List<String> mapFile;

	private final List<String> metaFile;

	public CSVMapGenerator(List<String> mapfile, List<String> metafile) {
		this.mapFile = mapfile;
		this.metaFile = metafile;
	}

	@Override
	public GeoMap generateMap() {
		final GeoMapBuilder mapBuilder = new GeoMapBuilder();
		// "Parse" the map
		for (String line : mapFile) {
			String[] squares = line.split(FileInputUtils.CSV_SEPARATOR);
			for (String square : squares) {
				mapBuilder.addGridSquare(Integer.valueOf(square));
			}
			mapBuilder.newLine();
		}
		// Parse the "metadata" - 0 mapping 1 name 2 weight
		for (String line : metaFile) {
			String[] csvRecord = line.split(FileInputUtils.CSV_SEPARATOR);
			mapBuilder.addNewTerrainType(Integer.valueOf(csvRecord[0]),
					csvRecord[1], Integer.valueOf(csvRecord[2]));
		}
		return mapBuilder.build();
	}

}
