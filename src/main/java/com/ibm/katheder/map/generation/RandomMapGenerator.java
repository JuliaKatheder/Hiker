package com.ibm.katheder.map.generation;

import java.util.Random;

import com.ibm.katheder.map.GeoMap;
import com.ibm.katheder.map.GeoMapBuilder;
import com.ibm.katheder.pathfinding.SimplexNoise;

// TODO Adapt to new structure
public class RandomMapGenerator implements MapGenerator {
	
	@Override
	public GeoMap generateMap() {
		TerrainTypes[][] map = new TerrainTypes[20][20];
		fillMap(map);
		
		final GeoMapBuilder mapBuilder = new GeoMapBuilder();
		
		// "Parse" the map
		for(int indY = 0; indY < map.length; indY++) {
			for(int indX = 0; indX < map.length; indX++) {
				final TerrainTypes type = map[indY][indX];
				mapBuilder.addGridSquare(type.getMappingId());
				
			}
			mapBuilder.newLine();
		}
		
		int mappingIndx = 0;
		for(TerrainTypes terrainType : TerrainTypes.values()) {			
			mapBuilder.addNewTerrainType(mappingIndx++, terrainType.name(), terrainType.getCost());
		}
		
		return mapBuilder.build();
	}
	
	private TerrainTypes[][] fillMap(TerrainTypes[][] map) {
		generatePlains(map);
		generateWoods(map);
		generateRocks(map);
		generateTrails(map);
		generateRivers(map);
		return map;
	}

	private void generatePlains(TerrainTypes[][] map) {
		int size = map.length;
		for(int i=0; i<size*size; i++) {
			map[i/size][i%size] = TerrainTypes.PLAIN;
		}
	}

	private void generateTrails(TerrainTypes[][] map) {
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

	private void generateWoods(TerrainTypes[][] map) {
		int size = map.length;
		for(int i=0; i<size*size; i++) {
			if(SimplexNoise.noise(i/size, i%size)<0)
				map[i/size][i%size] = TerrainTypes.WOOD;
		}
	}

	private void generateRocks(TerrainTypes[][] map) {
		int size = map.length;
		for(int i=0; i<size*size; i++) {
			if(SimplexNoise.noise(i/size, i%size)<-0.4)
				map[i/size][i%size] = TerrainTypes.ROCK;
		}
	}

	private void generateRivers(TerrainTypes[][] map) {
		Random r = new Random();
		int trailCount = 5;
		for(int i=0; i< trailCount; i++){
		
			int positionX = r.nextInt(map.length);
			int positionY = r.nextInt(map.length);
			
			int endX = r.nextInt(map.length);
			int endY = r.nextInt(map.length);
			
			do{
				if(map[positionX][positionY] == TerrainTypes.TRAIL)
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

}
