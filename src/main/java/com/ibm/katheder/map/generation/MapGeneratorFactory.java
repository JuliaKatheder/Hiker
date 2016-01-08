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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;
import com.ibm.katheder.utils.FileInputUtils;

/**
 * <p>
 * </p>
 * 
 * @author Julia Katheder
 * @version 1.0
 *
 */
public class MapGeneratorFactory {

	private static final Charset CHARSET = Charsets.ISO_8859_1;

	public static MapGenerator getMapGenerator(final boolean random,
			final File mapfile) throws IOException {
		final MapGenerator mapGenerator;
		if (random) {
			mapGenerator = new RandomMapGenerator();
		} else {
			List<String> mapData;
			List<String> metaData;
			if (mapfile != null && Files.isFile().apply(mapfile)) {
				List<String> inputLines = Files.readLines(mapfile, CHARSET);
				mapData = FileInputUtils.getMapData(inputLines);
				List<String> metaDataRaw = FileInputUtils
						.getMetaData(inputLines);
				metaData = FileInputUtils.sanitizeMetaData(metaDataRaw);
			} else {
				mapData = loadResource("map.csv");
				metaData = loadResource("cost.csv");
			}
			mapGenerator = new CSVMapGenerator(mapData, metaData);
		}
		return mapGenerator;
	}

	private static List<String> loadResource(final String resourceName)
			throws IOException {
		final ClassLoader cl = ClassLoader.getSystemClassLoader();
		final InputStream inputStream = cl.getResourceAsStream(resourceName);
		return CharStreams
				.readLines(new InputStreamReader(inputStream, CHARSET));
	}

}
