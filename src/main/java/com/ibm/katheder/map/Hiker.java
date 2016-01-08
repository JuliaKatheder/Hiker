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

import java.util.List;

import com.ibm.katheder.map.error.InvalidMapPositionException;
import com.ibm.katheder.pathfinding.PathFinding;

/**
 * <p>
 * </p>
 * 
 * @author Julia Katheder
 * @version 1.0
 *
 */
public class Hiker {

	private GeoMap geoMap;

	private MapPosition position;
	private MapPosition destination;

	private int climbingKitCount;

	private PathFinding pathFinding;

	public Hiker(PathFinding pathFinding, GeoMap geoMap) {
		this(pathFinding, geoMap, new MapPosition(0, 0),
				new MapPosition(19, 19));
	}

	public Hiker(PathFinding pathFinding, GeoMap geoMap, int posX, int posY,
			int destX, int destY, int climbingKitCount) {
		this(pathFinding, geoMap, new MapPosition(posX, posY), new MapPosition(
				destX, destY));
		this.climbingKitCount = climbingKitCount;
	}

	public Hiker(PathFinding pathFinding, GeoMap geoMap,
			MapPosition startPoint, MapPosition destination) {
		this.pathFinding = pathFinding;
		this.geoMap = geoMap;
		this.position = startPoint;
		this.destination = destination;
		this.climbingKitCount = 1; // Default amount
	}

	public GeoMap getGeoMap() {
		return geoMap;
	}

	public void setGeoMap(GeoMap geoMap) {
		this.geoMap = geoMap;
	}

	public MapPosition getPosition() {
		return this.position;
	}

	public void setPosition(MapPosition position)
			throws InvalidMapPositionException {
		if (getGeoMap().getFieldType(destination.getY(), destination.getX())
				.getWeight() == Integer.MAX_VALUE) {
			throw new InvalidMapPositionException("Blubb blubb noo!");
		}
		this.position = position;
	}

	public MapPosition getDestination() {
		return this.destination;
	}

	public void setDestination(MapPosition destination)
			throws InvalidMapPositionException {
		if (getGeoMap().getFieldType(destination.getY(), destination.getX())
				.getWeight() == Integer.MAX_VALUE) {
			throw new InvalidMapPositionException("Blubb blubb noo!");
		}
		this.destination = destination;
	}

	public int getPosX() {
		return (int) position.getX();
	}

	public int getPosY() {
		return (int) position.getY();
	}

	public int getDestX() {
		return (int) destination.getX();
	}

	public int getDestY() {
		return (int) destination.getY();
	}

	public List<MapPosition> findPath() {
		return pathFinding.findPath(this.geoMap, this.position,
				this.destination, this.climbingKitCount);
	}
}
