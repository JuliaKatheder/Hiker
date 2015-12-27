/**
 * 
 */
package com.ibm.katheder.view.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ibm.katheder.map.generation.RandomMapGenerator;
import com.ibm.katheder.view.MapVisulalization;

/**
 * Action to generate a new random map.
 * 
 * @author Sterbling
 *
 */
public class RandomMapAction implements ActionListener{

	
	private MapVisulalization mapVisualization;
	
	public RandomMapAction(MapVisulalization mapVisualization) {
		this.mapVisualization = mapVisualization;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		RandomMapGenerator generator = new RandomMapGenerator();
		mapVisualization.setNewMap(generator.generateMap());
	}
}
