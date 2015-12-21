
public class Main {

	public static void main(String[] args) {
		TreasureMap map = new TreasureMap(20);
		FieldCost costs = new FieldCost();
		costs.setCost(FieldType.TRAIL, 1);
		costs.setCost(FieldType.BRIDGE, 2);
		costs.setCost(FieldType.PLAIN, 3);
		costs.setCost(FieldType.WOOD, 4);
		costs.setCost(FieldType.WATER, 1000);
		costs.setCost(FieldType.ROCK, 20);
		map.setCosts(costs);
		MapGeneration.generateMap(map.getMap());
		Visualisation vis = new Visualisation(map);
		PathFinding f = new PathFinding(map);
		f.findPath();

	}

}
