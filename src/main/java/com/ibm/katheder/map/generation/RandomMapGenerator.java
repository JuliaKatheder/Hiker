package com.ibm.katheder.map.generation;

import java.util.Random;

import com.ibm.katheder.map.GeoMap;
import com.ibm.katheder.map.GeoMapBuilder;
import com.ibm.katheder.utils.SimplexNoise;

public class RandomMapGenerator implements MapGenerator {
	
	@Override
	public GeoMap generateMap() {
		StaticTerrainTypes[][] map = new StaticTerrainTypes[20][20];
		fillMap(map);
		
		final GeoMapBuilder mapBuilder = new GeoMapBuilder();
		
		// "Parse" the map
		for(int indY = 0; indY < map.length; indY++) {
			for(int indX = 0; indX < map.length; indX++) {
				final StaticTerrainTypes type = map[indY][indX];
				mapBuilder.addGridSquare(type.getMappingId());
				
			}
			mapBuilder.newLine();
		}
		
		for(StaticTerrainTypes terrainType : StaticTerrainTypes.values()) {			
			mapBuilder.addNewTerrainType(terrainType.getMappingId(), terrainType.name(), terrainType.getCost());
		}
		
		return mapBuilder.build();
	}
	
	private StaticTerrainTypes[][] fillMap(StaticTerrainTypes[][] map) {
		generatePlains(map);
		generateWoods(map);
		generateRocks(map);
		generateTrails(map);
		generateRivers(map);
		return map;
	}
	
	private void generateWoods(StaticTerrainTypes[][] map) {
		int size = map.length;
		for(int i=0; i<size*size; i++) {
			if(SimplexNoise.noise(i/size, i%size) < 0)
				map[i/size][i%size] = StaticTerrainTypes.WOOD;
		}
	}
	
	private void generateRocks(StaticTerrainTypes[][] map) {
		int size = map.length;
		for(int i=0; i<size*size; i++) {
			
			if(SimplexNoise.noise(i/size, i%size) < -0.4)
				map[i/size][i%size] = StaticTerrainTypes.ROCK;
		}
	}

	private void generatePlains(StaticTerrainTypes[][] map) {
		int size = map.length;
		for(int i=0; i<size*size; i++) {
			map[i/size][i%size] = StaticTerrainTypes.PLAIN;
		}
	}

	private void generateTrails(StaticTerrainTypes[][] map) {
		Random r = new Random();
		int trailCount = 4;
		for(int i=0; i< trailCount; i++){
		
			int positionX = r.nextInt(map.length);
			int positionY = r.nextInt(map.length);
			
			int endX = r.nextInt(map.length);
			int endY = r.nextInt(map.length);
			
			do{
				map[positionX][positionY] = StaticTerrainTypes.TRAIL;
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

	private void generateRivers(StaticTerrainTypes[][] map) {
		Random r = new Random();
		int trailCount = 5;
		for(int i=0; i< trailCount; i++){
		
			int positionX = r.nextInt(map.length-1);
			int positionY = r.nextInt(map.length-1);
			
			int endX = r.nextInt(map.length-1);
			int endY = r.nextInt(map.length-1);
			
			do{
				if(map[positionX][positionY] == StaticTerrainTypes.TRAIL)
					map[positionX][positionY] = StaticTerrainTypes.BRIDGE;
				else
					map[positionX][positionY] = StaticTerrainTypes.WATER;
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

}
