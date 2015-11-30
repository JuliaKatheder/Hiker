
public class TreasureMap {
	private FieldCost costs;
	private Hiker hiker;
	private FieldType[][] map;
	
	public TreasureMap(int size) {
		this.costs = new FieldCost();
		this.hiker = new Hiker();
		this.map = new FieldType[size][size];
	}
	
	public TreasureMap(FieldCost costs, Hiker hiker, FieldType[][] map) {
		this.costs = costs;
		this.hiker = hiker;
		this.map = map;
	}
	
	public FieldCost getCosts() {
		return costs;
	}
	
	public void setCosts(FieldCost costs) {
		this.costs = costs;
	}
	
	public Hiker getHiker() {
		return hiker;
	}
	
	public void setHiker(Hiker hiker) {
		this.hiker = hiker;
	}
	
	public FieldType[][] getMap() {
		return map;
	}
	
	public void setMap(FieldType[][] map) {
		this.map = map;
	}
	
	public int getSize(){
		return this.map.length;
	}
	
	public FieldType getFieldType(int x, int y) {
		return map[x][y];
	}
	
	
}
