/**
 * 
 */
package com.ibm.katheder.map.generation;

import java.util.List;

import com.ibm.katheder.map.GeoMap;
import com.ibm.katheder.map.GeoMapBuilder;
import com.ibm.katheder.utils.FileInputUtils;

/**
 * @author Sterbling
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
		for(String line : mapFile) {
			String[] squares = line.split(FileInputUtils.CSV_SEPARATOR);
			for(String square : squares) {			
				mapBuilder.addGridSquare(Integer.valueOf(square));
			}
			mapBuilder.newLine();
		}
		// Parse the "metadata" - 0 mapping 1 name 2 weight
		for(String line : metaFile) {
			String[] csvRecord = line.split(FileInputUtils.CSV_SEPARATOR);
			mapBuilder.addNewTerrainType(Integer.valueOf(csvRecord[0]), csvRecord[1], Integer.valueOf(csvRecord[2]));
		}
		return mapBuilder.build();
	}

}
