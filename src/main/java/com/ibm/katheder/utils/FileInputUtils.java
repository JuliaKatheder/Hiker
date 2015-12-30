/**
 * 
 */
package com.ibm.katheder.utils;

import java.util.LinkedList;
import java.util.List;

import com.sun.org.apache.bcel.internal.classfile.Code;

/**
 * <p>
 * Helper class to parse the required information about the map from a single
 * file. Separates the description of the Terrain from the Metadata about the
 * terraintypes.
 * </p>
 * 
 * @author Sterbling
 *
 */
public class FileInputUtils {

	public static String CSV_SEPARATOR = ";";

	/**
	 * Get only the part of the file that represents the map.
	 * 
	 * @param inputLines
	 *            the whole parsed csv-file
	 * @return the first X lines of the inputLines representing the map
	 */
	public static List<String> getMapData(List<String> inputLines) {
		for (int indx = 0; indx < inputLines.size(); indx++) {
			String line = inputLines.get(indx);
			String[] csvRecord = line.split(CSV_SEPARATOR);
			if (csvRecord.length == 0) {
				return inputLines.subList(0, indx);
			}
		}
		return new LinkedList<String>();
	}

	/**
	 * Get only the part of the file that represents the metadata about the
	 * terrain types.
	 * 
	 * @param inputLines
	 *            the whole parsed csv-file
	 * @return the last x lines of the inputLines representing the metadata
	 */
	public static List<String> getMetaData(List<String> inputLines) {
		for (int indx = 0; indx < inputLines.size(); indx++) {
			String line = inputLines.get(indx);
			String[] csvRecord = line.split(CSV_SEPARATOR);
			if (csvRecord.length == 0) {
				return inputLines.subList(indx, inputLines.size());
			}
		}
		return new LinkedList<String>();
	}

	/**
	 * Sanitize the metadata from hard to use input formats such as
	 * "unüberwindbar".
	 * 
	 * @param metaDataLines
	 *            only the metadata lines
	 * @return the sanitized metaDataLines
	 */
	public static List<String> sanitizeMetaData(final List<String> metaDataLines) {
		List<String> result = new LinkedList<>();
		for (String line : metaDataLines) {
			String[] csvRecord = line.split(CSV_SEPARATOR);
			if (csvRecord.length == 0) {
				continue;
			} else if (!csvRecord[0].matches("[0-9]*")) {
				continue;
			} else {
				result.add(sanitizeLine(line));
			}
		}
		return result;
	}

	/**
	 * <p>
	 * Sanitize a single line of the "meta data".
	 * </p>
	 * <p>
	 * For Id (1 Element in line): This method replaces any String which can't
	 * be casted to an {@link Integer} to {@link Code -1}.
	 * </p>
	 * <p>
	 * For Name (2 Element in line): No check implemented.
	 * </p>
	 * <p>
	 * For Weight (3 Element in line): This method replaces any String which
	 * can't be casted to an {@link Integer} to {@link Code -1}.
	 * </p>
	 * 
	 * @param line
	 *            one single line of the metadata
	 * @return the sanitized string
	 */
	private static String sanitizeLine(String line) {
		final StringBuilder result = new StringBuilder();
		final String[] lineRecord = line.split(CSV_SEPARATOR);

		try {
			Integer.valueOf(lineRecord[0]);
			result.append(lineRecord[0]);
		} catch (NumberFormatException e) {
			result.append(-1);
		}
		result.append(CSV_SEPARATOR);
		result.append(lineRecord[1]);
		result.append(CSV_SEPARATOR);
		try {
			Integer.valueOf(lineRecord[2]);
			result.append(lineRecord[2]);
		} catch (NumberFormatException e) {
			result.append(-1);
		}
		return result.toString();
	}

}
