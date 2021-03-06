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
package com.ibm.katheder.view.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ibm.katheder.view.MapVisualization;
import com.ibm.katheder.view.color.ColorScheme.ColorSchemes;

/**
 * <p>
 * Action to switch the color scheme.
 * </p>
 * 
 * @author Julia Katheder
 * @version 1.0
 *
 */
public class ChangeColorSchemeAction implements ActionListener {

	private final MapVisualization mapVisualization;

	private final ColorSchemes colorScheme;

	public ChangeColorSchemeAction(final MapVisualization mapVisualization,
			final ColorSchemes colorScheme) {
		this.mapVisualization = mapVisualization;
		this.colorScheme = colorScheme;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		mapVisualization.setColorScheme(colorScheme);
	}
}
