/**
 * 
 */
package com.ibm.katheder.view.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ibm.katheder.map.generation.RandomMapGenerator;
import com.ibm.katheder.view.MapVisulalization;

/**
 * <p>Action to generate a new random map.</p>
 * 
 * @author Sterbling
 * @version 1.0
 *
 */
public class RandomMapAction implements ActionListener{

	private final MapVisulalization mapVisualization;
	
	public RandomMapAction(final MapVisulalization mapVisualization) {
		this.mapVisualization = mapVisualization;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		final RandomMapGenerator generator = new RandomMapGenerator();
		mapVisualization.setNewMap(generator.generateMap());
	}
}
