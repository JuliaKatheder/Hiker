/**
 * 
 */
package com.ibm.katheder.utils;

import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Strings;

/**
 * @author Sterbling
 *
 */
public class FileInputUtils {
	
	public static String CSV_SEPARATOR = ";";
	
	public static List<String> getMapData(List<String> inputLines) {
		for(int indx = 0; indx < inputLines.size(); indx++) {
			String line = inputLines.get(indx);
			String[] csvRecord = line.split(CSV_SEPARATOR);
			if(Strings.isNullOrEmpty(csvRecord[0])) {
				return inputLines.subList(0, indx+1);
			}
		}
		return new LinkedList<String>();
	}
	
	public static List<String> getMetaData(List<String> inputLines) {
		for(int indx = 0; indx < inputLines.size(); indx++) {
			String line = inputLines.get(indx);
			String[] csvRecord = line.split(CSV_SEPARATOR);
			if(Strings.isNullOrEmpty(csvRecord[0])) {
				return inputLines.subList(indx, inputLines.size());
			}
		}
		return new LinkedList<String>();
	}
	
	public static List<String> sanitizeMetaData(List<String> metaDataLines) {
		List<String> result = new LinkedList<>();
		for(String line : metaDataLines) {
			String[] csvRecord = line.split(CSV_SEPARATOR);
			if(Strings.isNullOrEmpty(csvRecord[0])) {
				continue;
			} else if(!csvRecord[0].matches("[0-9]*")) {
				continue;
			} else {
				result.add(line.replaceAll("["+CSV_SEPARATOR+"]{1,*}", ""));
			}
		}
		return result;
	}

}
