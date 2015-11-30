
public class Main {

	public static void main(String[] args) {
		TreasureMap map = new TreasureMap(20);
		MapGeneration.generateMap(map.getMap());
		Visualisation vis = new Visualisation(map);

	}

}
