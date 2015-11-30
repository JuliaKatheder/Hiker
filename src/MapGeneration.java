import java.util.Random;

public class MapGeneration {
	
	public static FieldType[][] generateMap(FieldType[][] map) {
		
		generatePlains(map);
		generateWoods(map);
		generateRocks(map);
		generateTrails(map);
		generateRivers(map);
		
		return map;
	}

	private static void generatePlains(FieldType[][] map) {
		int size = map.length;
		for(int i=0; i<size*size; i++) {
			map[i/size][i%size] = FieldType.PLAIN;
		}
	}

	private static void generateTrails(FieldType[][] map) {
		Random r = new Random();
		int trailCount = 5;
		for(int i=0; i< trailCount; i++){
		
			int positionX = r.nextInt(map.length);
			int positionY = r.nextInt(map.length);
			
			int endX = r.nextInt(map.length);
			int endY = r.nextInt(map.length);
			
	
			
			do{
				map[positionX][positionY] = FieldType.TRAIL;
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

	private static void generateWoods(FieldType[][] map) {
		int size = map.length;
		for(int i=0; i<size*size; i++) {
			if(SimplexNoise.noise(i/size, i%size)<0)
				map[i/size][i%size] = FieldType.WOOD;
		}
	}

	private static void generateRocks(FieldType[][] map) {
		int size = map.length;
		for(int i=0; i<size*size; i++) {
			if(SimplexNoise.noise(i/size, i%size)<-0.4)
				map[i/size][i%size] = FieldType.ROCK;
		}
	}

	private static void generateRivers(FieldType[][] map) {

	}
}
