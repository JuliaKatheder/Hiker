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
package com.ibm.katheder.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Function;
import com.ibm.katheder.map.GeoMap;
import com.ibm.katheder.map.Hiker;
import com.ibm.katheder.map.MapPosition;
import com.ibm.katheder.map.TerrainType;

public class PathFinding {

	private static final int MAPPINGID_ROCK = 5;

	public ArrayList<MapPosition> findPath(final PathFindingData data)
			throws NoValidPathException {
		final GeoMap map = data.getMap();
		final MapPosition position = data.getStartingPosition();
		final MapPosition destination = data.getDestination();
		final ArrayList<MapPosition> open = new ArrayList<MapPosition>();
		final ArrayList<MapPosition> closed = new ArrayList<MapPosition>();
		final MapPosition[][] path = new MapPosition[map.getHeight()][map
				.getWidth()];

		final Function<MapPosition, Integer> fValue = new Function<MapPosition, Integer>() {

			@Override
			public Integer apply(MapPosition input) {
				return f(input, data, path);
			}

		};

		// Initial settings
		open.add(data.getStartingPosition());

		while (!open.isEmpty()) {

			MapPosition newPosition = Collections.min(open,
					new PathFindingValueComparator(fValue));

			open.remove(newPosition);
			closed.add(newPosition);

			if (f(newPosition, data, path) == Integer.MAX_VALUE) {
				throw new NoValidPathException(
						"You will never reach your destination dude!");
			}
			if (newPosition.equals(destination)) {
				ArrayList<MapPosition> route = new ArrayList<MapPosition>();

				route.add(newPosition);

				while (!newPosition.equals(position)) {
					route.add(path[newPosition.getY()][newPosition.getX()]);
					newPosition = path[newPosition.getY()][newPosition.getX()];
				}

				Collections.reverse(route);
				return route;

			}

			List<MapPosition> neighbours = neighbours(newPosition, map);

			for (MapPosition y : neighbours) {
				final boolean containedInOpen = open.contains(y);
				final boolean containedInClosed = closed.contains(y);
				if (!containedInOpen && !containedInClosed) {
					path[y.getY()][y.getX()] = newPosition;
					open.add(y);
				} else {
					int formerCosts = g(y, data, path);
					int newCosts = g(newPosition, data, path)
							+ map.getCost(y.getY(), y.getX());
					if (newCosts < formerCosts) {
						path[y.getY()][y.getX()] = newPosition;
						if (closed.contains(y)) {
							closed.remove(y);
							open.add(y);
						}

					}
				}
			}
		}
		return null;

	}

	/**
	 * Heuristic function that calculates the minimum costs to reach the
	 * destination from a field. It uses the minimum distance multiplied with
	 * the minimum costs of all Terraintypes, thus the function is optimistic.
	 * 
	 * @param data
	 *            the {@link PathFindingData}
	 * @return the heuristic value for a {@link MapPosition} in the
	 *         {@link GeoMap}
	 */
	private int h(final MapPosition currPosition, final PathFindingData data) {
		final int posCoordX = currPosition.getX();
		final int posCoordY = currPosition.getY();
		final int destCoordX = data.getDestination().getX();
		final int destCoordY = data.getDestination().getY();
		final int minCosts = Collections.min(
				new LinkedList<TerrainType>(data.getTerrainTypes().values()),
				TerrainType.getWeightComparator()).getWeight();

		int minCostPath = Math.abs(destCoordX - posCoordX)
				+ Math.abs(destCoordY - posCoordY);
		minCostPath *= minCosts;

		return minCostPath;
	}

