package com.ibm.katheder;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JMenuBar;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import com.ibm.katheder.map.GeoMap;
import com.ibm.katheder.map.Hiker;
import com.ibm.katheder.map.MapPosition;
import com.ibm.katheder.map.generation.MapGenerator;
import com.ibm.katheder.map.generation.MapGeneratorFactory;
import com.ibm.katheder.view.Visualisation;

/**
 * Main class. Starts the visualization and routing logic.
 * 
 * @author Julia Katheder
 *
 */
public final class Main { // NOPMD

	private Main() {
	};

	/**
	 * Main method for hitchhikers pathfinding problem.
	 * 
	 * @param args
	 *            Java input parameters
	 */
	public static void main(final String[] args) { // NOPMD

		CommandLineArguments arguments = new CommandLineArguments();

		new JCommander(arguments, args);

		try {
			// Set the map generator
			final MapGenerator mapGenerator = MapGeneratorFactory
					.getMapGenerator(arguments.randomGenerator, arguments.mapfile);
			final GeoMap geoMap = mapGenerator.generateMap();
			// TODO Hiker & Map things
			final MapPosition startPoint = new MapPosition(2, 14);
			final MapPosition endPoint = new MapPosition(9, 1);
			final Hiker hiker = new Hiker(geoMap, startPoint, endPoint);
			
			
			EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	    			final Visualisation mapVisualisation = new Visualisation(hiker);
	    			mapVisualisation.setVisible(true);
	            }
	        });

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// final HikingMap map = new HikingMap(20);
		// RandomMapGenerator.generateMap(map.getMap());

		// Visualisation vis = new Visualisation(map);

		// final PathFinding pathFinding = new PathFinding(map);
		// pathFinding.findPath();

	}

	/**
	 * Simple argument definitions.
	 * 
	 * @author Sterbling
	 *
	 */
	private static class CommandLineArguments {

		@Parameter(names = { "-mapfile", "-m" }, converter = FileConverter.class, description = "The absolute path to the csv file")
		public File mapfile;

		@Parameter(names = { "-random", "-r" }, description = "Indicator if the map should be generated randomly. Default false.")
		public boolean randomGenerator = false;

	}

}
