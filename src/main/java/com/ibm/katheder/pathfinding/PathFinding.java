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
import java.util.LinkedList;
import java.util.Map;

import com.ibm.katheder.map.GeoMap;
import com.ibm.katheder.map.MapPosition;
import com.ibm.katheder.map.TerrainType;

public class PathFinding {

	public PathFinding() {

	}

	public ArrayList<MapPosition> findPath(GeoMap map, MapPosition position, MapPosition destination, int climbingKitCount) {
		ArrayList<MapPosition> open = new ArrayList<MapPosition>();
		ArrayList<MapPosition> closed = new ArrayList<MapPosition>();
		//Height = x und Width = y
		MapPosition[][] path = new MapPosition[map.getHeight()][map.getWidth()]; //Frage initialisierung mit null?

		//Initial settings
		open.add(position);

		while(!open.isEmpty()) {
			
			MapPosition x = null;
			int f = Integer.MAX_VALUE;
			for(MapPosition p : open) {
				if(f > f(p, position, destination, path, map, climbingKitCount)){
					f = f(p, position, destination, path, map, climbingKitCount);
					x = p;
				}
			}

			open.remove(x);
			closed.add(x);

			if(x.equals(destination)) {
				ArrayList<MapPosition> route = new ArrayList<MapPosition>();

				route.add(x);
				
				while(!x.equals(position)) {
					route.add(path[x.getY()][x.getX()]);
					x = path[x.getY()][x.getX()];
				}
				
				Collections.reverse(route);
				return route;
				
			}

			ArrayList<MapPosition> neighbours = neighbours(x, map);

			for(MapPosition y : neighbours) {
				final boolean containedInOpen = open.contains(y);
				final boolean containedInClosed = closed.contains(y);
				if(!containedInOpen && !containedInClosed) {
					path[y.getY()][y.getX()] = x;
					open.add(y);
				} else  {
					int formerCosts = g(y, position, path, map, climbingKitCount);
					int newCosts = g(x, position, path, map, climbingKitCount) + map.getCost(y.getY(), y.getX());
					if( newCosts < formerCosts ) {
						path[y.getY()][y.getX()] = x;
						if(closed.contains(y)) {
							closed.remove(y);
							open.add(y);
						}

					}
				}
			}
		}
		return null;

	}

	private int h(MapPosition position, MapPosition destination, Map<Integer, TerrainType> terrainTypes) {
		int h = Math.abs(destination.getX()-position.getX());
		h += Math.abs(destination.getY()-position.getY());
		h *= Collections.min(new LinkedList<TerrainType>(terrainTypes.values()), TerrainType.getWeightComparator()).getWeight();
		return h;
	}

	private int g(MapPosition position, MapPosition startingPosition, MapPosition[][] path, GeoMap map, int climbingKitCount) {
		int g = 0;//map.getCost(startingPosition.getX(), startingPosition.getY());
		while(!position.equals(startingPosition)) {
			if(map.getCost(position.getY(), position.getX()) == Integer.MAX_VALUE)
				return Integer.MAX_VALUE;
			if(map.getFieldType(position.getY(), position.getX()).getMappingId() == 5) {
				if(climbingKitCount > 0) {
					g += map.getCost(position.getY(), position.getX());
					climbingKitCount--;
				} else
					return Integer.MAX_VALUE;
			} else
				g += map.getCost(position.getY(), position.getX());
			position = path[position.getY()][position.getX()];
		}
		return g;
	}

	private int f(MapPosition position, MapPosition startingPosition, MapPosition destination, MapPosition[][] path, GeoMap map, int climbingKitCount) {
		if(g(position, startingPosition, path, map, climbingKitCount) == Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		return g(position, startingPosition, path, map, climbingKitCount) + h(position, destination, map.getTerrainTypes());
	}


	private ArrayList<MapPosition> neighbours(MapPosition n, GeoMap map) {
		ArrayList<MapPosition> neighbours = new ArrayList<MapPosition>();

		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				int y = n.getY()+i;
				int x = n.getX()+j;
				if((j!=i) && (j + i)!=0 && y >= 0 && x >= 0 && y < map.getHeight() && x < map.getWidth()){
					MapPosition m = new MapPosition(y, x);
					neighbours.add(m);
				}	
			}
		}

		/* Version für diagonales Laufen des Wanderers

		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				int x = n.getX()+i;
				int y = n.getY()+j;
				if((j!=0 || i!=0) && x > 0 && y > 0 && x < map.getSize() && y < map.getSize()){
					Node m = new Node(x, y);
				}	
			}
		}
		 */

		return neighbours;
	}

}