	/**
	 * Function that sums up the costs of the path from a specific position on
	 * the map back to starting position. Thereby the costs of the starting
	 * position are 0. If the number of the traversed rock fields exceeds the
	 * number of climbingkits of the hiker the function returns the maximum
	 * Integer value.
	 * 
	 * @param currPosition
	 *            the {@link MapPosition} of which the costs are calculated
	 * @param data
	 *            the {@link PathFindingData} object that holds the information
	 *            passed by the {@link Hiker}
	 * @param path
	 *            the two-dimensional {@link MapPosition} Array that holds the
	 *            path information
	 * @return the cost of the current path to a specific {@link MapPosition}
	 */
	private int g(final MapPosition currPosition, final PathFindingData data,
			final MapPosition[][] path) {
		final GeoMap map = data.getMap();
		final MapPosition position = data.getStartingPosition();
		MapPosition newPosition = currPosition;
		int climbingKitCount = data.getClimbingKitCount();
		int sumCosts = 0;

		// Iterate until you reached the starting position
		while (!newPosition.equals(position)) {
			final int coordY = newPosition.getY();
			final int coordX = newPosition.getX();

			// Check if the field is "unreachable"
			if (map.getCost(coordY, coordX) == Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			}

			// Check for rocks and climbing kit amount
			final boolean isRockType = map.getFieldType(coordY, coordX)
					.getMappingId() == MAPPINGID_ROCK;

			if (isRockType && climbingKitCount > 0) {
				climbingKitCount--;
			} else if (isRockType && climbingKitCount == 0) {

				// If the path goes through a mountain and no climbingkit is
				// available the path is not feasible.
				return Integer.MAX_VALUE;
			}

			sumCosts += map.getCost(coordY, coordX);
			// Get the previous position in the path
			newPosition = path[coordY][coordX];
		}
		return sumCosts;
	}

	/**
	 * Function that returns the costs of the current Path of a specific
	 * position of the map plus its heuristic value.
	 * 
	 * @param currPosition
	 *            the {@link MapPosition} of which the costs are calculated
	 * @param data
	 *            the {@link PathFindingData} object that holds the information
	 *            passed by the {@link Hiker}
	 * @param path
	 *            the two-dimensional {@link MapPosition} Array that holds the
	 *            path information
	 * @return the cost of the current path to a specific {@link MapPosition}
	 *         plus its heuristic value
	 */
	private int f(final MapPosition currPosition, final PathFindingData data,
			MapPosition[][] path) {
		final int costCurrPath = g(currPosition, data, path);
		final int estimatedCost = h(currPosition, data);

		if (costCurrPath == Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}

		return costCurrPath + estimatedCost;
	}

	/**
	 * Function that returns all neighbours of a specific {@link MapPosition} in
	 * a list. It is noted that the Hiker moves horizontally and vertically but
	 * not diagonally.
	 * 
	 * @param currPosition
	 *            the {@link MapPosition} of which the neighbours are returned
	 * @param map
	 *            the {@link GeoMap} object which is needed for map dimensions
	 * @return List with all neighbouring {@link MapPosition} objects of
	 *         currPosition
	 */
	private List<MapPosition> neighbours(MapPosition currPosition, GeoMap map) {
		final LinkedList<MapPosition> neighbours = new LinkedList<MapPosition>();
		final int coordX = currPosition.getX();
		final int coordY = currPosition.getY();

		if (coordX > 0) {
			neighbours.add(new MapPosition(coordY, coordX - 1));
		}
		if (coordX < map.getWidth() - 1) {
			neighbours.add(new MapPosition(coordY, coordX + 1));
		}
		if (coordY > 0) {
			neighbours.add(new MapPosition(coordY - 1, coordX));
		}
		if (coordY < map.getHeight() - 1) {
			neighbours.add(new MapPosition(coordY + 1, coordX));
		}
		return neighbours;
	}

	private class PathFindingValueComparator implements Comparator<MapPosition> {

		final private Function<MapPosition, Integer> function;

		public PathFindingValueComparator(
				final Function<MapPosition, Integer> function) {
			super();
			this.function = function;
		}

		@Override
		public int compare(MapPosition position1, MapPosition position2) {
			return function.apply(position1) - function.apply(position2);
		}

	}

}
