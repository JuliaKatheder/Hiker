package com.ibm.katheder.pathfinding;

import java.util.ArrayList;

import com.ibm.katheder.MapPosition;
import com.ibm.katheder.map.HikingMap;
import com.ibm.katheder.map.generation.TerrainTypes;

public class PathFinding {
	
	private HikingMap map;
	private TerrainTypes[][] nodes;
	private MapPosition destination;
	private MapPosition position;
	private ArrayList<MapPosition> open;
	private ArrayList<MapPosition> closed;
	private ArrayList<MapPosition> path;
	
	public PathFinding(HikingMap map) {
		this.map = map;
		this.nodes = map.getMap();
		this.destination = map.getHiker().getDestination();
		this.position = map.getHiker().getPosition();
		this.open = new ArrayList<MapPosition>();
		this.open.add(this.position);
		this.closed = new ArrayList<MapPosition>();
		this.path = new ArrayList<MapPosition>();
		this.path.add(this.position);
		
	}
	
	public int findPath() {
		int h = estimateCosts(this.position);
		int g = optimalCosts(this.position);
		int f = h + g;
		System.out.println("h: "+h+" g: "+g+" f: "+f);
		return this.calculateStep();
	}
	
	private int calculateStep() {
		
		if(open.isEmpty())
			return 0;
		
		/* Minimum F */
		int minimum = 0;
		MapPosition nextNode = new MapPosition(0,0);

		for(int i = 0; i < open.size(); i++) {
			int h = estimateCosts(open.get(i));
			int g = optimalCosts(open.get(i));
			int f = h + g;
			if(f < minimum || minimum == 0) {
				nextNode = open.get(i);
				minimum = f;
				System.out.println("minimum: "+minimum);
				
			}
		}
		open.remove(nextNode);
		closed.add(nextNode);
		System.out.println("nextNode: "+nextNode.getX()+" "+nextNode.getY());
		/* Minimum F */
		
		updateOpenList(nextNode);
		
		
		
		return 1;
		
		
		
	}
	
	
	private void updateOpenList(MapPosition n) {
		calculateNeighbours(n);
	}

	private ArrayList<MapPosition> calculateNeighbours(MapPosition n) {
		ArrayList<MapPosition> neighbours = new ArrayList<MapPosition>();
		
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				int x = n.getX()+i;
				int y = n.getY()+j;
				if((j!=i) && (j + i)!=0 && x >= 0 && y >= 0 && x < map.getSize() && y < map.getSize()){
					MapPosition m = new MapPosition(x, y);
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
	/*
	private int calculateCosts() {
		
	}*/
	
	private int optimalCosts(MapPosition n) {
		int optimum = 0;
		for(int i = 0; i < path.size(); i++) {
			MapPosition node = path.get(i);
			optimum = optimum + map.getFieldType(node.getX(), node.getY()).getCost();
		}
		return optimum;
	}
	
	private int estimateCosts(MapPosition n) {
		int x = this.destination.getX() - n.getX();
		int y = this.destination.getY() - n.getY();
		
		int estimation = (x + y) * TerrainTypes.getMinCost();
		
		System.out.println("estimation: "+estimation+" x: "+x+" y: "+y+" minCosts: "+ TerrainTypes.getMinCost());
		return estimation;	
	}
	
}
