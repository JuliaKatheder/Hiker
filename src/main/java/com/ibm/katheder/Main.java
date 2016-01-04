package com.ibm.katheder;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import com.ibm.katheder.map.GeoMap;
import com.ibm.katheder.map.GeoPositionConverter;
import com.ibm.katheder.map.Hiker;
import com.ibm.katheder.map.MapPosition;
import com.ibm.katheder.map.generation.MapGenerator;
import com.ibm.katheder.map.generation.MapGeneratorFactory;
import com.ibm.katheder.pathfinding.PathFinding;
import com.ibm.katheder.view.Visualisation;

/**
 * Main class. Starts the visualization and routing logic.
 * 
 * TODO: In general proper error handling.
 * 
 * @author Julia Katheder
 *
 */
public final class Main {

	/** Main class shouldn't be instanciated.*/
	private Main() {
	};

	/**
	 * Main method for hitchhikers pathfinding problem.
	 * 
	 * @param args
	 *            Java input parameters
	 */
	public static void main(final String[] args) { // NOPMD

		final CommandLineArguments arguments = new CommandLineArguments();

		new JCommander(arguments, args);

		try {
			// Set the map generator
			final MapGenerator mapGenerator = MapGeneratorFactory
					.getMapGenerator(arguments.randomGenerator, arguments.mapfile);
			final GeoMap geoMap = mapGenerator.generateMap();
			// Add the Hiker and his destination on the map
			final MapPosition startPoint = arguments.startPosition;
			final MapPosition endPoint = arguments.endPosition;
			final Hiker hiker = new Hiker(geoMap, startPoint, endPoint);

			EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	// Start the GUI and wait for init.
	    			final Visualisation mapVisualisation = new Visualisation(hiker);
	    			mapVisualisation.setVisible(true);
	            }
	        });

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * <p>Simple input argument definitions. Allowed arguments are:
	 * <ul>
	 * <li>-mapfile -- path to mapfile csv-formatted</li>
	 * <li>-random -- Indicator if the program should start with a randomized map instead of a "handcrafted" one.</li>
	 * <li>-start -- Startposition for the Hiker</li>
	 * <li>-end -- Destination of the Hiker</li>
	 * </ul>
	 * </p>
	 * 
	 * @author Sventoni
	 * @version 1.0
	 */
	private static class CommandLineArguments {

		@Parameter(names = { "-mapfile", "-m" }, converter = FileConverter.class, 
				description = "The absolute path to the csv file.")
		public File mapfile;

		@Parameter(names = { "-random", "-r" },
				description = "Indicator if the map should be generated randomly. Default false.")
		public boolean randomGenerator = false;
		
		@Parameter(names = { "-start", "-s" }, converter = GeoPositionConverter.class,
				description = "StartPosition for hiker. Default: 14(y),2(x)")
		public MapPosition startPosition =  new MapPosition(14, 2);
		

		@Parameter(names = { "-end", "-e" }, converter = GeoPositionConverter.class,
				description = "Destination for hiker. Default: 1(y),9(x)")
		public MapPosition endPosition = new MapPosition(1, 9);

	}

}
