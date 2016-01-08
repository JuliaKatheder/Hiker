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
package com.ibm.katheder.map;

/**
 * <p>
 * Stores a single position on a given map.
 * </p>
 * 
 * @author Julia Katheder
 * @version 1.0
 *
 */
public class MapPosition {

	private int y;
	private int x;

	/**
	 * Constructor for a point on the map.
	 * 
	 * @param y
	 *            value on the Y-Axis ("Height" of the map)
	 * @param x
	 *            value on the X-Axis ("Width" of the map)
	 */
	public MapPosition(int y, int x) {
		this.y = y;
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setLocation(int y, int x) {
		this.y = y;
		this.x = x;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MapPosition) {
			final MapPosition compareEle = (MapPosition) obj;
			return compareEle.getX() == this.getX()
					&& compareEle.getY() == this.getY();
		}
		return false;
	}

	public String toString() {
		return "Y-Axis: " + this.getY() + " X-Axis: " + this.getX();
	}
}
