package com.ibm.katheder.map.generation;

import java.util.Random;

import com.ibm.katheder.map.GeoMap;
import com.ibm.katheder.pathfinding.SimplexNoise;

public class RandomMapGenerator implements MapGenerator {
	
	public static TerrainTypes[][] generateMap(TerrainTypes[][] map) {
		
		generatePlains(map);
		generateWoods(map);
		generateRocks(map);
		generateTrails(map);
		generateRivers(map);
		
		return map;
	}

	private static void generatePlains(TerrainTypes[][] map) {
		int size = map.length;
		for(int i=0; i<size*size; i++) {
			map[i/size][i%size] = TerrainTypes.PLAIN;
		}
	}

	private static void generateTrails(TerrainTypes[][] map) {
		Random r = new Random();
		int trailCount = 4;
		for(int i=0; i< trailCount; i++){
		
			int positionX = r.nextInt(map.length);
			int positionY = r.nextInt(map.length);
			
			int endX = r.nextInt(map.length);
			int endY = r.nextInt(map.length);
			
			do{
				map[positionX][positionY] = TerrainTypes.TRAIL;
				double d = Math.random();
				
				if(d>0.5){
					if(positionX > endX)
						positionX--;
					else
						positionX++;
				} else {
					if(positionY > endY)
						positionY--;
					else
						positionY++;
				}
			}while(positionX!=endX && positionY!=endY);
		}
		
	}

	private static void generateWoods(TerrainTypes[][] map) {
		int size = map.length;
		for(int i=0; i<size*size; i++) {
			if(SimplexNoise.noise(i/size, i%size)<0)
				map[i/size][i%size] = TerrainTypes.WOOD;
		}
	}

	private static void generateRocks(TerrainTypes[][] map) {
		int size = map.length;
		for(int i=0; i<size*size; i++) {
			if(SimplexNoise.noise(i/size, i%size)<-0.4)
				map[i/size][i%size] = TerrainTypes.ROCK;
		}
	}

	private static void generateRivers(TerrainTypes[][] map) {
		Random r = new Random();
		int trailCount = 5;
		for(int i=0; i< trailCount; i++){
		
			int positionX = r.nextInt(map.length);
			int positionY = r.nextInt(map.length);
			
			int endX = r.nextInt(map.length);
			int endY = r.nextInt(map.length);
			
			do{
				if(map[positionX][positionY]==TerrainTypes.TRAIL)
					map[positionX][positionY] = TerrainTypes.BRIDGE;
				else
					map[positionX][positionY] = TerrainTypes.WATER;
				double d = Math.random();
				
				if(d>0.5){
					if(positionX > endX)
						positionX--;
					else
						positionX++;
				} else {
					if(positionY > endY)
						positionY--;
					else
						positionY++;
				}
			}while(positionX!=endX && positionY!=endY);
		}
	}

	@Override
	public GeoMap generateMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
