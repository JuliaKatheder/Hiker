/**
 * 
 */
package com.ibm.katheder.view.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ibm.katheder.view.MapVisulalization;
import com.ibm.katheder.view.color.ColorScheme.ColorSchemes;

/**
 * <p>Action to switch the color scheme.</p>
 * 
 * @author Sterbling
 * @version 1.0
 *
 */
public class ChangeColorSchemeAction implements ActionListener{

	private final MapVisulalization mapVisualization;
	
	private final ColorSchemes colorScheme;
	
	public ChangeColorSchemeAction(final MapVisulalization mapVisualization, final ColorSchemes colorScheme) {
		this.mapVisualization = mapVisualization;
		this.colorScheme = colorScheme;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		mapVisualization.setColorScheme(colorScheme);
	}
}
