/**
 * 
 */
package com.ibm.katheder.map.generation;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.ibm.katheder.utils.FileInputUtils;

/**
 * @author Sterbling
 *
 */
public class MapGeneratorFactory {
	
	private static final Charset CHARSET = Charsets.ISO_8859_1;
	
	public static MapGenerator getMapGenerator(final boolean random, final File mapfile) throws IOException, URISyntaxException {
		final MapGenerator mapGenerator;
		if(random) {
			mapGenerator = new RandomMapGenerator();
		} else {
			List<String> mapData;
			List<String> metaData;
			if(mapfile != null && Files.isFile().apply(mapfile)) {
				List<String> inputLines = Files.readLines(mapfile, CHARSET);
				mapData = FileInputUtils.getMapData(inputLines);					
				List<String> metaDataRaw = FileInputUtils.getMetaData(inputLines);
				metaData = FileInputUtils.sanitizeMetaData(metaDataRaw);
			} else {
				mapData = Files.readLines(loadResource("map.csv"), CHARSET);
				metaData = Files.readLines(loadResource("cost.csv"), CHARSET);
			}
			mapGenerator = new CSVMapGenerator(mapData, metaData);
		}
		return mapGenerator;
	}

    private static File loadResource(final String resourceName) throws URISyntaxException {
        File resourceFile = null;
        // Setup the test file from source
        final ClassLoader cl = ClassLoader.getSystemClassLoader();
        final URL fileURL = cl.getResource(resourceName);

        resourceFile = new File(fileURL.toURI());
        return resourceFile;
    }
	
}
